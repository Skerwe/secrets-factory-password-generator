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

import java.util.ArrayList;
import java.util.List;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;

import org.passay.PasswordGenerator;

/**
 * Uses password policy for Java (Passay) to generate sectrets.
 *
 * @author Quintin Henn
 * @since 03.09.2019
 * @version 19.08.2021
 */
public final class PassayGenerator {

  /** The default suggested password length. */
  public static final int DEFAULT_LENGTH = 12;

  /** The default lowercase characters count in a password. */
  public static final int DEFAULT_LOWER_CASE = 4;

  /** The default uppercase characters count in a password. */
  public static final int DEFAULT_UPPER_CASE = 4;

  /** The default amount of numbers in a password. */
  public static final int DEFAULT_DIGITS = 2;

  /** The default special characters count in a password. */
  public static final int DEFAULT_SPECIALS = 2;

  private static PassayGenerator passwordGenerator;

  private PassayGenerator() {}

  /**
   * Obtain an instance of the singleton <code>PassayGenerator</code>.
   * @return instance of the password generator that implements Passay
   */
  public static PassayGenerator getInstance() {
    if (passwordGenerator == null) {
      passwordGenerator = new PassayGenerator();
    }
    return passwordGenerator;
  }

  private PasswordGenerator generator = new PasswordGenerator();

  private CharacterRule lowerCaseRule;
  private CharacterRule upperCaseRule;
  private CharacterRule digitRule;
  private CharacterRule splCharRule;

  private int length;

  private boolean useLowerCase;
  private boolean useUpperCase;
  private boolean useDigits;
  private boolean useSpecials;

  /**
   * Generate a password of the configured length,
   * amount of lowercase, uppercase, special characters and numbers.
   * @return the generated password
   */
  public String generate() {

    List<CharacterRule> rules = new ArrayList<>();
    if (useLowerCase) {
      rules.add(lowerCaseRule);
    }
    if (useUpperCase) {
      rules.add(upperCaseRule);
    }
    if (useDigits) {
      rules.add(digitRule);
    }
    if (useSpecials) {
      rules.add(splCharRule);
    }

    if (this.length == 0) {
      this.length = this.lowerCaseRule.getNumberOfCharacters()
        + this.upperCaseRule.getNumberOfCharacters()
        + this.digitRule.getNumberOfCharacters()
        + this.splCharRule.getNumberOfCharacters();
    }

    return this.generator.generatePassword(length, rules);
  }

  /**
   * Reset all configurations to their default values.
   */
  public void useDefaultConfiguration() {
    this.configureLowerCase(DEFAULT_LOWER_CASE);
    this.setUseLowerCase(true);

    this.configureUpperCase(DEFAULT_UPPER_CASE);
    this.setUseUpperCase(true);

    this.configureDigits(DEFAULT_DIGITS);
    this.setUseDigits(true);

    this.configureSpecials(DEFAULT_SPECIALS);
    this.setUseSpecials(true);

    this.setSecretLength(DEFAULT_LENGTH);
  }

  /**
   * Configure the amount of lowercase characters in this password.
   * @param lowerCase the lowercase character count
   */
  public void configureLowerCase(final int lowerCase) {
    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    this.lowerCaseRule = new CharacterRule(lowerCaseChars);
    this.lowerCaseRule.setNumberOfCharacters(lowerCase);
  }

  /**
   * Configure the amount of uppercase characters in this password.
   * @param upperCase the uppercase character count
   */
  public void configureUpperCase(final int upperCase) {
    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    this.upperCaseRule = new CharacterRule(upperCaseChars);
    this.upperCaseRule.setNumberOfCharacters(upperCase);
  }

  /**
   * Configure the amount of numbers in this password.
   * @param digits the numbers count
   */
  public void configureDigits(final int digits) {
    CharacterData digitChars = EnglishCharacterData.Digit;
    this.digitRule = new CharacterRule(digitChars);
    this.digitRule.setNumberOfCharacters(digits);
  }

  /**
   * Configure the amount of special characters in this password.
   * @param specials the special characters count
   */
  public void configureSpecials(final int specials) {
    CharacterData specialChars = new CharacterData() {
      public String getErrorCode() {
          return "Password contains invalid special character!";
      }

      public String getCharacters() {
          return "!@#$%^&*()_+";
      }
    };
    this.splCharRule = new CharacterRule(specialChars);
    this.splCharRule.setNumberOfCharacters(specials);
  }

  /**
   * Set the length of the password string.
   * @param length the password length
   */
  public void setSecretLength(int length) {
    this.length = length;
  }

  /**
   * Set the use of lowercase characters in the password.
   * @param useLowerCase should lowercase characters be used
   */
  public void setUseLowerCase(boolean useLowerCase) {
    this.useLowerCase = useLowerCase;
  }

  /**
   * Set the use of uppercase characters in the password.
   * @param useUpperCase should uppercase characters be used
   */
  public void setUseUpperCase(boolean useUpperCase) {
    this.useUpperCase = useUpperCase;
  }

  /**
   * Set the use of numbers in the password.
   * @param useDigits should numbers be used
   */
  public void setUseDigits(boolean useDigits) {
    this.useDigits = useDigits;
  }

  /**
   * Set the use of special characters in the password.
   * @param useSpecials should special characters be used
   */
  public void setUseSpecials(boolean useSpecials) {
    this.useSpecials = useSpecials;
  }

  /**
   * Reset the use of lowercase, uppercase, special characters and numbers.
   */
  public void resetConfiguration() {
    this.useLowerCase = false;
    this.useUpperCase = false;
    this.useDigits = false;
    this.useSpecials = false;
  }
}
