/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.beans;

import com.bordatech.support.Tokenise;
import java.util.Map;

/**
 *
 *
 */
public class AccountStatement {

    private String datetime;
    private String seqno;
    private String amount;
    private String trantype;
    private String fromacct;
    private String toacct;
    private String currcode;
    private String surcharge;
    private String termid;
    private Map<String, Integer> map;

    public AccountStatement(String string) {
        Tokenise tokenise = new Tokenise(string);
        String toke = tokenise.next("~");

    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrcode() {
        return currcode;
    }

    public void setCurrcode(String currcode) {
        this.currcode = currcode;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getFromacct() {
        return fromacct;
    }

    public void setFromacct(String fromacct) {
        this.fromacct = fromacct;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }

    public String getToacct() {
        return toacct;
    }

    public void setToacct(String toacct) {
        this.toacct = toacct;
    }

    public String getTrantype() {
        return trantype;
    }

    public void setTrantype(String trantype) {
        this.trantype = trantype;
    }

}
