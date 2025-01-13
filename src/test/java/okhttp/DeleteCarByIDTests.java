package okhttp;

import dto.CarDTO_API;
import dto.CarsDTO;
import dto.TokenDTO;
import dto.UserDTO;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseAPI;

import java.io.IOException;

import static utils.PropertiesReader.getProperty;

public class DeleteCarByIDTests implements BaseAPI {

    TokenDTO tokenDTO;

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

    }

    // Method to get all cars of the user
    private CarDTO_API[] getUserCars() {

        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .get()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                CarsDTO carsDTO = GSON.fromJson(response.body().string(), CarsDTO.class);
                return carsDTO.getCars();
            } else {
                System.out.println("Response Status Code -> " + response.code());
                return null;
            }
        } catch (IOException e) {
            System.out.println("Created Exception -> getUserCars() -> return null");
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Test
    public void deleteCarByID() {

        CarDTO_API[] arrayCarDTO_API = getUserCars();
        String serialNumberOfFirstElement = "";
        if (arrayCarDTO_API != null) {
            serialNumberOfFirstElement = arrayCarDTO_API[0].getSerialNumber();
        } else {
            Assert.fail("Method GET returned null");
        }

        Request request = new Request.Builder()
                .url(BASE_URL + DELETE_CAR_BY_ID + serialNumberOfFirstElement)
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .delete()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                Assert.assertEquals(response.code(), 200);
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> deleteCarByID()");
        }

    }

}
