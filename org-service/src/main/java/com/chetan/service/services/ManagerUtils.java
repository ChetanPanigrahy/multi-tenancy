package com.chetan.service.services;

import java.util.UUID;

public class ManagerUtils {

    public static String generateId(String prefix) {
        return prefix + UUID.randomUUID().toString().toUpperCase().subSequence(0, 15).toString()
                .replace("-", "");
    }
}
