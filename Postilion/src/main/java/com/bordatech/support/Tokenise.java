/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.support;

/**
 *
 *
 */
public class Tokenise {

    private String data;

    public Tokenise(String data) {
        this.data = data;
    }

    public String next(String delim) {
        String newicc = data;
        String icc = "";
        int index = newicc.indexOf(delim);
        // System.out.println("wwwwwwwwwwwwwwwwwwwww: "+iccdata);
        if (index > -1) {
            icc = newicc.substring(0, index);
            this.data = newicc.substring(index + 1);
        }
        return icc;
    }

    public boolean hasNext(String delim) {
        return data.indexOf(delim) > -1;
    }
}
