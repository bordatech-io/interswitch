/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 */
public class ISOLogger {

    private static final Logger log = LoggerFactory.getLogger(ISOLogger.class);
    String level = "";

    public ISOLogger() {

    }

    public ISOLogger(String level) {
        this.level = level;
    }

    public static void generateLog1(String[] args) {
        String formats = "";
        for (int i = 0; i < args.length; i++) {
            formats += "   " + args[i];
        }
        log.info(formats);

    }

    public static void generateLog2(String[] args, Throwable t) {
        String formats = "";
        for (int i = 0; i < args.length; i++) {
            formats += "   " + args[i];
        }
        log.info(formats, t);

    }

    public static void generateLog2(String[] args) {
        String formats = "";
        for (int i = 0; i < args.length; i++) {
            formats += "   " + args[i];
        }
        log.info(formats);

    }

    public void generateLog2(String args) {
        log.info(args);

    }

    public void generateLog(int req, int position, int fixed, int length, String value, char datatype) {
        String format = "[%1$-10s%2$-5s%3$-10d%4$5d]%5$10s [%6$-40s]";
        String set = "";
        String type = "";
        switch (fixed) {
            case 1: {
                set = "Fixed";
                break;
            }
            case 0: {
                set = "LLVAR";
                //check if the length value had been appended
                int b = Integer.toString(req).length();
                int len = 0;
                boolean validNumber = false;
                try {
                    if (value.length() >= b) {
                        len = Integer.parseInt(value.substring(0, b));
                    }
                    validNumber = true;
                } catch (RuntimeException ee) {
                    validNumber = false;
                }
                if (validNumber && (length - b) == len) {
                    value = value.substring(b, length);
                    length -= b;
                }
                break;
            }
        }

        switch (datatype) {
            case 'n': {
                type = "n";
                break;
            }
            case 'a': {
                type = "a";
                break;
            }
            case 'x': {
                type = "x+n";
                break;
            }
            case 's': {
                type = "ans";
                break;
            }
            case 'b': {
                type = "b";
                break;
            }
            case 'p': {
                type = "an";
                break;
            }
        }
        String levelposition = level.concat(Integer.toString(position));
        String formats = String.format(format, set, type, req, length, levelposition, value);
        log.info(formats);
    }

    public void generateLog(String req, int position, int fixed, int length, String value, char datatype) {
        String format = "[%1$-10s%2$-5s%3$-10d%4$5d]%5$10s [%6$-40s]";
        String set = "";
        String type = "";
        switch (fixed) {
            case 1: {
                set = "Fixed";
                break;
            }
            case 0: {
                set = "LLVAR";
                //check if the length value had been appended
                int b = (req).length();
                int len = 0;
                boolean validNumber = false;
                try {
                    if (value.length() >= b) {
                        len = Integer.parseInt(value.substring(0, b));
                    }
                    validNumber = true;
                } catch (RuntimeException ee) {
                    validNumber = false;
                }
                if (validNumber && (length - b) == len) {
                    value = value.substring(b, length);
                    length -= b;
                }
                break;
            }
        }

        switch (datatype) {
            case 'n': {
                type = "n";
                break;
            }
            case 'a': {
                type = "a";
                break;
            }
            case 'x': {
                type = "x+n";
                break;
            }
            case 's': {
                type = "ans";
                break;
            }
            case 'b': {
                type = "b";
                break;
            }
            case 'p': {
                type = "an";
                break;
            }
        }
        String levelposition = level.concat(Integer.toString(position));
        String formats = String.format(format, set, type, Integer.parseInt(req), length, levelposition, value);
        log.info(formats);
    }
}
