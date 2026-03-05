/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.beans;

import java.io.Serializable;

/**
 *
 *
 */
public class Bitmap implements Serializable {

    public byte bitmap1 = '\0';
    public byte bitmap2 = '\0';
    public byte bitmap3 = '\0';
    public byte bitmap4 = '\0';
    public byte bitmap5 = '\0';
    public byte bitmap6 = '\0';
    public byte bitmap7 = '\0';
    public byte bitmap8 = '\0';
    public byte bitmap9 = '\0';
    public byte bitmap10 = '\0';
    public byte bitmap11 = '\0';
    public byte bitmap12 = '\0';
    public byte bitmap13 = '\0';
    public byte bitmap14 = '\0';
    public byte bitmap15 = '\0';
    public byte bitmap16 = '\0';
    public byte bitmapsb1 = '\0';
    public byte bitmapsb2 = '\0';
    public byte bitmapsb3 = '\0';
    public byte bitmapsb4 = '\0';
    public byte bitmapsb5 = '\0';
    public byte bitmapsb6 = '\0';
    public byte bitmapsb7 = '\0';
    public byte bitmapsb8 = '\0';

    public String setBitmapStr(int length) {
        String bitmap_buffer = "";

        for (int i = 0; i < length; i++) {
            switch (i) {
                case 0: {
                    if (this.bitmap1 != '\0') {
                        bitmap_buffer += (char) this.bitmap1;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap2 != '\0') {
                        bitmap_buffer += (char) this.bitmap2;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap3 != '\0') {
                        bitmap_buffer += (char) this.bitmap3;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap4 != '\0') {
                        bitmap_buffer += (char) this.bitmap4;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap5 != '\0') {
                        bitmap_buffer += (char) this.bitmap5;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap6 != '\0') {
                        bitmap_buffer += (char) this.bitmap6;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap7 != '\0') {
                        bitmap_buffer += (char) this.bitmap7;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap8 != '\0') {
                        bitmap_buffer += (char) this.bitmap8;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }
                    break;
                }
                case 1: {
                    if (this.bitmap9 != '\0') {
                        bitmap_buffer += (char) this.bitmap9;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap10 != '\0') {
                        bitmap_buffer += (char) this.bitmap10;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap11 != '\0') {
                        bitmap_buffer += (char) this.bitmap11;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap12 != '\0') {
                        bitmap_buffer += (char) this.bitmap12;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap13 != '\0') {
                        bitmap_buffer += (char) this.bitmap13;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap14 != '\0') {
                        bitmap_buffer += (char) this.bitmap14;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap15 != '\0') {
                        bitmap_buffer += (char) this.bitmap15;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap16 != '\0') {
                        bitmap_buffer += (char) this.bitmap16;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }
                    break;
                }
            }//end of for statement
        }
        return bitmap_buffer;
    }

    public String setBitmapsbStr(int length) {
        String bitmap_buffer = "";

        for (int i = 0; i < length; i++) {
            switch (i) {
                case 0: {
                    if (this.bitmapsb1 != '\0') {
                        bitmap_buffer += (char) this.bitmapsb1;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmapsb2 != '\0') {
                        bitmap_buffer += (char) this.bitmapsb2;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmapsb3 != '\0') {
                        bitmap_buffer += (char) this.bitmapsb3;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmapsb4 != '\0') {
                        bitmap_buffer += (char) this.bitmapsb4;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmapsb5 != '\0') {
                        bitmap_buffer += (char) this.bitmapsb5;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmapsb6 != '\0') {
                        bitmap_buffer += (char) this.bitmapsb6;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmapsb7 != '\0') {
                        bitmap_buffer += (char) this.bitmapsb7;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmapsb8 != '\0') {
                        bitmap_buffer += (char) this.bitmapsb8;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }
                    break;
                }
            }//end of for statement
        }
        return bitmap_buffer;
    }

    public void setBitmap(int position) {
        int byte_position = position % 8;
        byte[] fixer = {1, -128, 64, 32, 16, 8, 4, 2};
        position = position / 8;
        if (byte_position == 0) {
            position--;
        }
        switch (position) {
            case 0: {
                this.bitmap1 |= fixer[byte_position];
                break;
            }
            case 1: {
                this.bitmap2 |= fixer[byte_position];
                break;
            }
            case 2: {
                this.bitmap3 |= fixer[byte_position];
                break;
            }
            case 3: {
                this.bitmap4 |= fixer[byte_position];
                break;
            }
            case 4: {
                this.bitmap5 |= fixer[byte_position];
                break;
            }
            case 5: {
                this.bitmap6 |= fixer[byte_position];
                break;
            }
            case 6: {
                this.bitmap7 |= fixer[byte_position];
                break;
            }
            case 7: {
                this.bitmap8 |= fixer[byte_position];
                break;
            }
            case 8: {
                this.bitmap9 |= fixer[byte_position];
                break;
            }
            case 9: {
                this.bitmap10 |= fixer[byte_position];
                break;
            }
            case 10: {
                this.bitmap11 |= fixer[byte_position];
                break;
            }
            case 11: {
                this.bitmap12 |= fixer[byte_position];
                break;
            }
            case 12: {
                this.bitmap13 |= fixer[byte_position];
                break;
            }
            case 13: {
                this.bitmap14 |= fixer[byte_position];
                break;
            }
            case 14: {
                this.bitmap15 |= fixer[byte_position];
                break;
            }
            case 15: {
                this.bitmap16 |= fixer[byte_position];
                break;
            }

        }
        //bitmap_buffer[position]=bitmap_buffer[position]|fixer;

    }

    public void setBitmapsb(int position) {
        int byte_position = position % 8;
        byte[] fixer = {1, -128, 64, 32, 16, 8, 4, 2};
        position = position / 8;
        if (byte_position == 0) {
            position--;
        }
        switch (position) {
            case 0: {
                this.bitmapsb1 |= fixer[byte_position];
                break;
            }
            case 1: {
                this.bitmapsb2 |= fixer[byte_position];
                break;
            }
            case 2: {
                this.bitmapsb3 |= fixer[byte_position];
                break;
            }
            case 3: {
                this.bitmapsb4 |= fixer[byte_position];
                break;
            }
            case 4: {
                this.bitmapsb5 |= fixer[byte_position];
                break;
            }
            case 5: {
                this.bitmapsb6 |= fixer[byte_position];
                break;
            }
            case 6: {
                this.bitmapsb7 |= fixer[byte_position];
                break;
            }
            case 7: {
                this.bitmapsb8 |= fixer[byte_position];
                break;
            }

        }
        //bitmap_buffer[position]=bitmap_buffer[position]|fixer;

    }

    public byte[] toBCD(String hexString) {
        byte bcd[] = new byte[hexString.length() / 2];
        String hex = new String("0123456789ABCDEF");
        for (int i = 0, j = 0; i < hexString.length(); i++, j++) {
            byte firstQuadByte = (byte) (hex.indexOf(hexString.toUpperCase().charAt(i)) << 4);
            byte secondQuadByte = (byte) (hex.indexOf(hexString.toUpperCase().charAt(++i)));
            bcd[j] = (byte) (firstQuadByte | secondQuadByte);
        }
        return bcd;
    }

    public String toAscii(String toAsciiData) {
        String hexString = "";
        char hexChars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        int i = 0;
        while (toAsciiData.length() > i) {

            int hbits = (toAsciiData.charAt(i) & 0x00f0) >>> 4;
            int lbits = (toAsciiData.charAt(i) & 0x000f);

            hexString += hexChars[hbits];

            hexString += hexChars[lbits];
            i++;
        }
        return hexString;
    }

}
