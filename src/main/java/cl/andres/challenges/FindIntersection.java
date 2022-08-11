package cl.andres.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find Intersection
 *
 * Have the function FindIntersection(strArr) read the array of strings stored in strArr which will contain
 * 2 elements: the first element will represent a list of comma-separated numbers sorted in ascending order, the second
 * element will represent a second list of comma-separated numbers (also sorted). Your goal is to return a
 * comma-separated string containing the numbers that occur in elements of strArr in sorted order. If there is no
 * intersection, return the string false.
 *
 * Examples:
 *
 * Input: new String[] {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"}
 * Output: 1,4,13
 *
 * Input: new String[] {"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"}
 * Output: 1,9,10
 *
 */
public class FindIntersection {
    public static void main(String[] args) {
        String[] stringArray = new String[] {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"};
        String[] stringArray2 = new String[] {"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"};

        System.out.println("Output: " + findIntersection(stringArray));
        System.out.println("Output: " + findIntersection(stringArray2));
    }

    public static String findIntersection(String[] strArr) {
        // code goes here
        Map<String, String> map = new HashMap<>();
        List<String> output = new ArrayList<>();
        String returnedValue;

        Arrays.stream(strArr[0].replaceAll("\\s","").split(","))
                .forEach(number -> map.put(number, number));
        Arrays.stream(strArr[1].replaceAll("\\s","").split(","))
                .forEach(number -> {

                    if (map.get(number) != null) {
                        output.add(number);
                    }
                });

        returnedValue = (output.size() == 0) ? "false" : String.join(",", output);

        return returnedValue;
    }
}
