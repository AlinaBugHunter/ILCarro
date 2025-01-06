package okhttp;

import dto.UserDTO;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseAPI;

import java.io.IOException;
import java.util.Random;

public class RegistrationTests implements BaseAPI {

    int randomInt = new Random().nextInt(1000) + 1000;

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

        Assert.assertEquals(response.code(), 200);

    }

}
