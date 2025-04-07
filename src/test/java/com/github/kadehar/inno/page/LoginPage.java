package com.github.kadehar.inno.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.kadehar.inno.data.User;
import io.qameta.allure.Param;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

public class LoginPage {
    private final SelenideElement username = $("#user-name");
    private final SelenideElement password = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement error = $("h3[data-test=error]");

    private LoginPage() {}

    @Step("Open login page")
    public static LoginPage loginPage() {
        Selenide.open("");
        return new LoginPage();
    }

    @Step("Login as {user.login}")
    public LoginPage login(@Param(value = "user", mode = HIDDEN) User user) {
        username.shouldBe(Condition.visible).setValue(user.login());
        password.shouldBe(Condition.visible).setValue(user.password());
        loginButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Verify error message is \"{errorMessage}\"")
    public void verifyErrorMessageIs(String errorMessage) {
        error.shouldBe(Condition.visible).should(Condition.text(errorMessage));
    }
}
