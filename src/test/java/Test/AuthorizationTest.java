package Test;

import com.codeborne.selenide.Configuration;
import data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import Page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {

    @AfterAll
    static void shouldCleanDataBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    void shouldAuthorizationFirstUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoForFirstUser();
        var verificationPage = loginPage.validInfo(authInfo);
        var verifyInfo = SQLHelper.getVerificationCode();
        var dashboardPage = verificationPage.validCode(verifyInfo);
        dashboardPage.check();
    }
}
