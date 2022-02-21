package tests;

import com.codeborne.selenide.Condition;
import config.UserConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static filters.LogFilter.logFilter;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTest extends TestBase {
    public static UserConfig webConfig = ConfigFactory.create(UserConfig.class, System.getProperties());
    public static String authorizationCookie;

    @Test
    @DisplayName("Авторизация и проверка что указана зарегистрированная эл. почта")
    void AuthRegistrationUserTest() {
        step("Get cookie by api and set it to browser", () -> {
            authorizationCookie =
                    given()
                            .filter(logFilter().withCustomTemplates())
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

        step("Проверяем что пользователь авторизовался", () -> {
            $(".account").shouldBe(Condition.visible)
                    .shouldHave(Condition.text("fvfny7uz6nqu@mail.ru"));
        });
    }

    @Test
    @DisplayName("Авторизация, добавление товара, проверка названия")
    void addedProductWithAuthTest() {

        step("Get cookie by api and set it to browser", () -> {
            authorizationCookie =
                    given()
                            .filter(logFilter().withCustomTemplates())
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

        given()
                .cookie("NOPCOMMERCE.AUTH", authorizationCookie)
                .when()
                .post("addproducttocart/catalog/31/1/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

        step("Открыть главную страницу", () -> {
            open("");
        });

        step("Переходим в корзину", () -> {
            $(".cart-label").click();
        });
        step("Проверяем товар в корзине", () -> {
            $(".product-name")
                    .shouldBe(Condition.visible)
                    .shouldHave(Condition.text("14.1-inch Laptop"));
        });
    }
}





