package com.github.kadehar.inno.jupiter.arguments;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.data.User;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UsersArgumentsProvider implements ArgumentsProvider {

    private static final Config CFG = Config.getInstance();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        Named.of(
                                "Login as " + CFG.standardUser(),
                                new User(CFG.standardUser(), CFG.password())
                        )
                ),
                Arguments.of(
                        Named.of(
                                "Login as " + CFG.glitchedUser(),
                                new User(CFG.glitchedUser(), CFG.password())
                        )
                )
        );
    }
}
