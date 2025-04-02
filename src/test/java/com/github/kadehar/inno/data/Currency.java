package com.github.kadehar.inno.data;

public enum Currency {
    USD("$");

    private final String sign;

    Currency(String sign) {
        this.sign = sign;
    }

    public String sign() {
        return sign;
    }
}
