package utils;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public interface BaseAPI {

    Gson GSON = new Gson();
    MediaType JSON = MediaType.get("application/json");
    OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    String AUTH = "Authorization";

    String BASE_URL = "https://ilcarro-backend.herokuapp.com";
    String REGISTRATION = "/v1/user/registration/usernamepassword";
    String LOGIN = "/v1/user/login/usernamepassword";
    String ADD_NEW_CAR = "/v1/cars";
    String GET_USER_CARS = "/v1/cars/my";
    String DELETE_CAR_BY_ID = "/v1/cars/"; // + serialNumber

}
