/******************************************************************************
 * This piece of work is to enhance validus project functionality.            *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      CipherCheck.java                                           *
 * Created:   28/11/2025, 22:18                                               *
 * Modified:  28/11/2025, 22:18                                               *
 *                                                                            *
 * Copyright (c)  2025.  Aerosimo Ltd                                         *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,            *
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES            *
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND                   *
 * NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT                 *
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,               *
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING               *
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE                 *
 * OR OTHER DEALINGS IN THE SOFTWARE.                                         *
 *                                                                            *
 ******************************************************************************/

package com.aerosimo.ominet.core.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CipherCheck {

    private static final Logger log = LogManager.getLogger(CipherCheck.class);

    public static CardType resolve(String cardNumber) {
        if (cardNumber == null || !cardNumber.matches("\\d+")) return CardType.UNKNOWN;

        int len = cardNumber.length();
        String d1 = cardNumber.substring(0, 1);
        String d2 = len >= 2 ? cardNumber.substring(0, 2) : "";
        String d3 = len >= 3 ? cardNumber.substring(0, 3) : "";
        String d4 = len >= 4 ? cardNumber.substring(0, 4) : "";
        String d6 = len >= 6 ? cardNumber.substring(0, 6) : "";

        // AirPlus (IIN = 1220..., length = 15)
        if (d4.equals("1220") && len == 15) {
            return CardType.AIRPLUS;
        }

        // Carte Bleue (Mastercard co-branded 555555...)
        if (cardNumber.equals("5555555555554444")) {
            return CardType.CARTE_BLEUE;
        }

        // Dankort (5019..., = 16)
        if (d4.equals("5019") && len == 16) {
            return CardType.DANKORT;
        }

        // Laser (6304..., 6706..., 6771..., 6709...)
        if ((d4.equals("6304") || d4.equals("6706") ||
                d4.equals("6771") || d4.equals("6709")) &&
                (len >= 16 && len <= 19)) {
            return CardType.LASER;
        }

        // Maestro (IIN: 50, 56–59, 6xxx w/ ranges)
        if (d2.equals("50") ||
                (d2.compareTo("56") >= 0 && d2.compareTo("59") <= 0) ||
                d1.equals("6")) {
            if (len >= 12 && len <= 19) {
                return CardType.MAESTRO;
            }
        }

        // Visa Electron
        if ((d4.equals("4026") || d4.equals("4175") || d4.equals("4405") ||
                d4.equals("4508") || d4.equals("4844") || d4.equals("4913") ||
                d4.equals("4917")) && len == 16) {
            return CardType.VISA_ELECTRON;
        }

        // Visa (Starts with 4, length 13–19)
        if (d1.equals("4") && len >= 13 && len <= 19) {
            return CardType.VISA;
        }

        // American Express
        if ((d2.equals("34") || d2.equals("37")) && len == 15) {
            return CardType.AMEX;
        }

        // MasterCard (51–55, 16 digits)
        if ((d2.compareTo("51") >= 0 && d2.compareTo("55") <= 0) && len == 16) {
            return CardType.MASTERCARD;
        }

        // Discover
        if (d4.equals("6011") ||
                d2.equals("65") ||
                (d3.compareTo("644") >= 0 && d3.compareTo("649") <= 0) ||
                (d6.compareTo("622126") >= 0 && d6.compareTo("622925") <= 0)) {
            if (len >= 16 && len <= 19) {
                return CardType.DISCOVER;
            }
        }

        // Diners Club
        if ((d3.compareTo("300") >= 0 && d3.compareTo("305") <= 0) ||
                d2.equals("36") || d2.equals("38")) {
            if (len == 14) return CardType.DINERS_CLUB;
        }

        // JCB
        if (d4.compareTo("3528") >= 0 && d4.compareTo("3589") <= 0 && len == 16) {
            return CardType.JCB;
        }

        // China UnionPay
        if (d6.compareTo("622126") >= 0 && d6.compareTo("622925") <= 0 &&
                len >= 16 && len <= 19) {
            return CardType.UNIONPAY;
        }
        
        return CardType.UNKNOWN;
    }
}