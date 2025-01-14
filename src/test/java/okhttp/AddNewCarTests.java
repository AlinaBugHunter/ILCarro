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
import java.util.Random;

import static utils.PropertiesReader.getProperty;

public class AddNewCarTests implements BaseAPI {

    SoftAssert softAssert = new SoftAssert();
    TokenDTO tokenDTO;
    int i = new Random().nextInt(10000);

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
            if (response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 200);
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

    // Status Code: 400

    @Test
    public void addNewCar_400_emptyCity() {

        CarDTO_API carDTO_api = CarDTO_API.builder()
                .city("")
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
            if (!response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 400);
                ErrorMessageDTO errorMessageDTO = GSON.fromJson(response.body().string(), ErrorMessageDTO.class);
                softAssert.assertTrue(errorMessageDTO.getError().equals("Bad Request"));
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> addNewCar_emptyCity()");
        }

    }

    @Test
    public void addNewCar_400_invalidCity() {

        CarDTO_API carDTO_api = CarDTO_API.builder()
                .city("1234567")
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
            if (!response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 400);
                ErrorMessageDTO errorMessageDTO = GSON.fromJson(response.body().string(), ErrorMessageDTO.class);
                softAssert.assertTrue(errorMessageDTO.getError().equals("Bad Request"));
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> addNewCar_invalidCity()");
        }

    }

    @Test
    public void addNewCar_400_spaceInManufacture() {

        CarDTO_API carDTO_api = CarDTO_API.builder()
                .city("Haifa")
                .manufacture(" ")
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
            if (!response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 400);
                ErrorMessageDTO errorMessageDTO = GSON.fromJson(response.body().string(), ErrorMessageDTO.class);
                softAssert.assertTrue(errorMessageDTO.getError().equals("Bad Request"));
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> addNewCar_spaceInManufacture()");
        }

    }

    // Status Code: 401

    @Test
    public void addNewCar_401_Unauthorized() {

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
                .addHeader(AUTH, "invalidToken")
                .post(requestBody)
                .build();

        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 401);
                ErrorMessageDTO errorMessageDTO = GSON.fromJson(response.body().string(), ErrorMessageDTO.class);
                softAssert.assertTrue(errorMessageDTO.getError().equals("Unauthorized"));
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> addNewCar_401_Unauthorized()");
        }

    }

    // Status Code: 403

    @Test
    public void addNewCar_403_invalidURL() {

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
                .url(BASE_URL + "/v1/invalidURL")
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .post(requestBody)
                .build();

        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                Assert.assertEquals(response.code(), 403);
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> addNewCar_403_invalidURL()");
        }

    }

    @Test
    public void addNewCar_403_WOHeaders() {

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
                .post(requestBody)
                .build();

        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 403);
                // ErrorMessageDTO errorMessageDTO = GSON.fromJson(response.body().string(), ErrorMessageDTO.class);
                // softAssert.assertTrue(errorMessageDTO.getError().equals("Forbidden"));
                // Cannot invoke "dto.ErrorMessageDTO.getError()" because "errorMessageDTO" is null
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> addNewCar_403_WOHeaders()");
        }

    }

    @Test
    public void addNewCar_403_invalidRequestMethod() {

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
                .get() // Should be POST
                .build();

        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                Assert.assertEquals(response.code(), 403);
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> addNewCar_403_invalidRequestMethod()");
        }

    }

    // Status Code: 500

    @Test
    public void addNewCar_500_InternalServerError() {

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

        String malformedJson = "{ \"city\": \"Haifa\", \"manufacture\": \"Mazda\" "; // Неполный JSON
        RequestBody requestBody = RequestBody.create(malformedJson, JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + ADD_NEW_CAR)
                .addHeader(AUTH, tokenDTO.getAccessToken())
                .post(requestBody)
                .build();

        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                softAssert.assertEquals(response.code(), 500);
                ErrorMessageDTO errorMessageDTO = GSON.fromJson(response.body().string(), ErrorMessageDTO.class);
                softAssert.assertEquals(errorMessageDTO.getError(), "Internal Server Error");
                softAssert.assertTrue(errorMessageDTO.getMessage().equals("JSON parse error: Unexpected end-of-input: " +
                        "expected close marker for Object (start marker at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); " +
                        "line: 1, column: 1]); nested exception is com.fasterxml.jackson.core.io.JsonEOFException: Unexpected end-of-input: " +
                        "expected close marker for Object (start marker at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 1])\n" +
                        " at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 43]"));
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> addNewCar_500_InternalServerError()");
        }

    }

}
