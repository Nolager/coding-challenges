package cl.andres.challenges.coderbyte.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Questions Marks
 *
 * Have the function QuestionsMarks(str) take the str string parameter, which will contain single digit numbers,
 * letters, and question marks, and check if there are exactly 3 question marks between every pair of two numbers that
 * add up to 10. If so, then your program should return the string true, otherwise it should return the string false.
 * If there aren't any two numbers that add up to 10 in the string, then your program should return false as well.
 *
 * For example: if str is "arrb6???4xxbl5???eee5" then your program should return true because there are exactly 3
 * question marks between 6 and 4, and 3 question marks between 5 and 5 at the end of the string.
 *
 * Examples:
 *
 * Input: "aa6?9"
 * Output: false
 *
 * Input: "acc?7??sss?3rr1??????5"
 * Output: true
 */
public class QuestionsMarks {
    public static void main(String[] args) {
        String input = "5??aaaaaaaaaaaaaaaaaaa?5?5";
        System.out.println("Input: " + input);
        System.out.println("Output: " + questionsMarks(input));
    }

    public static String questionsMarks(String str) {
        // code goes here
        char[] charArray = str.toCharArray();
        List<String> samplesList = new ArrayList<>();
        StringBuilder sample = new StringBuilder();
        String returnValue = "false";

        for (int i = 0; i < charArray.length ; i++) {
            try {
                Integer.valueOf(String.valueOf(charArray[i]));
                sample.append(charArray[i]);

                for (int j = i + 1; j < charArray.length ; j++) {

                    sample.append(charArray[j]);

                    try {
                        Integer.valueOf(String.valueOf(charArray[j]));
                        samplesList.add(sample.toString());
                        sample = new StringBuilder();
                        break;
                    } catch (NumberFormatException ignored) {}
                }

            } catch (NumberFormatException ignored) {}
        }

        for (String s : samplesList) {
            int questionMarkCount = 0;
            int firstNumberValue = Integer.parseInt(String.valueOf(s.toCharArray()[0]));
            int secondNumberValue = Integer.parseInt(String.valueOf(s.toCharArray()[s.toCharArray().length-1]));
            int numbersSum = firstNumberValue + secondNumberValue;

            for (char c : s.toCharArray()) {
                if (c == '?') {
                    questionMarkCount++;
                }
            }

            if (questionMarkCount > 2 && numbersSum == 10) {
                returnValue = "true";
                break;
            }
        }

        return returnValue;
    }
}
