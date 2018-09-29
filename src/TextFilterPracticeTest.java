import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * CS18000 - Fall 2018
 *
 * Project 1 - Text Filter
 *
 * Practice Test Cases for Project 1. Run these tests to check the 
 * functionality of your code.
 *
 * @author Jonathan Grider, jagrider@purdue.edu
 * @version 2/2/18
 */
public class TextFilterPracticeTest {

    /* Strings that are used in multiple cases */
    private static final String welcome = "Welcome to TextFilter!";
    private static final String options = multiline("Please select one of the following filtering options: ",
            "1. Filter Word\n", "2. Find-And-Replace\n", "3. Censor Information", "");
    private static final String welcomeOptions = multiline(welcome, options);
    private static final String invalidOption = "The option you chose was invalid!";
    private static final String moreFilter = "Would you like to keep filtering? Yes/No";
    private static final String goodbye = "Thank you for using TextFilter!";
    private static final String noGoodbye = multiline(moreFilter, goodbye);
    private static final String uncensored = "Uncensored :";
    private static final String censored = "Censored: ";

    /* Part 1 Strings */
    private static final String filterPassage = "Please enter the passage you would like filtered: ";
    private static final String filterWord = "Please enter the word you would like to censor: ";

    /* Part 2 Strings */
    private static final String replacePassage = "Please enter the passage you would like filtered: ";
    private static final String replaceWord = "Please enter the word you would like to replace: ";
    private static final String replaceFilter = "Please enter word you would like to insert: ";

    /* Part 3 Strings */
    private static final String censorPassage = "Please enter the phrase you would like to censor information from: ";

    /* Strings that are reused in multiple tests */
    private static final String woodchuck = "How much wood could a woodchuck chuck if a woodchuck could chuck wood";
    private static final String woodchuckFiltered = "How much wood could a Narwhal chuck if a " +
            "Narwhal could chuck wood";

    private static final String thinkers = "Three thin thinkers thinking thick thoughtful thoughts.";
    private static final String thinkersCensored = "Three XXXX thinkers thinking thick thoughtful thoughts.";
    private static final String thinkersFiltered = "Three tall thinkers thinking thick thoughtful thoughts.";

    private static final String csIsFun = "I wish I could go back to my first CS class, CS180";
    private static final String csIsFunCensored = "I wish I could go back to my first CS class, XXXXX";

    private static final String uncensoredName = "Name: Logan Kullinski";
    private static final String censoredName = "Name: L**** ********i";
    private static final String uncensoredPhone = "Phone: 123-456-7890";
    private static final String censoredPhone = "Phone: ***-***-7890";


    private final InputStream originalSysin = System.in;
    private final PrintStream originalSysout = System.out;
    private ByteArrayOutputStream recordingSysout;

    @Before
    public void setup() {
        System.setIn(originalSysin);
        System.setOut(originalSysout);
    }

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @After
    public void tearDown() {
        stopRecording();
    }


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /*                                BEGIN TEST CASES                                 */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    /**     _____________________________________
     *     |           Testcase Summary:         |
     *     |-------------------------------------|
     *     | Part 1 Tests      |               2 |
     *     | Part 2 Tests      |               2 |
     *     | Part 3 Tests      |               2 |
     *     | Part 4 Tests      |               1 |
     *     ---------------------------------------
     */


    /* Begin Part 1 Tests */


    @Test(timeout = 100)
    public void testCensorWord1() {
        String input = multiline("1", thinkers, "thin", "No");
        String expected = multiline(welcomeOptions, filterPassage, filterWord, uncensored, thinkers, censored,
                thinkersCensored, noGoodbye);
        String message = "Ensure that you properly censor words that are not part of larger words.";

        setSystemIn(input);
        recordOutput();
        TextFilter.main(new String[] {});

        assertFuzzyEquals(message, expected, getRecording());
    }

    @Test(timeout = 100)
    public void testCensorWordEdges() {
        String input = multiline("1", csIsFun, "CS180", "No");
        String expected = multiline(welcomeOptions, filterPassage, filterWord, uncensored, csIsFun, censored,
                csIsFunCensored, noGoodbye);
        String message = "Ensure that you properly censor words at the beginning and/or end of the passage.";

        setSystemIn(input);
        recordOutput();
        TextFilter.main(new String[] {});

        assertFuzzyEquals(message, expected, getRecording());
    }



    /* End Part 1 Tests */


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    /* Begin Part 2 Tests */


    @Test(timeout = 100)
    public void testFilterWord1() {
        String input = multiline("2", woodchuck, "woodchuck", "Narwhal", "No");
        String expected = multiline(welcomeOptions, replacePassage, replaceWord, replaceFilter, uncensored,
                woodchuck, censored, woodchuckFiltered, noGoodbye);
        String message = "Ensure you are properly filtering phrases when a key-phrase is entered.";

        setSystemIn(input);
        recordOutput();
        TextFilter.main(new String[] {});

        assertFuzzyEquals(message, expected, getRecording());
    }

    @Test(timeout = 100)
    public void testFilterWord2() {
        String replace = "thin";
        String insert = "tall";

        String input = multiline("2", thinkers, replace, insert, "No");
        String expected = multiline(welcomeOptions, replacePassage, replaceWord, replaceFilter, uncensored,
                thinkers, censored, thinkersFiltered, noGoodbye);
        String message = "Ensure you are properly filtering phrases when a key-phrase is entered.";

        setSystemIn(input);
        recordOutput();
        TextFilter.main(new String[] {});

        assertFuzzyEquals(message, expected, getRecording());
    }


    /* End Part 2 Tests */


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    /* Begin Part 3 Tests */


    @Test(timeout = 100)
    public void testName() {
        String input = multiline("3", uncensoredName + '\n', "No");
        String expected = multiline(welcomeOptions, censorPassage, uncensored, uncensoredName, censored,censoredName,
                noGoodbye);
        String message = "Ensure that you are properly censoring Names.";

        setSystemIn(input);
        recordOutput();
        TextFilter.main(new String[] {});

        assertFuzzyEquals(message, expected, getRecording());
    }

    @Test(timeout = 100)
    public void testPhone() {
        String input = multiline("3", uncensoredPhone + '\n', "No");
        String expected = multiline(welcomeOptions, censorPassage, uncensored, uncensoredPhone, censored, censoredPhone,
                noGoodbye);
        String message = "Ensure that you are properly censoring Telephone Numbers.";

        setSystemIn(input);
        recordOutput();
        TextFilter.main(new String[] {});

        assertFuzzyEquals(message, expected, getRecording());
    }


    /* End Part 3 Tests */


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    /* Begin Part 4 Tests */


    @Test(timeout = 100)
    public void testKeepGoing() {
        String input = multiline("4", "YAS");
        String expected = multiline(welcomeOptions, invalidOption, moreFilter, goodbye);
        String message = "Ensure that you update the value of keepGoing correctly if the user enters something " +
                "other than \"Yes\"";

        setSystemIn(input);
        recordOutput();
        TextFilter.main(new String[] {});

        assertFuzzyEquals(message, expected, getRecording());
    }


    /* End Part 4 Tests */


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    /* Begin Robustness Test Case */

    /**
     * This test is to ensure that multiple types of filtering and censoring can be done consecutively
     */

    @Test(timeout = 1000)
    public void testRobustness() {

        // TODO: Try to write your own Robustness test case :)
        // TODO: THIS IS NOT REQUIRED

        assertEquals(1, 1);
    }

    /* End Robustness Test Case */


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */



    /* * * * * * * * * * * * * * * * * * * * * */
    /*          UTILITY FUNCTIONS              */
    /*           @author joseph                */
    /* * * * * * * * * * * * * * * * * * * * * */

    /**
     * Behaves like WebCat's setSystemIn, terminates all lines with a
     * system dependent newline character and replaces System.in
     *
     * @param inputLines The lines of input, one string per line.
     */
    private void setSystemIn(String... inputLines) {
        byte[] bytes = multiline(inputLines).getBytes();
        ByteArrayInputStream newInput = new ByteArrayInputStream(bytes);
        System.setIn(newInput);
    }

    /**
     * Behaves like WebCat's setSystemIn, terminates all lines with a
     * system dependent newline character and replaces System.in
     *
     * @param inputLines The lines of input, one string per line.
     * @return A string with all lines concatenated and ended with the system newline character.
     */
    private static String multiline(String... inputLines) {
        StringBuilder sb = new StringBuilder();

        for(String line : inputLines) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    /**
     * Starts recording the stdout of the application.
     */
    private void recordOutput() {
        recordingSysout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(recordingSysout));
    }

    public void stopRecording() {
        System.setOut(originalSysout);

        if(recordingSysout != null) {
            getRecording();
        }

        recordingSysout = null;
    }

    /**
     * Gets the recording of sysout.
     * @return All the lines put in stdout since calling recordOutput
     */
    private String getRecording() {
        String output = recordingSysout.toString();
        recordingSysout.reset();

        originalSysout.print(output);
        return output;
    }

    /**
     * Does a "fuzzy" equals on two strings. This is comparable to running diff
     * with the -wiB flags. Ignores whitespace differences, case and blank lines.
     *
     * @param description The human readable description if the strings don't compare.
     * @param expected The expected value of the program output
     * @param actual The actual value of the program output
     */
    private void assertFuzzyEquals(String description, String expected, String actual) {
        if(normalize(expected).equals(normalize(actual))) {
            return;
        }

        assertEquals(description, expected, actual);
    }

    /**
     * Does a "fuzzy" startsWith on two strings. This is comparable to running diff
     * with the -wiB flags. Ignores whitespace differences, case and blank lines.
     *
     * @param description The human readable description if the strings don't compare.
     * @param expectedStart The string actual is expected to start with
     * @param actual The actual value of the program output
     */
    private void assertFuzzyBegins(String description, String expectedStart, String actual) {
        if(normalize(actual).startsWith(normalize(expectedStart))) {
            return;
        }

        assertEquals(description, expectedStart, actual);
    }


    private static String normalize(String str) {
        str = str.toLowerCase();
        str = str.replaceAll("\\s+","");
        return str;
    }


    /* * * * * * * * * * * * * * * * * * * * * */
    /*           END UTILITY FUNCTIONS         */
    /* * * * * * * * * * * * * * * * * * * * * */



}
