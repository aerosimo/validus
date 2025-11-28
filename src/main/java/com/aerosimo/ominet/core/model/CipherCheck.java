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

    public static CardType resolve(String number) {
        if (!number.matches("\\d+")) return CardType.UNKNOWN;

        int length = number.length();
        String digit1 = number.substring(0, 1);
        String digit2 = number.substring(0, 2);
        String digit3 = number.substring(0, 3);
        String digit4 = number.substring(0, 4);
        String digit6 = number.substring(0, 6);

        // VISA ELECTRON
        if (length == 16 &&
                (digit4.equals("4026") || digit4.equals("4175") || digit4.equals("4405") ||
                        digit4.equals("4508") || digit4.equals("4844") || digit4.equals("4913") ||
                        digit4.equals("4917"))) {
            log.info("Card type is VISA");
            return CardType.VISA_ELECTRON;
        }

        // VISA
        if (digit1.equals("4") && length >= 13 && length <= 19) {
            log.info("Card type is VISA");
            return CardType.VISA;
        }

        // AMEX
        if (length == 15 && (digit2.equals("34") || digit2.equals("37"))) {
            log.info("Card type is AMEX");
            return CardType.AMEX;
        }

        // MASTERCARD (51–55)
        int d2 = Integer.parseInt(digit2);
        if (length == 16 && d2 >= 51 && d2 <= 55) {
            log.info("Card type is MASTERCARD");
            return CardType.MASTERCARD;
        }

        // DISCOVER
        int d3 = Integer.parseInt(digit3);
        int d6 = Integer.parseInt(digit6);
        if (length == 16 &&
                (digit4.equals("6011") ||
                        digit2.equals("65") ||
                        (d3 >= 644 && d3 <= 649) ||
                        (d6 >= 622126 && d6 <= 622925))) {
            log.info("Card type is DISCOVER");
            return CardType.DISCOVER;
        }

        // JCB
        int d4 = Integer.parseInt(digit4);
        if (length == 16 && (d4 >= 3528 && d4 <= 3589)) {
            log.info("Card type is JCB");
            return CardType.JCB;
        }

        // DINERS CLUB (300–305, 36, 38)
        if (length == 14 &&
                ((d4 >= 3000 && d4 <= 3059) ||
                        digit2.equals("36") || digit2.equals("38"))) {
            log.info("Card type is DINERSCLUB");
            return CardType.DINERS_CLUB;
        }

        // UNIONPAY 622126–622925
        if (d6 >= 622126 && d6 <= 622925 && length >= 16 && length <= 19) {
            log.info("Card type is UNIONPAY");
            return CardType.UNIONPAY;
        }
        log.info("Card type is UNKNOWN");
        return CardType.UNKNOWN;
    }
}