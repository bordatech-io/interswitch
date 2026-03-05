/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.support;

import com.bordatech.beans.ISOLogger;

/**
 *
 *
 */
public class PostilionKeyException extends RuntimeException {

    ISOLogger log = new ISOLogger();

    public PostilionKeyException() {
        super();
    }

    public PostilionKeyException(String message) {
        super(message);
        log.generateLog1(new String[]{message});
    }
}
