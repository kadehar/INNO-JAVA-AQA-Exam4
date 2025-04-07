package com.github.kadehar.inno.jupiter.extension;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.data.User;
import com.github.kadehar.inno.jupiter.annotation.WithUser;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.support.AnnotationSupport;

public class WithUserExtension implements ParameterResolver {
    private static final Config CFG = Config.getInstance();


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return AnnotationSupport.findAnnotation(
                extensionContext.getRequiredTestMethod(),
                WithUser.class
        ).isPresent() && parameterContext.getParameter().getType() == User.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        WithUser annotation = AnnotationSupport.findAnnotation(
                extensionContext.getRequiredTestMethod(),
                WithUser.class
        ).orElseThrow(() -> new IllegalArgumentException("Can not find WithUser annotation above test method!"));
        return switch (annotation.type()) {
            case Standard -> new User(CFG.standardUser(), CFG.password());
            case LockedOut -> new User(CFG.lockedOutUser(), CFG.password());
        };
    }
}
