// Andrew Twardowski
// 2026.11.19
// Because there isn't a built-in for this and it's a PAIN

// The comments here are terrible and it's digusting, but I'm tired of messing with it.

import java.util.ArrayList;

public class CSVParser {
    public static ArrayList<String> parse(String arg) {
        ArrayList<String> parsed = new ArrayList<>();
        
        // Check if the first char is a '"'.
        // If it is, search for the next '"'.
        // If the char after that IS NOT a '"', then get that substring.
        // If the char after IS a '"', then DO NOT get that substring, repeat the search for the next '"'
        // If the first char is NOT a '"', then split on ','

        // Check for as long as there are characters in the string
        while (arg.length() > 0) {
            // If the next character is NOT '"', split on ',' and add the first part
            // Then begin the loop with just the remaining part of the string
            if (arg.charAt(0) != '"') {
                if (arg.indexOf(',') > 0) {
                    String[] split = arg.split(",", 2);

                    parsed.add(split[0]);

                    arg = split[1];
                } else {
                    parsed.add(arg);

                    arg = "";
                }
            } else {
                int nextQuote = arg.indexOf('"', 1);

                // Otherwise, first check to see if next quote is NOT part of an escape sequence
                // If so, take the substring of just the part in the quotes and reassign arg to
                // the the substring of what remains
                while (arg.length() > 0) {
                    if (nextQuote + 2 > arg.length()) {
                        parsed.add(arg.substring(1, nextQuote - 4));

                        arg = "";

                        break;
                    } else if (arg.charAt(nextQuote + 1) != '"') {
                        parsed.add(arg.substring(1, nextQuote));

                        arg = arg.substring(nextQuote + 2);
                        
                        break;
                    } else {
                        // Cuts out the first '"'
                        String partOne = arg.substring(0, nextQuote + 1);
                        String partTwo = arg.substring(nextQuote + 2);
                        arg = partOne.concat(partTwo);

                        nextQuote = arg.indexOf('"', nextQuote + 1);
                    }
                }
            }
        }

        return parsed;
    }
}