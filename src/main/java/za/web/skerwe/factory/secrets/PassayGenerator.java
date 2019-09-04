package za.web.skerwe.factory.secrets;

import org.passay.PasswordGenerator;
import org.passay.CharacterData;
import org.passay.EnglishCharacterData;
import org.passay.CharacterRule;
import org.passay.CharacterData;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Quintin henn
 * @since 03.09.2019
 * @version 04.09.2019
 */
public class PassayGenerator {

  public static final int DEFAULT_LENGTH = 12;
  public static final int DEFAULT_LOWER_CASE = 2;
  public static final int DEFAULT_UPPER_CASE = 2;
  public static final int DEFAULT_DIGITS = 2;
  public static final int DEFAULT_SPECIALS = 2;

  private static PassayGenerator passwordGenerator;

  private PassayGenerator() {}

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

    return this.generator.generatePassword(length, rules);
  }

  public void useDefaultConfiguration() {
    this.configureLowerCase(DEFAULT_LOWER_CASE);
    this.setUseLowerCase(true);

    this.configureUpperCase(DEFAULT_UPPER_CASE);
    this.setUseUpperCase(true);

    this.configureDigits(DEFAULT_DIGITS);
    this.setUseDigits(true);

    this.configureSpecials(DEFAULT_SPECIALS);
    this.setUseSpecials(true);

    this.setSecretLength(10);
  }

  public void configureLowerCase(int lowerCase) {
    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    this.lowerCaseRule = new CharacterRule(lowerCaseChars);
    this.lowerCaseRule.setNumberOfCharacters(lowerCase);
  }

  public void configureUpperCase(int upperCase) {
    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    this.upperCaseRule = new CharacterRule(upperCaseChars);
    this.upperCaseRule.setNumberOfCharacters(upperCase);
  }

  public void configureDigits(int digits) {
    CharacterData digitChars = EnglishCharacterData.Digit;
    this.digitRule = new CharacterRule(digitChars);
    this.digitRule.setNumberOfCharacters(digits);
  }

  public void configureSpecials(int specials) {
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

  public void setSecretLength(int length) {
    this.length = length;
  }

  public void setUseLowerCase(boolean useLowerCase) {
    this.useLowerCase = useLowerCase;
  }

  public void setUseUpperCase(boolean useUpperCase) {
    this.useUpperCase = useUpperCase;
  }

  public void setUseDigits(boolean useDigits) {
    this.useDigits = useDigits;
  }

  public void setUseSpecials(boolean useSpecials) {
    this.useSpecials = useSpecials;
  }

  public void resetConfiguration() {
    this.useLowerCase = false;
    this.useUpperCase = false;
    this.useDigits = false;
    this.useSpecials = false;
  }
}
