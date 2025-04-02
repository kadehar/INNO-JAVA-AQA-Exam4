package com.github.kadehar.inno.config;

public interface Config {
    static Config getInstance() {
        return LocalConfig.INSTANCE;
    }

    String baseUrl();
    String browser();
    String pageLoadStrategy();
    Boolean headless();
    Long timeout();
    Boolean enableSteps();
    String standardUser();
    String glitchedUser();
    String password();
}
