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
        if (cardNumber == null || !cardNumber.matches("\\d+"))
            return CardType.UNKNOWN;

        int length = cardNumber.length();

        // Convert first 6 digits to int safely
        int first6 = Integer.parseInt(cardNumber.substring(0, Math.min(6, cardNumber.length())));
        int first4 = Integer.parseInt(cardNumber.substring(0, Math.min(4, cardNumber.length())));
        int first3 = Integer.parseInt(cardNumber.substring(0, Math.min(3, cardNumber.length())));
        int first2 = Integer.parseInt(cardNumber.substring(0, Math.min(2, cardNumber.length())));
        int first1 = Integer.parseInt(cardNumber.substring(0, 1));

        // --- VISA ---
        if (first1 == 4 && (length == 13 || length == 16 || length == 19))
            return CardType.VISA;

        // --- VISA ELECTRON ---
        if ((first4 == 4026 || first6 == 417500 || first4 == 4405 || first4 == 4508 ||
                first4 == 4844 || first4 == 4913 || first4 == 4917) && length == 16)
            return CardType.VISA_ELECTRON;

        // --- MASTERCARD (51–55 or 2221–2720) ---
        if ((first2 >= 51 && first2 <= 55) || (first4 >= 2221 && first4 <= 2720))
            return CardType.MASTERCARD;

        // --- AMERICAN EXPRESS ---
        if ((first2 == 34 || first2 == 37) && length == 15)
            return CardType.AMEX;

        // --- DISCOVER ---
        if (first4 == 6011 || (first2 == 65) ||
                (first3 >= 644 && first3 <= 649) ||
                (first6 >= 622126 && first6 <= 622925))
            return CardType.DISCOVER;

        // --- JCB ---
        if (first4 >= 3528 && first4 <= 3589)
            return CardType.JCB;

        // --- DINERS CLUB ---
        if ((first2 == 36 || first2 == 38 || first2 == 39) && (length == 14 || length == 15))
            return CardType.DINERS_CLUB;

        // --- UNIONPAY (China UnionPay) ---
        if (first1 == 6 && length >= 14 && length <= 19)
            return CardType.UNIONPAY;

        return CardType.UNKNOWN;
    }
}