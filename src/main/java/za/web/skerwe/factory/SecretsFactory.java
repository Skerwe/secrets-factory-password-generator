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

package za.web.skerwe.factory;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import za.web.skerwe.factory.secrets.PassayGenerator;

/**
 * Utility to start the password and passphrase generators.
 *
 * @author Quintin Henn
 * @since 04.09.2019
 * @version 09.04.2022
 */
public class SecretsFactory {

  private static final String VALUE = "value";
  private static final String VERSION = "\nVersion 0.1.1 (09-04-2022)";

  private SecretsFactory() {
  }

  /**
   * Generate passwords and passphrases based on command line arguments
   * Prints version, license, help and useage information.
   *
   * @param args command line arguments for customizing the password
   */
  public static void main(String[] args) {

    System.out.println(getLicense());

    if (args.length == 0) {
      generateDefaultPassayPassword();
    } else {
      try {

        handleArguments(args);
      } catch (ParseException | NumberFormatException e) {
        System.err.println("Parsing failed. Reason: " + e.getMessage());
        System.exit(1);
      }
    }
  }

  private static void handleArguments(String[] arguments) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(getOptions(), arguments);

    if (cmd.getOptions().length == 0) {
      System.out.printf("%nUnrecognised command(s) %s%n%n", cmd.getArgList().toString());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("sgen [command]", getOptions());
      return;
    }

    if (cmd.hasOption("help")) {
      System.out.println();
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("sgen", getOptions());
      return;
    }
    if (cmd.hasOption("version")) {
      System.out.println(getVersionInfo());
      return;
    }

    if (cmd.hasOption("w")) {
      System.out.println(getWarranty());
      return;
    }
    if (cmd.hasOption("c")) {
      System.out.println(getTermsAndConditions());
      return;
    }

    PassayGenerator generator = PassayGenerator.getInstance();
    generator.resetConfiguration();

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

    System.out.printf("%nGenerated secret: %s%%n", generator.generate());
  }

  private static void generateDefaultPassayPassword() {
    PassayGenerator generator = PassayGenerator.getInstance();
    generator.useDefaultConfiguration();
    System.out.printf("%nGenerated default configured secret: %s%n%n", generator.generate());
  }

  private static Options getOptions() {
    Option help = new Option("help", "print this usage message");
    Option version = new Option("version", "print the version information");
    Option warranty = new Option("w", "print the warranty information");
    Option conditions = new Option("c", "print the terms & conditions");

    Option lowerCase = Option.builder("l")
        .argName(VALUE)
        .hasArg()
        .desc("number of lower case characters")
        .build();

    Option upperCase = Option.builder("u")
        .argName(VALUE)
        .hasArg()
        .desc("number of upper case character")
        .build();

    Option digits = Option.builder("d")
        .argName(VALUE)
        .hasArg()
        .desc("numbers of digits")
        .build();

    Option specials = Option.builder("s")
        .argName(VALUE)
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
    options.addOption(warranty);
    options.addOption(conditions);

    return options;
  }

  private static String getLicense() {
    return "\nSecrets Factory  Copyright (C) 2019  Quintin Henn\n"
        + "This program comes with ABSOLUTELY NO WARRANTY; for details type 'sgen -w'.\n"
        + "This is free software, and you are welcome to redistribute it\n"
        + "under certain conditions; type 'sgen -c' for details.";
  }

  private static String getVersionInfo() {
    return VERSION;
  }

  private static String getWarranty() {
    return "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
        "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
        "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
        "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
        "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
        "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
        "SOFTWARE.";
  }

  private static String getTermsAndConditions() {
    return "%nTerms and Conditions";
  }
}
