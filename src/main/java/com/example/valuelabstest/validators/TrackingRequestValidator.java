package com.example.valuelabstest.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Validate all request parameters
 */
public class TrackingRequestValidator {

    private static final String UUID_REGEX = "^[\\da-f]{8}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{12}$";

    private static final String DATE_TIME_REGEX = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2} \\d{2}:\\d{2}$";

    public static List<String> validateParameters(String origin_country_id, String destination_country_id, String weight,
                                                   String created_at, String customer_id, String customer_name, String customer_slug) {
        List<String> errors = new ArrayList<>();

        // Validate origin_country_id (ISO 3166-1 alpha-2)
        if (!Pattern.matches("^[A-Z]{2}$", origin_country_id)) {
            errors.add("origin_country_id must be a valid ISO 3166-1 alpha-2 country code.");
        }

        // Validate destination_country_id (ISO 3166-1 alpha-2)
        if (!Pattern.matches("^[A-Z]{2}$", destination_country_id)) {
            errors.add("destination_country_id must be a valid ISO 3166-1 alpha-2 country code.");
        }

        // Validate weight (up to three decimal places)
        if (!Pattern.matches("^(\\d+(\\.\\d{1,3})?)$", weight)) {
            errors.add("weight must be a valid weight in kilograms (up to three decimal places).");
        }

        // Validate created_at (RFC 3339 format)
        if (!isValidDateTime(created_at)) {
            errors.add("created_at must be in RFC 3339 format.");
        }

        // Validate customer_id (UUID)
        if (!Pattern.matches(UUID_REGEX, customer_id)) {
            errors.add("customer_id must be a valid UUID.");
        }

        // Validate customer_name
        if (customer_name == null || customer_name.isEmpty()) {
            errors.add("customer_name is required.");
        }

        // Validate customer_slug (kebab-case)
        if (!Pattern.matches("^[a-z-9]+(-[a-z-9]+)*$", customer_slug)) {
            errors.add("customer_slug must be in slug-case/kebab-case format.");
        }

        return errors;
    }

    public static boolean isValidDateTime(String dateTime) {
        Pattern pattern = Pattern.compile(DATE_TIME_REGEX);
        Matcher matcher = pattern.matcher(dateTime);
        return matcher.matches();
    }

}