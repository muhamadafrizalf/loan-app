package com.enigma.loan_app.constant;

public class Message {
    /// Validation ///
    // Empty ID
    public static final String CUSTOMER_ID_IS_EMPTY = "Customer ID cannot be null or empty. Please provide a valid ID.";
    public static final String INSTALLMENT_TYPE_ID_IS_EMPTY = "Installment type ID cannot be null or empty. Please provide a valid ID.";
    public static final String LOAN_TYPE_ID_IS_EMPTY = "Loan type ID cannot be null or empty. Please provide a valid ID.";
    public static final String LOAN_TRANSACTION_ID_IS_EMPTY = "Loan transaction ID cannot be null or empty. Please provide a valid ID.";
    // Required field
    public static final String EMAIL_IS_REQUIRED = "Email is required. Please enter your email address.";
    public static final String PASSWORD_IS_REQUIRED = "Password is required. Please enter your password.";
    public static final String NAME_IS_REQUIRED = "Please enter your first name. It cannot be empty.";
    public static final String PHONE_IS_REQUIRED = "Please enter your phone number. It cannot be empty.";
    public static final String INSTALLMENT_TYPE_IS_REQUIRED = "Please enter installment type. It cannot be empty.";
    public static final String MAX_LOAN_IS_REQUIRED = "Please enter the maximum amount of loan. It cannot be empty.";
    public static final String TYPE_IS_REQUIRED = "Please enter loan type. It cannot be empty.";
    public static final String NOMINAL_IS_REQUIRED = "Please enter the nominal loan. It cannot be empty.";
    // Invalid format
    public static final String EMAIL_INVALID = "Please enter a valid email address. Make sure it follows the format: example@domain.com.";
    public static final String PHONE_INVALID = "Phone number must be between 10 and 15 digits and may include a leading '+' and spaces.";
    public static final String MAX_LOAN_MUST_POSITIVE = "The maximum loan amount must be a positive number. Please enter a valid amount.";
    public static final String NOMINAL_MUST_POSITIVE = "The nominal loan must be a positive number.";
    // Conflict
    public static final String EMAIL_ALREADY_EXIST = "This email address is already in use. Please try logging in or use a different email address.";
    public static final String PHONE_ALREADY_EXIST = "This phone number is already associated with another account. Please use a different number.";
    public static final String INSTALLMENT_TYPE_ALREADY_EXIST = "Installment type already exist.";
    // Bad Request
    public static final String CONFIRM_PASSWORD_DO_NOT_MATCH = "The passwords do not match. Please try again.";
    public static final String CONFIRM_PASSWORD_IS_REQUIRED = "Please confirm your password. It is required.";
    public static final String NOMINAL_IS_GREATER_THAN_MAX_LOAN = "The nominal amount cannot exceed the maximum loan limit. Please enter a valid amount.";
    // Not Found
    public static final String CUSTOMER_NOT_FOUND = "Customer not found. Please check the provided ID and try again.";
    public static final String USER_NOT_FOUND = "User not found. Please check the provided ID and try again.";
    public static final String EMAIL_NOT_FOUND = "Email not found. Please check the provided email and try again.";
    public static final String INSTALLMENT_TYPE_NOT_FOUND = "Installment type not found. Please check the provided ID and try again.";
    public static final String LOAN_TYPE_NOT_FOUND = "Loan type not found. Please check the provided ID and try again.";
    public static final String LOAN_TRANSACTION_NOT_FOUND = "Loan transaction not found. Please check the provided ID and try again.";
}
