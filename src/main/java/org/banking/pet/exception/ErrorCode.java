package org.banking.pet.exception;

public enum ErrorCode {
    DEPOSIT_INVALID("error.deposit.invalid"),
    ACCOUNT_NOT_FOUND("error.account.notfound"),
    WITHDRAW_INSUFFICIENT("error.withdraw.insufficient"),
    GENERIC("error.generic");

    private final String key;

    ErrorCode(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
