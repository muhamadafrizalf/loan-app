package com.enigma.loan_app.constant;

public class Message {
    /// Validation ///
    // EMPTY ID
    public static final String CUSTOMER_ID_IS_EMPTY = "Customer ID cannot be null or empty. Please provide a valid ID.";
    public static final String INSTALLMENT_TYPE_ID_IS_EMPTY = "Installment type ID cannot be null or empty. Please provide a valid ID.";
    public static final String LOAN_TYPE_ID_IS_EMPTY = "Loan type ID cannot be null or empty. Please provide a valid ID.";
    public static final String LOAN_TRANSACTION_ID_IS_EMPTY = "Loan transaction ID cannot be null or empty. Please provide a valid ID.";
    // REQUIRED FIELD
    public static final String EMAIL_IS_REQUIRED = "Email is required. Please enter your email address.";
    public static final String PASSWORD_IS_REQUIRED = "Password is required. Please enter your password.";
    public static final String NAME_IS_REQUIRED = "Please enter your first name. It cannot be empty.";
    public static final String PHONE_IS_REQUIRED = "Please enter your phone number. It cannot be empty.";
    public static final String INSTALLMENT_TYPE_IS_REQUIRED = "Please enter installment type. It cannot be empty.";
    public static final String MAX_LOAN_IS_REQUIRED = "Please enter the maximum amount of loan. It cannot be empty.";
    public static final String TYPE_IS_REQUIRED = "Please enter loan type. It cannot be empty.";
    public static final String NOMINAL_IS_REQUIRED = "Please enter the nominal loan. It cannot be empty.";
    public static final String INTEREST_RATE_IS_REQUIRED = "Please enter the interest rate. It cannot be empty.";
    // INVALID FORMAT
    public static final String EMAIL_INVALID = "Please enter a valid email address. Make sure it follows the format: example@domain.com.";
    public static final String PHONE_INVALID = "Phone number must be between 10 and 15 digits and may include a leading '+' and spaces.";
    public static final String MAX_LOAN_MUST_POSITIVE = "The maximum loan amount must be a positive number. Please enter a valid amount.";
    public static final String NOMINAL_MUST_POSITIVE = "The nominal loan must be a positive number.";
    public static final String INTEREST_RATE_MUST_POSITIVE = "The interest rate must be a positive number.";
    // CONFLICT
    public static final String EMAIL_ALREADY_EXIST = "This email address is already in use. Please try logging in or use a different email address.";
    public static final String PHONE_ALREADY_EXIST = "This phone number is already associated with another account. Please use a different number.";
    public static final String INSTALLMENT_TYPE_ALREADY_EXIST = "Installment type already exist.";
    // BAD REQUEST
    public static final String CONFIRM_PASSWORD_DO_NOT_MATCH = "The passwords do not match. Please try again.";
    public static final String CONFIRM_PASSWORD_IS_REQUIRED = "Please confirm your password. It is required.";
    public static final String NOMINAL_IS_GREATER_THAN_MAX_LOAN = "The nominal amount cannot exceed the maximum loan limit. Please enter a valid amount.";
    public static final String LOAN_TRANSACTION_ALREADY_APPROVED = "Loan transaction already approved.";
    // NOT FOUND
    public static final String CUSTOMER_NOT_FOUND = "Customer not found. Please check the provided ID and try again.";
    public static final String USER_NOT_FOUND = "User not found. Please check the provided ID and try again.";
    public static final String EMAIL_NOT_FOUND = "Email not found. Please check the provided email and try again.";
    public static final String INSTALLMENT_TYPE_NOT_FOUND = "Installment type not found. Please check the provided ID and try again.";
    public static final String LOAN_TYPE_NOT_FOUND = "Loan type not found. Please check the provided ID and try again.";
    public static final String LOAN_TRANSACTION_NOT_FOUND = "Loan transaction not found. Please check the provided ID and try again.";

    /// API ///
    // AUTH
    public static final String REGISTER_SUCCESS = "Registration successful! Welcome aboard!";
    public static final String REGISTER_SUCCESS_ADMIN = "Admin registration successful! You now have access to the admin panel.";
    public static final String LOGIN_SUCCESS = "Login successful! Welcome back!";
    // CREATED
    public static final String INSTALLMENT_TYPE_CREATED = "Installment type created successfully.";
    public static final String LOAN_TYPE_CREATED = "Loan type created successfully.";
    public static final String LOAN_TRANSACTION_CREATED = "Loan transaction created successfully";
    // FOUND
    public static final String CUSTOMER_FOUND = "Customer found successfully.";
    public static final String INSTALLMENT_TYPE_FOUND = "Installment type found successfully.";
    public static final String LOAN_TYPE_FOUND = "Loan type found successfully.";
    public static final String USER_FOUND = "User found successfully.";
    public static final String LOAN_TRANSACTION_FOUND = "Loan transaction found successfully.";
    // UPDATED
    public static final String CUSTOMER_UPDATED = "Successfully updated customer.";
    public static final String INSTALLMENT_TYPE_UPDATED = "Successfully updated Installment type.";
    public static final String LOAN_TYPE_UPDATED = "Successfully updated loan type";
    // DELETED
    public static final String CUSTOMER_DELETED = "Customer deleted successfully.";
    public static final String INSTALLMENT_TYPE_DELETED = "Instalment type deleted successfully.";
    public static final String LOAN_TYPE_DELETED = "Loan type deleted successfully";
    // APPROVED
    public static final String LOAN_TRANSACTION_APPROVED = "Loan transaction approved successfully.";

    // FOUND LIST
    public static String CUSTOMERS_FOUND (int size) {return String.format("Successfully retrieved %d customer(s).", size);}
    public static String INSTALLMENT_TYPE_FOUNDS(int size) { return String.format("Successfully retrieved %d installment type(s).", size);}
    public static String LOAN_TYPE_FOUNDS(int size) {return String.format("Successfully retrieved %d loan type(s).", size);}

}
