package tests;

import com.codeborne.selenide.Condition;
import config.UserConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class DemoWebShopTest extends TestBase {
    public static UserConfig webConfig = ConfigFactory.create(UserConfig.class, System.getProperties());

    @Test
    void AuthRegistrationUserTest() {

        step("Get cookie by api and set it to browser", () -> {
            String authorizationCookie =
                    given()
                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                            .formParam("Email", webConfig.userLogin())
                            .formParam("Password", webConfig.userPassword())
                            .when()
                            .post("login")
                            .then()
                            .statusCode(302)
                            .extract()
                            .cookie("NOPCOMMERCE.AUTH");

            step("Open minimal content, because cookie can be set when site is opened", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));

            step("Set cookie to to browser", () ->
                    getWebDriver().manage().addCookie(
                            new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)));
        });


        step("Открыть главную страницу", () -> {
            open("");
        });

        step("Проверяем что зарегистрированный пользователь авторизовался", () -> {
            $(".account").shouldBe(Condition.visible)
                    .shouldHave(Condition.text("fvfny7uz6nqu@mail.ru"));
        });

    }
}





