// This is a SUGGESTED skeleton file.  Throw it away if you want.
package enigma;

import java.io.*;

/** Enigma simulator.
 *  @author
 */
public final class Main {

    // WARNING: Not all methods that have code in them are complete!

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    private static Rotor _firotor;
    private static Rotor _serotor;
    private static Rotor _throtor;
    private static FixedRotor _fixrotor;
    private static Reflector _reflector;
    public static void main(String[] args) {
        boolean isfirst = true;
        Machine M;
        BufferedReader input = null;
        try {
            input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0])));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file found.");
        }

        String outputFilename;
        if (args.length >= 2) {
            outputFilename = args[1];
        } else {
            outputFilename = "output.txt";
        }

        buildRotors();

        M = null;

        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isfirst) {
                    if (isConfigurationLine(line)) {
                        M = new Machine();
                        configure(M, line);
                        isfirst = false;
                    }
                } else {
                    if (issecondConfigurationLine(line)) {
                        M = new Machine();
                        configure(M, line);
                    } else {
                        writeMessageLine(M.convert(standardize(line)),
                                outputFilename);
                    }
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    private static boolean isConfigurationLine(String line) {
        if (line.startsWith("*")) {
            return true;
        }
        //return false; // FIXME
        throw new EnigmaException("no configuration line");
    }

    private static boolean issecondConfigurationLine(String line) {
        if (line.startsWith("*")) {
            return true;
        }
        return false; // FIXME
        //throw new EnigmaException("no configuration line");
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    private static void configure(Machine M, String config) {
        // FIXME
        String[] arr = config.split(" ");
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].equals(arr[i])) {
                    throw new EnigmaException("bad configuration");
                }
            }
        }
        _firotor.setIndex(arr[5]);
        _serotor.setIndex(arr[4]);
        _throtor.setIndex(arr[3]);
        _fixrotor.setIndex(arr[2]);
        _reflector.setIndex(arr[1]);

        Rotor[] rotors = {_reflector, _fixrotor, _throtor, _serotor, _firotor};
        M.replaceRotors(rotors);
        M.setRotors(arr[6]);
    }

    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) { //try this method but failed
        line = line.replace(" ", "");
        line = line.replace("\t", "");
        line = line.replace("\n", "");
        line = line.toUpperCase();
        if (!line.matches("[a-zA-Z]+") && !line.equals("")) {
            System.out.format(line);
            throw new EnigmaException("bad input line");
        }
        return line; // FIXME
    }

    /** Write MSG in groups of five to out file (except that the last
     *  group may have fewer letters). */
    private static void writeMessageLine(String msg, String filename) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            String outputString = ""; 
            for (int i = 0; i < msg.length(); i += 5) {
                outputString += msg.substring(i, Math.min(i + 5, msg.length()));
                if (i + 5 < msg.length()) {
                    outputString += " ";
                }
            }

            writer.write(outputString + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException when writing: " + e);
        }
    }

    /** Create all the necessary rotors. */
    private static void buildRotors() {
        _firotor = new Rotor();
        _serotor = new Rotor();
        _throtor = new Rotor();
        _fixrotor = new FixedRotor();
        _reflector = new Reflector();
        // FIXME
    }

}

