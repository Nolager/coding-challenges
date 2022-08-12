package cl.andres.challenges.coderbyte.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array, find the most frequent element in it. If there are multiple elements that appear a maximum number
 * of times, print any one of them.
 *
 * Examples:
 *
 * Input : arr[] = {1, 3, 2, 1, 4, 1}
 * Output : 1
 * Explanation: 1 appears three times in array which is maximum frequency.
 *
 * Input : arr[] = {10, 20, 10, 20, 30, 20, 20}
 * Output : 20
 *
 * Source: https://www.geeksforgeeks.org/frequent-element-array/
 *
 */
public class MostFrequentElement {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 2, 1, 4, 1};
        int[] numbersTwo = {10, 20, 10, 20, 30, 20, 20};

        myAttempt(numbers);
        myAttempt(numbersTwo);

        System.out.println("Most frequent better solution: " + mostFrequentBetterSolution(numbers, numbers.length));
        System.out.println("Most frequent better solution: " + mostFrequentBetterSolution(numbersTwo, numbersTwo.length));

        System.out.println("Most frequent efficient solution: " + mostFrequentMoreEfficient(numbers, numbers.length));
        System.out.println("Most frequent efficient solution: " + mostFrequentMoreEfficient(numbersTwo, numbersTwo.length));
    }

    public static void myAttempt(int[] numbers) {
        int currentCount = 1;
        int highestCount = 0;
        int mostFrequentNumber = 0;

        for (int i = 0; i < numbers.length; i++) {
            currentCount = 1;

            for (int j = 0; j < numbers.length; j++) {

                if (i != j && numbers[i] == numbers[j]) {
                    currentCount++;
                }
            }

            if (currentCount > highestCount) {
                highestCount = currentCount;
                mostFrequentNumber = numbers[i];
            }
        }

        System.out.println("The most frequent number is: " + mostFrequentNumber);
    }

    /**
     * A better solution is to do the sorting. We first sort the array, then linearly traverse the array.
     *
     * @param arr Numbers array
     * @param n Array size
     * @return Most frequent number
     */
    static int mostFrequentBetterSolution(int arr[], int n)
    {
        // Sort the array
        Arrays.sort(arr);

        // find the max frequency using linear traversal
        int max_count = 1;
        int res = arr[0];
        int curr_count = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1])
                curr_count++;
            else
                curr_count = 1;

            if (curr_count > max_count) {
                max_count = curr_count;
                res = arr[i - 1];
            }
        }

        return res;
    }

    /**
     * An efficient solution is to use hashing. We create a hash table and store elements and their frequency counts
     * as key-value pairs. Finally, we traverse the hash table and print the key with the maximum value.
     *
     * @param arr Numbers array
     * @param n Array size
     * @return Most frequent number
     */
    static int mostFrequentMoreEfficient(int arr[], int n) {

        // Insert all elements in hash
        Map<Integer, Integer> hp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int key = arr[i];

            if (hp.containsKey(key)) {
                int freq = hp.get(key);
                freq++;
                hp.put(key, freq);
            } else {
                hp.put(key, 1);
            }
        }

        // find max frequency.
        int max_count = 0;
        int res = -1;

        for (Map.Entry<Integer, Integer> val : hp.entrySet()) {
            if (max_count < val.getValue()) {
                res = val.getKey();
                max_count = val.getValue();
            }
        }

        return res;
    }
}
