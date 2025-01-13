package okhttp;

import dto.CarsDTO;
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

public class DeleteCarByIDTests implements BaseAPI {

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
                //System.out.println("Access Token -> " + tokenDTO.getAccessToken());
            } else {
                System.out.println("Something went wrong -> login()");
            }
        } catch (IOException e) {
            System.out.println("Created Exception -> login()");
        }

        Request request1 = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .get()
                .build();

        try {
            Response response = OK_HTTP_CLIENT.newCall(request1).execute();
            if (response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 200);
                CarsDTO carsDTO = GSON.fromJson(response.body().string(), CarsDTO.class);

                softAssert.assertTrue(carsDTO.getCars().length > 0);
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> getUserCars()");
        }

    }

    // Метод для получения всех автомобилей пользователя
    private CarsDTO getUserCars() {

        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .get()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return GSON.fromJson(response.body().string(), CarsDTO.class);
            } else {
                throw new IOException("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> getUserCars()");
            return null;
        }

    }

    @Test
    public void deleteCarByID() {

        CarsDTO carsDTO = getUserCars();

        String serialNumberToDelete = null;
        if (carsDTO != null) {
            serialNumberToDelete = carsDTO.getCars()[0].getSerialNumber();
        }

        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR_BY_ID + serialNumberToDelete)
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .delete()
                .build();

        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if (response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 200);
                carsDTO = GSON.fromJson(response.body().string(), CarsDTO.class);
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> getUserCars()");
        }

    }

}
