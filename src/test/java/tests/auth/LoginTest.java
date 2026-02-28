package tests.auth;

import config.ConfigManager;
import tests.base.BaseTest;
import models.response.LoginResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.AuthService;

public class LoginTest extends BaseTest {

    private static final String USER_NAME = ConfigManager.get("username");
    private static final String PASS_WORD = ConfigManager.get("password");

    @Test
    public void shouldLoginSuccessfully(){
        LoginResponse response =
                new AuthService()
                        .loginAndGetToken(USER_NAME, PASS_WORD);
        System.out.println(response.getToken());
        Assert.assertNotNull(response.getToken());
    }
}
