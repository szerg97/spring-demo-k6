package com.example.springdemo.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CreditLimitValidator {
    private static final Map<String, BigDecimal> map = new HashMap<>();

    static {
        map.put("Account 1", new BigDecimal("18000"));
        map.put("Account 2", new BigDecimal("5000"));
        map.put("Account 3", new BigDecimal("10000"));
    }

    public static ValidationStatus validateCreditLimit(String account, BigDecimal amount){
        if (amount.doubleValue() > map.get(account).doubleValue()){
            return ValidationStatus.INVALID;
        }
        return ValidationStatus.VALID;
    }
}
