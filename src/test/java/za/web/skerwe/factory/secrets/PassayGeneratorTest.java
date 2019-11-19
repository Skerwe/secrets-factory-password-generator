package za.web.skerwe.factory.secrets;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class PassayGeneratorTest {

  @Test 
  public void testGetInstance() { 

    PassayGenerator generator1 = PassayGenerator.getInstance();
    generator1.useDefaultConfiguration();
    assertThat("First generator instance", generator1, not(nullValue()));

    PassayGenerator generator2 = PassayGenerator.getInstance();
    generator2.useDefaultConfiguration();
    assertThat("Second generator instance", generator2, not(nullValue()));
    assertThat("Singleton pattern check failed", generator1, equalTo(generator2)); 
  }

  @Test
  public void testGenerateSpecialCharacters() {

      PassayGenerator generator = PassayGenerator.getInstance();
      generator.useDefaultConfiguration();

      String password = generator.generate();
      int specialCharCount = 0;
      for (char c : password.toCharArray()) {
          if (c >= 33 && c <= 47) {
              specialCharCount++;
          }
      }
      assertThat("Password validation failed", specialCharCount, is(lessThan(3)));
  }

  @Test
  public void testGenerateDuplicates() {

      PassayGenerator generator = PassayGenerator.getInstance();
      generator.useDefaultConfiguration();
      
      String password1 = generator.generate();
      String password2 = generator.generate();
      assertThat("Password validation failed", password1, is(not(password2)));

      String password3 = generator.generate();
      assertThat("Password validation failed", password3, is(not(password1)));
      assertThat("Password validation failed", password3, is(not(password2)));
  }
}
