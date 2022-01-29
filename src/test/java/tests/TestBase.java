package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.App;
import config.UserConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void configureBaseUrl() {
        Configuration.baseUrl = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();
        Configuration.remote = App.config.remoteUrl();
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
