package za.web.skerwe.factory;

import za.web.skerwe.factory.secrets.PassayGenerator;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.HelpFormatter;

import org.apache.commons.cli.ParseException;

/**
 * @author Quintin henn
 * @since 04.09.2019
 * @version 04.09.2019
 */
public class SecretsFactory {

  public static void main(String[] args) {

    PassayGenerator generator = PassayGenerator.getInstance();
    generator.setSecretLength(PassayGenerator.DEFAULT_LENGTH);

    if (args.length == 0) {
      generator.useDefaultConfiguration();
    } else {
      try {
        generator.resetConfiguration();
        
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(getOptions(), args);
  
        if (cmd.hasOption("help")) {
          HelpFormatter formatter = new HelpFormatter();
          formatter.printHelp("ant", getOptions());
        }
        if (cmd.hasOption("version")) {
        }
  
        if (cmd.hasOption("l")) {
          generator.configureLowerCase(Integer.valueOf(cmd.getOptionValue("l")));
          generator.setUseLowerCase(true);
        }
        if (cmd.hasOption("u")) {
          generator.configureUpperCase(Integer.valueOf(cmd.getOptionValue("u")));
          generator.setUseUpperCase(true);
        }
        if (cmd.hasOption("d")) {
          generator.configureDigits(Integer.valueOf(cmd.getOptionValue("d")));
          generator.setUseDigits(true);
        }
        if (cmd.hasOption("s")) {
          generator.configureSpecials(Integer.valueOf(cmd.getOptionValue("s")));
          generator.setUseSpecials(true);
        }
      } catch (ParseException | NumberFormatException e) {
        System.err.println("Parsing failed. Reason: " + e.getMessage());
        System.exit(1);
      }
    }

    System.out.printf("\n\nGenerated secret: %s\n\n", generator.generate());
  }

  private static Options getOptions() {
    Option help = new Option("help", "print this message");
    Option version = new Option("version", "print the version information and exit");

    Option lowerCase = OptionBuilder
      .withArgName("value")
      .hasArg()
      .withDescription("number of lower case characters")
      .create("l");
    
    Option upperCase = OptionBuilder
      .withArgName("value")
      .hasArg()
      .withDescription("number of upper case character")
      .create("u");
    
    Option digits = OptionBuilder
      .withArgName("value")
      .hasArg()
      .withDescription("numbers of digits")
      .create("d");
    
    Option specials = OptionBuilder
      .withArgName("value")
      .hasArg()
      .withDescription("number of special characters")
      .create("s");

    Options options = new Options();
    options.addOption(help);
    options.addOption(version);
    options.addOption(lowerCase);
    options.addOption(upperCase);
    options.addOption(digits);
    options.addOption(specials);

    return options;
  }
}
