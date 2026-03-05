/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.beans;

/**
 *
 *
 */
public enum ErrorCodes {

    PASSWORD_EMPTY_OR_NULL, USER_EXISTS, BAD_PASSWORD, INCORRECT_PASSWORD,
    UNKNOWN_USER, NON_MATCHING_PASSWORD, EXCEPTION, EMPTY_EMAIL, BAD_EMAIL, INVALID_MOBILE_NO,
    INVALID_NUMBER, SUCCESS, BADDATAFORMAT, CONNECTIONERROR;

    @Override
    public String toString() {
        switch (this) {
            case SUCCESS:
                return "Successful";
            case INVALID_NUMBER:
                return "Invalid Number";
            case INVALID_MOBILE_NO:
                return "Invalid Mobile Number.";
            case BAD_PASSWORD:
                return "Bad password";
            case USER_EXISTS:
                return "User already exists";
            case PASSWORD_EMPTY_OR_NULL:
                return "Password is empty";
            case INCORRECT_PASSWORD:
                return "Incorrect password";
            case UNKNOWN_USER:
                return "Unknown user";
            case NON_MATCHING_PASSWORD:
                return "Passwords did not match";
            case EXCEPTION:
                return "Exception occured";
            case EMPTY_EMAIL:
                return "Email address not supplied";
            case BAD_EMAIL:
                return "Not a valid email address";
            case BADDATAFORMAT:
                return "Invalid Data Format in Message";
            case CONNECTIONERROR:
                return "There is error in Connection";
            default:
                throw new AssertionError();
        }
    }
}
