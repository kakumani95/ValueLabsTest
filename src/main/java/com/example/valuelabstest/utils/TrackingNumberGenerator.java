package com.example.valuelabstest.utils;

import java.security.SecureRandom;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Generate the unique 16 digit tracking number
 */
public class TrackingNumberGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int TRACKING_NUMBER_LENGTH = 16;
    private static final SecureRandom random = new SecureRandom();
    private static final Set<String> generatedTrackingNumbers = ConcurrentHashMap.newKeySet();

    public static String generateUniqueTrackingNumber() {
        String trackingNumber;
        do {
            trackingNumber = generateRandomTrackingNumber();
        } while (!isUnique(trackingNumber));
        return trackingNumber;
    }

    private static String generateRandomTrackingNumber() {
        StringBuilder trackingNumber = new StringBuilder(TRACKING_NUMBER_LENGTH);
        for (int i = 0; i < TRACKING_NUMBER_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            trackingNumber.append(CHARACTERS.charAt(index));
        }
        return trackingNumber.toString();
    }

    private static boolean isUnique(String trackingNumber) {
        return generatedTrackingNumbers.add(trackingNumber);
    }
}