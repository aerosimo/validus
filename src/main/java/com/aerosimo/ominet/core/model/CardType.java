/******************************************************************************
 * This piece of work is to enhance validus project functionality.            *
 *                                                                            *
 * Author:    eomisore                                                        *
 * File:      CardType.java                                                   *
 * Created:   28/11/2025, 22:17                                               *
 * Modified:  28/11/2025, 22:17                                               *
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

public enum CardType {
    VISA("Visa"),
    VISA_ELECTRON("Visa Electron"),
    MASTERCARD("MasterCard"),
    AMEX("American Express"),
    DISCOVER("Discover"),
    JCB("JCB"),
    DINERS_CLUB("Diners Club"),
    UNIONPAY("China UnionPay"),
    AIRPLUS("AirPlus"),
    CARTE_BLEUE("Carte Bleue"),
    DANKORT("Dankort"),
    LASER("Laser"),
    MAESTRO("Maestro"),
    UNKNOWN("Unknown");

    public final String display;
    CardType(String display) { this.display = display; }
}