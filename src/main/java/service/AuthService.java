package service;

import auth.TokenManager;
import config.ConfigManager;
import core.BaseRequest;
import io.restassured.response.Response;
import models.common.ErrorResponse;
import models.request.LoginRequest;
import models.response.LoginResponse;

public class AuthService extends BaseRequest {

    private String getBaseUrl() {
        return ConfigManager.get("base.url");
    }

    public LoginResponse loginAndGetToken(String username, String password){

        LoginRequest loginRequest = new LoginRequest(username, password);

        Response response = post(
                getBaseUrl() + "/auth",
                loginRequest
        );

        LoginResponse loginResponse =
                handleResponse(response, LoginResponse.class, ErrorResponse.class);

        TokenManager.setToken("admin", loginResponse.getToken());

        return loginResponse;
    }
}
