package tests.auth;

import config.ConfigManager;
import tests.base.BaseTest;
import models.response.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.AuthService;

public class LoginTest extends BaseTest {

    private String getUsername() {
        return ConfigManager.get("username");
    }

    private String getPassword() {
        return ConfigManager.get("password");
    }

    @Test
    public void shouldLoginSuccessfully(){
        LoginResponse response =
                new AuthService()
                        .loginAndGetToken(getUsername(), getPassword());
        System.out.println(response.getToken());
        Assert.assertNotNull(response.getToken());
    }
}
