package okhttp;

import dto.*;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseAPI;

import java.io.IOException;
import java.util.Random;

public class RegistrationTests implements BaseAPI {

    SoftAssert softAssert = new SoftAssert();
    int randomInt = new Random().nextInt(1000) + 1000;

    // Status Code: 200

    @Test
    public void registration() {

        UserDTO user = UserDTO.builder()
                .firstName("Test")
                .lastName("Test")
                .username("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION).post(requestBody).build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                TokenDTO tokenDTO = GSON.fromJson(response.body().string(), TokenDTO.class);
                softAssert.assertEquals(response.code(), 200);
                softAssert.assertFalse(tokenDTO.getAccessToken().isBlank());
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> registration()");
            throw new RuntimeException(e);
        }

    }

    // Status Code: 400

    @Test
    public void registration_400_emptyFirstName() {

        UserDTO user = UserDTO.builder()
                .firstName("")
                .lastName("Test")
                .username("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.isSuccessful());
        System.out.println(response.toString());
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);

    }

    @Test
    public void registration_400_emptyLastName() {

        UserDTO user = UserDTO.builder()
                .firstName("Test")
                .lastName("")
                .username("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.isSuccessful());
        System.out.println(response.toString());
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);

    }

    @Test
    public void registration_400_emptyUsername() {

        UserDTO user = UserDTO.builder()
                .firstName("Test")
                .lastName("Test")
                .username("")
                .password("Test999!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.isSuccessful());
        System.out.println(response.toString());
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);

    }

    @Test
    public void registration_400_emptyPassword() {

        UserDTO user = UserDTO.builder()
                .firstName("Test")
                .lastName("Test")
                .username("test" + randomInt + "@example.com")
                .password("")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.isSuccessful());
        System.out.println(response.toString());
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);

    }

    // SUGGESTION: The status code should return 409 (Conflict)
    // when there is attempting to register with an already existing username or email
    @Test
    public void registration_400_duplicateUser() {

        UserDTO user = UserDTO.builder()
                .firstName("Test")
                .lastName("Test")
                .username("testemail@example.com")
                .password("Password123!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.isSuccessful());
        System.out.println(response.toString());
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);

    }

    @Test
    public void registration_400_invalidUsername() {

        UserDTO user = UserDTO.builder()
                .firstName("Test")
                .lastName("Test")
                .username("test" + randomInt + "example.com")
                .password("Test999!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.isSuccessful());
        System.out.println(response.toString());
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);

    }

    @Test
    public void registration_400_invalidPassword() {

        UserDTO user = UserDTO.builder()
                .firstName("Test")
                .lastName("Test")
                .username("test" + randomInt + "@example.com")
                .password("Test9999")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION).post(requestBody).build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                ErrorMessageDTO errorMessageDTO = GSON.fromJson(response.body().string(), ErrorMessageDTO.class);
                System.out.println(errorMessageDTO.toString());
                softAssert.assertEquals(response.code(), 400);
                softAssert.assertTrue(errorMessageDTO.getError().equals("Bad Request"));
                softAssert.assertTrue(errorMessageDTO.getMessage().toString().contains("At least 8 characters; Must contain " +
                        "at least 1 uppercase letter, 1 lowercase letter, and 1 number; Can contain special characters [@$#^&*!]"));
                softAssert.assertAll();
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> ");
            throw new RuntimeException(e);
        }

    }

    // Status Code: 403

    @Test
    public void registration_403_invalidURL() {

        UserDTO user = UserDTO.builder()
                .firstName("Test")
                .lastName("Test")
                .username("testemail@@example.com")
                .password("Test9999")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + "/v1/user/registration").post(requestBody).build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Assert.assertEquals(response.code(), 403);
            } else {
                Assert.fail("Response Status Code -> " + response.code());
            }
        } catch (IOException e) {
            Assert.fail("Created Exception -> registration_403_invalidURL()");
            throw new RuntimeException(e);
        }

    }

}
