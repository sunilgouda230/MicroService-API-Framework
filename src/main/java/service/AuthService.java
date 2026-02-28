package service;

import auth.TokenManager;
import config.ConfigManager;
import core.BaseRequest;
import io.restassured.response.Response;
import models.common.ErrorResponse;
import models.request.LoginRequest;
import models.response.LoginResponse;

public class AuthService extends BaseRequest {

    private static final String BASE_URL = ConfigManager.get("auth.base.url");

    public LoginResponse loginAndGetToken(String username, String password){

        LoginRequest loginRequest = new LoginRequest(username, password);

        Response response = post(BASE_URL + "/auth", loginRequest);

        LoginResponse loginResponse = handleResponse(response,LoginResponse.class,ErrorResponse.class);

        TokenManager.setToken("admin",loginResponse.getToken());

        return handleResponse(response, LoginResponse.class, ErrorResponse.class);
    }
}
