/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.support;

/**
 *
 *
 */
public interface ResponseCode {

    public static final char HSM_OK = '0';
    public static final char HSM_ERROR = '1';

    public static final int TRAN_TYPE_PURCHASE = 1;
    public static final int TRAN_TYPE_PAYATTITUDE = 2;
    //public static final int TRAN_TYPE_PURCASH = 2;
    public static final int TRAN_TYPE_CASHBACK = 3;
    public static final int TRAN_TYPE_REVERSAL = 4;
    public static final int TRAN_TYPE_REFUND = 5;
    public static final int TRAN_TYPE_AUTH = 6;
    public static final int TRAN_TYPE_BALANCE = 7;
    public static final int TRAN_TYPE_PINCHANGE = 8;
    public static final int TRAN_TYPE_MINISTATE = 9;
    public static final int TRAN_TYPE_TRANSFER = 10;
    public static final int TRAN_TYPE_DEPOSIT = 11;
    public static final int TRAN_TYPE_ROLLBACK = 12;
    public static final int TRAN_TYPE_VALUEADD_VOUCHER = 13;
    public static final int TRAN_TYPE_PANCHECK = 14;
    public static final int TRAN_TYPE_VALUEADD_RECHARGE = 15;
    public static final int TRAN_TYPE_LINKDEPOSIT = 23;
    public static final int TRAN_TYPE_LINKACCOUNT = 22;
    public static final int TRAN_TYPE_SERVICEPAYMENT = 25;
    public static final int TRAN_TYPE_AGENTDEPOSIT = 30;
    public static final int TRAN_TYPE_AGENT_LINKDEPOSIT = 31;

    public static final int TRAN_TYPE_AGENTREVERSAL = 36;
    public static final int TRAN_TYPE_TAMSACCT_TRANSFER = 37;
    

    public static final int TRAN_TYPE_PAYMENT1 = 38;
    
    public static final int TRAN_TYPE_BILL_PAYMENT = 39;
    
    public static final int TRAN_TYPE_PAYMENT = 40;

    public static final int TXN_PENDING = 0;
    public static final int TXN_APPROVED = 1;
    public static final int TXN_DECLINED = 2;
    public static final int TXN_RECONSILED = 3;
    public static final int TXN_ROLLBACK = 4;

    public final static int STATUS_OK = 0;
    public final static int STATUS_INVALIDTERMINAL = 300;
    public final static int STATUS_INVALIDBATCH = 301;
    public final static int STATUS_TRANNOTFOUND = 302;
    public final static int STATUS_TXNEXIST = 303;
    public final static int STATUS_NOSWITCH = 304;
    public final static int STATUS_INVALIDMERCHANT = 305;
    public final static int STATUS_INVALIDAUTHCODE = 306;
    public final static int STATUS_SYSUNAVAIL = 307;
    public final static int STATUS_VALIDATION = 308;
    public final static int STATUS_INVALIDAMOUNT = 309;
    public final static int STATUS_INVALIDCARD = 310;
    public final static int STATUS_PICKUP = 311;
    public final static int STATUS_WRONGCARDTYPE = 312;
    public final static int STATUS_INVALIDACCOUNT = 313;
    public final static int STATUS_INSUFFIENCTFUNDS = 314;
    public final static int STATUS_EXPIRED = 315;
    public final static int STATUS_EXCEEDSLIMIT = 316;
    public final static int STATUS_PINFAULT = 317;
    public final static int STATUS_TXNMISMATCH = 318;
    public final static int STATUS_HOTCARD = 319;
    public final static int STATUS_MANUALNOTALLOWED = 320;
    public final static int STATUS_REFUNDNOTALLOWED = 321;
    public final static int STATUS_REVERSALNOTALLOWED = 322;
    public final static int STATUS_BUDGETNOTALLOWED = 323;
    public final static int STATUS_CASHBACKNOTALLOWED = 324;
    public final static int STATUS_AUTHOVERRIDENOTALLOWED = 325;
    public final static int STATUS_AUTHONLYNOTALLOWED = 326;
    public final static int STATUS_PINCHANGE_FAILED = 327;
    public final static int STATUS_ALREADY_REVERSED = 328;
    public final static int STATUS_NO_OPEN_BATCH = 329;
    public final static int STATUS_CURRENCY_ERROR = 330;
    public final static int STATUS_HSM_ERROR = 331;
    public final static int STATUS_DUKPT_SERIAL_ERROR = 333;
//334,335,336,337,338,339,340
    public final static int STATUS_NO_ROUTE = 341;
    public final static int STATUS_UNKNOWN = 399;
    public final static int STATUS_SYSTEM_MALFUNCTION = 400;
    public final static int STATUS_EXCEPTION = 401;
}
