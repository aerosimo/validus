/******************************************************************************
 * This piece of work is to enhance validus project functionality.            *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      AuthLayer.java                                       *
 * Created:   28/11/2025, 22:21                                               *
 * Modified:  28/11/2025, 22:21                                               *
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

import com.aerosimo.ominet.dao.impl.CardResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthLayer {

    private static final Logger log = LogManager.getLogger(AuthLayer.class);

    public static CardResponseDTO validate(String number) {
        if (number == null || number.isEmpty()) {
            log.info("Card number is null or empty");
            return new CardResponseDTO("false", "Unknown", "Card number is empty");
        }
        if (number.length() < 13 || number.length() > 19) {
            log.info("Card number is too short or too long");
            return new CardResponseDTO("false", "Unknown", "Card number is too short or too long");
        }
        boolean valid = LuhnWatch.isValid(number);
        CardType type = CipherCheck.resolve(number);
        return new CardResponseDTO(
                String.valueOf(valid),
                type.display,
                valid ? "Card is valid" : "Card number is not valid"
        );
    }
}