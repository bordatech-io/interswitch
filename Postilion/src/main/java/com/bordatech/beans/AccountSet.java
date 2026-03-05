/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.beans;

/**
 *
 *
 */
public class AccountSet {

    private String account;
    private String currencyCode;
    private String accountType;
    private String amount;
    private char amountSign;

    public AccountSet(String set) {
        this.account = set.substring(0, 28);
        this.accountType = set.substring(28, 30);
        this.currencyCode = set.substring(30, 33);
        this.amountSign = (set.charAt(33) == 'D' ? '-' : '+');
        this.amount = set.substring(34, 46);
    }

    public String getAccount() {
        return account;
    }

    public String getAccountType() {
        return accountType;
    }

    public char getAmountSign() {
        return amountSign;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

}
