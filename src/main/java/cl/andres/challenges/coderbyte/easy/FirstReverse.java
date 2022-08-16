package cl.andres.challenges.coderbyte.easy;

/**
 * First Reverse
 *
 * Have the function FirstReverse(str) take the str parameter being passed and return the string in reversed order.
 * For example: if the input string is "Hello World and Coders" then your program should return the string sredoC dna
 * dlroW olleH.
 *
 * Examples:
 *
 * Input: "coderbyte"
 * Output: etybredoc
 *
 * Input: "I Love Code"
 * Output: edoC evoL I
 *
 */
public class FirstReverse {
    public static void main(String[] args) {
        System.out.println(firstReverse("I Love Code"));
    }

    public static String firstReverse(String str) {
        // code goes here
        StringBuilder reverseString = new StringBuilder();

        for (int i = str.length()-1; i >= 0; i--) {
            reverseString.append(str.charAt(i));
        }

        return reverseString.toString();
    }

    /**
     * Solution from https://coderbyte.com/results/CEThompson:First%20Reverse:Java
     */
    String otherSolution(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
