package ru.robofinance.test.project.core.exception;

public class CustomerNotFindException extends RuntimeException {
    private static final String CUSTOMER_NOT_EXIST = "Customer not exist";

    public CustomerNotFindException() {
        super(CUSTOMER_NOT_EXIST);
    }
}
