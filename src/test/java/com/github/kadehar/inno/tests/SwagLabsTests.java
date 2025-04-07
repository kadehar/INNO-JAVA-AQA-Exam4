package com.github.kadehar.inno.tests;

import com.github.kadehar.inno.data.TestData;
import com.github.kadehar.inno.data.User;
import com.github.kadehar.inno.jupiter.annotation.WebTest;
import com.github.kadehar.inno.jupiter.annotation.WithUser;
import com.github.kadehar.inno.jupiter.arguments.UsersArgumentsProvider;
import io.qameta.allure.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.github.kadehar.inno.page.CartPage.cartPage;
import static com.github.kadehar.inno.page.CheckoutCompletePage.checkoutCompletePage;
import static com.github.kadehar.inno.page.CheckoutFormPage.checkoutFormPage;
import static com.github.kadehar.inno.page.CheckoutOverviewPage.checkoutOverviewPage;
import static com.github.kadehar.inno.page.LoginPage.loginPage;
import static com.github.kadehar.inno.page.ProductsPage.productsPage;
import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

@WebTest
public class SwagLabsTests {

    @ParameterizedTest
    @ArgumentsSource(UsersArgumentsProvider.class)
    @DisplayName("Verify that checkout sum is valid")
    void verifyCheckoutSumIsValid(@Param(value = "user", mode = HIDDEN) User user) {
        final int itemsCount = TestData.items();
        loginPage().login(user);
        productsPage()
                .addProductsToCart(itemsCount)
                .goToCart();
        cartPage()
                .verifyProductsCount(itemsCount)
                .goToCheckoutForm();
        checkoutFormPage()
                .fillForm(
                        TestData.firstName(),
                        TestData.lastName(),
                        TestData.zipCode()
                )
                .continueCheckout();
        checkoutOverviewPage()
                .verifyTotalPrice()
                .clickFinish();
        checkoutCompletePage()
                .verifyBackHomeButtonIsDisplayed();
    }

    @Test
    @DisplayName("Verify log in is successful")
    @WithUser(type = WithUser.Type.Standard)
    void verifyLogInIsSuccessful(@Param(value = "user", mode = HIDDEN) User user) {
        loginPage().login(user);
        productsPage().verifyProductsListIsDisplayed();
    }

    @Test
    @DisplayName("Verify log in is failed")
    @WithUser(type = WithUser.Type.LockedOut)
    void verifyLogInIsFailed(@Param(value = "user", mode = HIDDEN) User user) {
        loginPage()
                .login(user)
                .verifyErrorMessageIs("Sorry, this user has been locked out");
    }
}
