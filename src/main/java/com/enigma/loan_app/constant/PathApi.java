package com.enigma.loan_app.constant;

public class PathApi {
    // BASE
    public static final String BASE = "/api";

    // FEATURE
    public static final String AUTH = BASE + "/auth";
    public static final String CUSTOMER = BASE + "/customers";
    public static final String INSTALLMENT_TYPE = BASE + "/installment-types";
    public static final String LOAN_TYPE = BASE + "/loan-types";
    public static final String USER = BASE + "/users";
    public static final String LOAN_TRANSACTION = BASE + "/transactions";

    // SUB PATH
    public static final String REGISTER = "/signup";
    public static final String REGISTER_ADMIN = "/signup/admin";
    public static final String LOGIN = "/signin";
    public static final String ID = "/{id}";
}
