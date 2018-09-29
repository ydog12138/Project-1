import java.util.Scanner;

/**
 * TODO: DESCRIBE YOUR PROJECT HERE
 *
 * @author Your Name, youremail@purdue.edu
 * @version MM/DD/YY
 */
public class TextFilter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print hello message
        System.out.println("Welcome to TextFilter!");

        // Value to keep track of if the user wants to keep filtering text
        boolean keepFiltering;

        do {
            // Print options for the user to select
            System.out.println("Please select one of the following filtering options: \n");
            System.out.println("1. Filter Word\n" +
                    "2. Find-And-Replace\n" +
                    "3. Censor Information");

            // Save their choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                // PART 1 - Censoring Words


                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");

                passage = scanner.nextLine();


                String word;  // The word to be censored from the text phrase
                System.out.println("Please enter the word you would like to censor: ");

                word = scanner.nextLine();
                String temp = "";
                for (int i = 0; i < word.length(); i++)
                    temp += "X";

                System.out.println("Uncensored: ");
                System.out.println(passage);

                if (word.trim().contains(" ")) {
                    passage = passage.replaceAll(word, temp);
                } else {
                    String[] input = passage.split(" ");
                    for (int k = 0; k < input.length; k++) {
                        if (input[k].equals(word)) {
                            input[k] = temp;
                        } else if (input[k].equals(word + ".")) {
                            input[k] = temp + ".";
                        } else if (input[k].equals(word + ",")) {
                            input[k] = temp + ",";
                        } else if (input[k].equals(word + "?")) {
                            input[k] = temp + "?";
                        } else if (input[k].equals(word + "!")) {
                            input[k] = temp + "!";
                        }
                    }
                    passage = String.join(" ", input);
                }

                System.out.println("Censored: ");
                System.out.println(passage);


            } else if (choice == 2) {

                // PART 2 - Replacing Words


                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");


                passage = scanner.nextLine();


                String replace;  // The word to be filtered from the text phrase
                System.out.println("Please enter the word you would like to replace: ");

                replace = scanner.nextLine();


                String insert;  // The word to be inserted in the text phrase
                System.out.println("Please enter word you would like to insert: ");

                insert = scanner.nextLine();


                System.out.println("Uncensored: ");
                System.out.println(passage);


                if (replace.trim().contains(" ")) {
                    passage = passage.replaceAll(replace, insert);
                } else {
                    String[] input = passage.split(" ");
                    for (int k = 0; k < input.length; k++) {
                        if (input[k].equals(replace)) {
                            input[k] = insert;
                        } else if (input[k].equals(replace + ".")) {
                            input[k] = insert + ".";
                        } else if (input[k].equals(replace + ",")) {
                            input[k] = insert + ",";
                        } else if (input[k].equals(replace + "?")) {
                            input[k] = insert + "?";
                        } else if (input[k].equals(replace + "!")) {
                            input[k] = insert + "!";
                        }
                    }
                    passage = String.join(" ", input);
                }


                System.out.println("Censored: ");
                System.out.println(passage);



            } else if (choice == 3) {

                // PART 3 - Censoring Personal Information


                /*
                 * DO NOT ALTER THIS CODE! This formatting is imperative to the completion of this task.
                 *
                 * Keep asking for input until the user enters an empty line
                 * If they enter an empty line and the phrase is empty, keep waiting for input
                 */
                String passage = "";  // String for holding text to be filtered

                System.out.println("Please enter the phrase you would like to censor information from: ");

                while (true) {

                    // Obtain a line from the user
                    String temp = scanner.nextLine();

                    if (!passage.isEmpty() && temp.isEmpty()) {
                        break;
                    } else if (passage.isEmpty() && temp.isEmpty()) {
                        continue;
                    }


                    // Add the contents of temp into the phrase
                    passage += temp;


                    // Append a newline character to each line for parsing
                    // This will separate each line the user enters
                    // To understand how input is formatted in Part 3, please refer to the handout.
                    passage += '\n';
                }

                // Print the uncensored passage
                System.out.println("Uncensored: ");
                System.out.println(passage);

                // TODO: PART 3 - Implement your parsing here

                // Print the censored passage
                System.out.println("Censored: ");
                System.out.println(passage);

            } else {

                // They entered a number that was not 1, 2 or 3
                System.out.println("The option you chose was invalid!");

            }


            System.out.println("Would you like to keep filtering? Yes/No");

            // TODO: PART 4 - Update the value of keepGoing accordingly
            // TODO: PART 4 - Replace the line below with your code
            keepFiltering = false;

        } while (keepFiltering);

        System.out.println("Thank you for using TextFilter!");

    }

}
