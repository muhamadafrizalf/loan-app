package com.enigma.loan_app.constant;

public class Message {
    /// Validation ///
    // Empty
    public static final String EMAIL_IS_REQUIRED = "Email is required. Please enter your email address.";
    public static final String PASSWORD_IS_REQUIRED = "Password is required. Please enter your password.";
    public static final String NAME_IS_REQUIRED = "Please enter your first name. It cannot be empty.";
    public static final String PHONE_IS_REQUIRED = "Please enter your phone number. It cannot be empty.";
    // Invalid format
    public static final String EMAIL_INVALID = "Please enter a valid email address. Make sure it follows the format: example@domain.com.";
    // Conflict
    public static final String EMAIL_ALREADY_EXIST = "This email address is already in use. Please try logging in or use a different email address.";
    public static final String PHONE_ALREADY_EXIST = "This phone number is already associated with another account. Please use a different number.";
    // Bad Request
    public static final String CONFIRM_PASSWORD_DO_NOT_MATCH = "The passwords do not match. Please try again.";
    public static final String CONFIRM_PASSWORD_IS_REQUIRED = "Please confirm your password. It is required.";
}
