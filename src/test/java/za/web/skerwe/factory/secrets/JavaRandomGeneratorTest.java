package za.web.skerwe.factory.secrets;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JavaRandomGeneratorTest {

  @Test
  public void testGeneratePassword() {
    JavaRandomGenerator generator = new JavaRandomGenerator();
    String password = generator.generatePassword();
    assertThat(password, is(notNullValue()));
    assertThat(password.length(), is(equalTo(12)));
  }
}
