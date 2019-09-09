/**
  Secrets factory - a password generator
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

package za.web.skerwe.factory;

import za.web.skerwe.factory.secrets.PassayGenerator;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;

import org.apache.commons.cli.ParseException;

/**
 * @author Quintin henn
 * @since 04.09.2019
 * @version 09.09.2019
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

    Option lowerCase = Option.builder("l")
      .argName("value")
      .hasArg()
      .desc("number of lower case characters")
      .build();

    Option upperCase = Option.builder("u")
      .argName("value")
      .hasArg()
      .desc("number of upper case character")
      .build();

    Option digits = Option.builder("d")
      .argName("value")
      .hasArg()
      .desc("numbers of digits")
      .build();

    Option specials = Option.builder("s")
      .argName("value")
      .hasArg()
      .desc("number of special characters")
      .build();

    Options options = new Options();
    options.addOption(help);
    options.addOption(version);
    options.addOption(lowerCase);
    options.addOption(upperCase);
    options.addOption(digits);
    options.addOption(specials);

    return options;
  }

  private String getLicense() {
    return String "<program>  Copyright (C) <year>  <name of author>
    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
    This is free software, and you are welcome to redistribute it
    under certain conditions; type `show c' for details.";
  }
}
