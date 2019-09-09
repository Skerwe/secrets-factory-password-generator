package za.web.skerwe.factory;

import org.junit.Test;

public class SecretsFactoryTest {

  @Test
  public void testSecretsFactoryDefault() {
    SecretsFactory.main(new String[]{});
  }

  @Test
  public void testSecretsFactoryFull() {
    SecretsFactory.main(new String[]{"-l", "4", "-u", "3", "-d", "3", "-s", "2"});
  }

  @Test
  public void testSecretsFactoryPartial() {
    SecretsFactory.main(new String[]{"-l", "4", "-u", "4"});
  }

  // @Test
  // public void testSecretsFactoryHelp() {
  //   SecretsFactory.main(new String[]{"help"});
  // }

  // @Test
  // public void testSecretsFactoryVersion() {
  //   SecretsFactory.main(new String[]{"version"});
  // }
}
