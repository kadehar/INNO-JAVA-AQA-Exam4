package com.github.kadehar.inno.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.kadehar.inno.page.component.Title;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {
    private final SelenideElement backHomeBtn = $("#back-to-products");

    private CheckoutCompletePage() {
        new Title().assertIsVisible();
    }

    public static CheckoutCompletePage checkoutCompletePage() {
        return new CheckoutCompletePage();
    }

    @Step("Verify \"Back Home\" button is displayed")
    public CheckoutCompletePage verifyBackHomeButtonIsDisplayed() {
        backHomeBtn.shouldBe(Condition.visible);
        return this;
    }
}
