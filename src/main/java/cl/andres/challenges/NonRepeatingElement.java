package cl.andres.challenges;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Find the first non-repeating element in a given array of integers.
 *
 * Examples:
 *
 * Input : -1 2 -1 3 2
 * Output : 3
 * Explanation : The first number that does not repeat is : 3
 *
 * Input : 9 4 9 6 7 4
 * Output : 6
 *
 * Source: https://www.geeksforgeeks.org/non-repeating-element/
 *
 */
public class NonRepeatingElement {
    public static void main(String[] args) {
        int[] numbers = { -1, 2, -1, 3, 2 };
        int[] numbersTwo = { 9, 4, 9, 6, 7, 4 };

        myAttempt(numbers);
        myAttempt(numbersTwo);

        System.out.println("Simple solution: " + firstNonRepeatingSimple(numbers, numbers.length));
        System.out.println("Simple solution: " + firstNonRepeatingSimple(numbersTwo, numbersTwo.length));
        System.out.println("Efficient solution: " + firstNonRepeatingEfficient(numbers, numbers.length));
        System.out.println("Efficient solution: " + firstNonRepeatingEfficient(numbersTwo, numbersTwo.length));
        firstNonRepeatingMoreOptimized(numbers, numbers.length);
        firstNonRepeatingMoreOptimized(numbersTwo, numbersTwo.length);

        System.out.println("Using Stream API: " + firstNonRepeatingStream(numbers));
        System.out.println("Using Stream API: " + firstNonRepeatingStream(numbersTwo));
    }

    /**
     * This is my attempt to complete the challenge
     * @param numbers
     */
    public static void myAttempt(int[] numbers) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length ; i++) {

            if (map.containsKey(numbers[i])) {
                map.put(numbers[i], map.get(numbers[i]) + 1);
            } else {
                map.put(numbers[i], 1);
            }
        }

        for (int i = 0; i < numbers.length ; i++) {
            if (map.get(numbers[i]) == 1) {
                System.out.println("The first non-repeating element is: " + numbers[i]);
                return;
            }
        }
    }

    /**
     * Simple Solution: Use two loops. The outer loop picks elements one by one and inner loop checks if the element is
     * present more than once or not.
     *
     * Source: https://www.geeksforgeeks.org/non-repeating-element/
     *
     * @param arr Numbers array
     * @param n Array size
     * @return First non-repeating element
     */
    static int firstNonRepeatingSimple(int[] arr, int n) {

        for (int i = 0; i < n; i++) {
            int j;
            for (j = 0; j < n; j++) {
                if (i != j && arr[i] == arr[j]) {
                    break;
                }
            }

            if (j == n) {
                return arr[i];
            }
        }

        return -1;
    }

    /**
     * Efficient Solution: use hashing.
     *
     * 1) Traverse array and insert elements and their counts in hash table.
     * 2) Traverse array again and print first element with count equals to 1.
     *
     * Source: https://www.geeksforgeeks.org/non-repeating-element/
     *
     * @param arr Numbers array
     * @param n Array size
     * @return First non-repeating element
     */
    static int firstNonRepeatingEfficient(int[] arr, int n) {
        Map<Integer, Integer> map = new HashMap<>();

        // Insert all array elements in hash table
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        // Traverse array again and return first element with count 1.
        for (int i = 0; i < n; i++) {
            if (map.get(arr[i]) == 1) {
                return arr[i];
            }
        }

        return -1;
    }

    /**
     * Further Optimization: If array has many duplicates, we can also store index in hash table, using a hash table
     * where value is a pair. Now we only need to traverse keys in hash table (not complete array) to find first non
     * repeating.
     *
     * Source: https://www.geeksforgeeks.org/non-repeating-element/
     *
     * @param arr Numbers array
     * @param n Array size
     * @return First non-repeating element
     */
    static void firstNonRepeatingMoreOptimized(int[] arr, int n) {
        Map<Integer, Integer> map = new HashMap<>();

        // Insert all array elements in hash table
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        // Traverse through map only and
        // using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<Integer, Integer> x : map.entrySet()) {
            if (x.getValue() == 1) {
                System.out.println("More optimized solution: " + x.getKey());
                return;
            }
        }
    }

    /**
     * Using Stream API
     *
     * @param arr Numbers array
     * @return First non-repeating element
     */
    static int firstNonRepeatingStream(int[] arr) {

        List<Integer> numbers = Arrays.stream(arr).boxed().collect(Collectors.toList());

        return numbers
                .stream()
                .filter(i -> Collections.frequency(numbers, i) == 1)
                .findFirst()
                .orElse(-1);
    }
}
