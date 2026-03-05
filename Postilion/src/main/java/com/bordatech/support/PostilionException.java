/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.support;

import com.bordatech.beans.ISOLogger;
import java.net.SocketException;

/**
 *
 *
 */
public class PostilionException extends SocketException {

    ISOLogger log = new ISOLogger();

    public PostilionException() {
        super();
    }

    public PostilionException(String message) {
        super(message);
        log.generateLog1(new String[]{message});
    }
}
