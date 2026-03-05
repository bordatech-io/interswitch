/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.beans;

/**
 *
 * 
 */
public class AccountEntry {

    char accoutType = ' ';
    String amountType = "";
    char amountSign = '+';
    String amount = "";
    String amountCurrency = "";

    public AccountEntry(String entry) {
        this.accoutType = entry.charAt(0);
        this.amountType = entry.substring(2, 4);
        this.amountSign = (entry.charAt(7) == 'D' ? '-' : '+');
        this.amount = entry.substring(8, 20);
        this.amountCurrency = entry.substring(4, 7);
    }

    public String getAmountCurrency() {
        return amountCurrency;
    }

    public char getAccoutType() {
        return accoutType;
    }

    public String getAmount() {
        return amount;
    }

    public char getAmountSign() {
        return amountSign;
    }

    public String getAmountType() {
        return amountType;
    }
}
