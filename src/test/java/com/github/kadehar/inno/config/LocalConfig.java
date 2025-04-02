package com.github.kadehar.inno.config;

import com.github.kadehar.inno.config.users.UsersConfig;
import com.github.kadehar.inno.config.web.WebConfig;
import org.aeonbits.owner.ConfigFactory;

public enum LocalConfig implements Config {
    INSTANCE;

    private static final WebConfig WEB_CONFIG = ConfigFactory.create(
            WebConfig.class,
            System.getProperties()
    );
    private static final UsersConfig USERS_CONFIG = ConfigFactory.create(
            UsersConfig.class,
            System.getProperties()
    );

    @Override
    public String baseUrl() {
        return WEB_CONFIG.baseUrl();
    }

    @Override
    public String browser() {
        return WEB_CONFIG.browser();
    }

    @Override
    public String pageLoadStrategy() {
        return WEB_CONFIG.pageLoadStrategy();
    }

    @Override
    public Boolean headless() {
        return WEB_CONFIG.headless();
    }

    @Override
    public Long timeout() {
        return WEB_CONFIG.timeout();
    }

    @Override
    public Boolean enableSteps() {
        return WEB_CONFIG.enableSteps();
    }

    @Override
    public String standardUser() {
        return USERS_CONFIG.standardUser();
    }

    @Override
    public String glitchedUser() {
        return USERS_CONFIG.glitchedUser();
    }

    @Override
    public String password() {
        return USERS_CONFIG.password();
    }
}
