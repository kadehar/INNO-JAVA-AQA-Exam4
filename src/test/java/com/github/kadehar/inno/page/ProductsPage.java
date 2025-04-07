package com.github.kadehar.inno.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.kadehar.inno.page.component.Title;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ProductsPage {
    private final SelenideElement inventoryList = $(".inventory_list");
    private final ElementsCollection pricebars = inventoryList.$$(".pricebar");
    private final SelenideElement cart = $(".shopping_cart_link");

    private ProductsPage() {
        new Title().assertIsVisible();
    }

    public static ProductsPage productsPage() {
        return new ProductsPage();
    }

    @Step("Add {count} product(-s) to cart")
    public ProductsPage addProductsToCart(int count) {
        pricebars.stream()
                .limit(count)
                .forEach(pricebar ->
                        pricebar.$(".btn_inventory")
                                .shouldBe(Condition.visible)
                                .click()
                );
        return this;
    }

    @Step("Go to cart")
    public void goToCart() {
        cart.shouldBe(Condition.visible).click();
    }

    @Step("Verify products list is displayed")
    public void verifyProductsListIsDisplayed() {
        inventoryList.shouldBe(Condition.visible);
    }
}
