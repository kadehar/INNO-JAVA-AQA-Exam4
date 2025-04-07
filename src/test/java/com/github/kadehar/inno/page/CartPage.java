package com.github.kadehar.inno.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.kadehar.inno.page.component.Title;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    private final SelenideElement checkoutButton = $("#checkout");
    private final SelenideElement cartBadge = $("[data-test=shopping-cart-badge]");
    private final ElementsCollection cartItems = $$(".cart_list > .cart_item");

    private CartPage() {
        new Title().assertIsVisible();
    }

    public static CartPage cartPage() {
        return new CartPage();
    }

    @Step("Go to checkout form page")
    public void goToCheckoutForm() {
        checkoutButton.shouldBe(Condition.visible).click();
    }

    @Step("Verify {count} item(-s) added to cart")
    public CartPage verifyProductsCount(int count) {
        cartBadge.shouldBe(Condition.visible).shouldHave(Condition.text(String.valueOf(count)));
        cartItems.shouldHave(CollectionCondition.size(count));
        return this;
    }
}
