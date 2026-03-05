/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.support;

/**
 *
 * 
 */
public class ISO8583Exception extends RuntimeException {

    public ISO8583Exception() {
    }

    public ISO8583Exception(Throwable cause) {
        super(cause);
    }

    public ISO8583Exception(String cause) {
        super(cause);
    }

    public ISO8583Exception(String message, Exception cause) {
        super(message, cause);
    }
}
