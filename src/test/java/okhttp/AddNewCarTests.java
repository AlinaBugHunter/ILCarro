package okhttp;

import dto.CarDTO_API;
import dto.ResponseMessageDTO;
import dto.TokenDTO;
import dto.UserDTO;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseAPI;

import java.io.IOException;
import java.util.Random;

import static utils.PropertiesReader.getProperty;

public class AddNewCarTests implements BaseAPI {

    SoftAssert softAssert = new SoftAssert();
    TokenDTO tokenDTO;
    int i = new Random().nextInt(10000);

    @BeforeClass
    public void login() {
        UserDTO user = UserDTO.builder()
                .username(getProperty("login.properties", "username"))
                .password(getProperty("login.properties", "password"))
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + LOGIN).post(requestBody).build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                tokenDTO = GSON.fromJson(response.body().string(), TokenDTO.class);
                System.out.println("Access Token -> " + tokenDTO.getAccessToken());
            } else {
                System.out.println("Something went wrong -> login()");
            }
        } catch (IOException e) {
            System.out.println("Created Exception -> login()");
        }
    }

    @Test
    public void addNewCar() {

        CarDTO_API carDTO_api = CarDTO_API.builder()
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel("Electric")
                .seats(4)
                .carClass("A")
                .serialNumber("SN-" + i)
                .pricePerDay(123.99)
                .about("About my car")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(carDTO_api), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CAR)
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .post(requestBody)
                .build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response.isSuccessful());
            System.out.println(response.code());
            if (response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 200);
                System.out.println(response.toString());
                ResponseMessageDTO responseMessageDTO = GSON.fromJson(response.body().string(), ResponseMessageDTO.class);
                softAssert.assertTrue(responseMessageDTO.getMessage().equals("Car added successfully"));
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> addNewCar()");
        }

    }

}
