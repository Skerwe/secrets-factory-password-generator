/*
  Secrets Factory - Generate passwords and passphrases.
  Copyright (C) 2019  Quintin Henn

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package za.web.skerwe.factory.secrets;

import java.util.Random;

/**
 * Generates a non cofigurable 12 lenght password.
 * Utilising <code>java.util.Random</code> to select a
 * random letter from a list of allowed characters.
 *
 * @author Quintin Henn
 * @since 16.11.2019
 * @version 30.11.2019
 */
public class JavaRandomGenerator {

  /** Special character codes for: !, #, $, %, &, (, ), *, @, [, ], ^,{, }. */
  private static final char[] specialCharacters = {
      33, 35, 36, 37, 38, 40, 41, 42, 64, 91, 93, 94, 123, 125 };

  private static Random random = new Random();

  /**
   * Generates a 12 lenght string of random characters.
   *
   * @return generates a password using the basic configuration
   */
  public String generatePassword() {

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 12; i++) {
      builder.append(getNextValue());
    }
    return builder.toString();
  }

  private char getNextValue() {
    char nextChar;
    switch (random.nextInt(4)) {
      case 0:
        nextChar = getLowerLetter();
        break;
      case 1:
        nextChar = getUpperLetter();
        break;
      case 2:
        nextChar = getNumber();
        break;
      case 3:
        nextChar = getSpecial();
        break;
      default:
        nextChar = Character.MIN_VALUE;
    }
    return nextChar;
  }

  private char getLowerLetter() {
    return (char) (random.nextInt(26) + 'a');
  }

  private char getUpperLetter() {
    return (char) (random.nextInt(26) + 'A');
  }

  private char getNumber() {
    return (char) (random.nextInt(10) + 48);
  }

  private char getSpecial() {
    return specialCharacters[random.nextInt(specialCharacters.length)];
  }
}
