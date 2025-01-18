package okhttp;

import dto.*;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseAPI;

import java.io.IOException;

import static utils.PropertiesReader.getProperty;

public class GetUserCarsTests implements BaseAPI {

    SoftAssert softAssert = new SoftAssert();
    TokenDTO tokenDTO;

    @BeforeClass(alwaysRun = true)
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

    // Status Code: 200

    @Test
    public void getUserCars() {

        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .get()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
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
            System.out.println(e.getMessage());
        }

    }

    // Status Code: 400

    @Test
    public void getUserCars_400_invalidRequestMethod() {

        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .delete()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                ErrorMessageDTO errorMessageDTO = GSON.fromJson(response.body().string(), ErrorMessageDTO.class);
                softAssert.assertEquals(response.code(), 400);
                softAssert.assertEquals(errorMessageDTO.getError(), "Bad Request");
                softAssert.assertTrue(errorMessageDTO.getMessage().equals("Car with serial number my not found"));
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> getUserCars_400_invalidRequestMethod()");
            System.out.println(e.getMessage());
        }

    }

    // Status Code: 401

    @Test
    public void getUserCars_401_Unauthorized() {

        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .addHeader(AUTH, tokenDTO.getAccessToken() + "invalidToken")
                .get()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                ErrorMessageDTO errorMessageDTO = GSON.fromJson(response.body().string(), ErrorMessageDTO.class);
                softAssert.assertEquals(response.code(), 401);
                softAssert.assertEquals(errorMessageDTO.getError(), "Unauthorized");
                softAssert.assertTrue(errorMessageDTO.getMessage().equals("JWT signature does not match locally computed " +
                        "signature. JWT validity cannot be asserted and should not be trusted."));
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> getUserCars_401_Unauthorized()");
            System.out.println(e.getMessage());
        }

    }

    // Status Code: 403

    @Test
    public void getUserCars_403_invalidURL() {

        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS + "/invalidURL")
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .get()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Assert.assertEquals(response.code(), 403);
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> getUserCars_403_invalidURL()");
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void getUserCars_403_WOHeaders() {

        Request request = new Request.Builder()
                .url(BASE_URL + GET_USER_CARS)
                .get()
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Assert.assertEquals(response.code(), 403);
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> getUserCars_403_WOHeaders()");
            System.out.println(e.getMessage());
        }

    }

}
