package com.github.kadehar.inno.tests;

import com.github.kadehar.inno.data.TestData;
import com.github.kadehar.inno.data.User;
import com.github.kadehar.inno.jupiter.annotation.WebTest;
import com.github.kadehar.inno.jupiter.arguments.UsersArgumentsProvider;
import com.github.kadehar.inno.page.LoginPage;
import io.qameta.allure.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

@WebTest
public class SwagLabsTests {

    @ParameterizedTest
    @ArgumentsSource(UsersArgumentsProvider.class)
    @DisplayName("Verify that checkout sum is valid")
    void verifyCheckoutSumIsValid(@Param(value = "user", mode = HIDDEN) User user) {
        LoginPage.goTo()
                .login(user)
                .addProductsToCart(TestData.items())
                .goToCart()
                .goToCheckoutForm()
                .fillForm(
                        TestData.firstName(),
                        TestData.lastName(),
                        TestData.zipCode()
                )
                .continueCheckout()
                .verifyTotalPrice();
    }
}
