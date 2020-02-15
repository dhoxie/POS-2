/*
 * MIT License
 *
 * Copyright (c) 2018 Mykhailo Kyrychenko <mykhailo.kyrychenko@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.scottsdaleair.data.generators;

/**
 * Exception thrown during validation of VIN in the {@link VinValidatorUtils}.
 */
public class InvalidVinException extends Exception {

  private static final long serialVersionUID = -5782168254480270182L;
  private final String wrongVin;

  public InvalidVinException(final String wrongVin) {
    this.wrongVin = wrongVin;
  }

  /**
   * Thrown if the vin is invalid.
   * @param wrongVin  The VIN that is invalid
   * @param message   An additional error message
   */
  public InvalidVinException(final String wrongVin, final String message) {
    super(message);

    this.wrongVin = wrongVin;
  }

  /**
   * Thrown if the vin is invalid.
   * @param wrongVin  The VIN that is invalid
   * @param message   An Additional error message
   * @param cause     The cause of the exception
   */
  public InvalidVinException(final String wrongVin, final String message, final Throwable cause) {
    super(message, cause);

    this.wrongVin = wrongVin;
  }

  /**
   * Thrown if the vin is invalid.
   * @param wrongVin  The VIN that is invalid
   * @param cause     The cause of the exception
   */
  public InvalidVinException(final String wrongVin, final Throwable cause) {
    super(cause);

    this.wrongVin = wrongVin;
  }

  public String getWrongVin() {
    return this.wrongVin;
  }
}