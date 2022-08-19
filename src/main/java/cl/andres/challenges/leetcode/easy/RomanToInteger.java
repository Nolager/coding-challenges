package cl.andres.challenges.leetcode.easy;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Roman to Integer
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is
 * simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not
 * IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The
 * same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * Given a roman numeral, convert it to an integer.
 *
 * Example 1:
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 *
 * Example 2:
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 *
 * Example 3:
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 */
public class RomanToInteger {
    private static final Map<String, RomanNumeral> romanNumerals = new HashMap<>();

    public static void main(String[] args) {
        romanNumerals.put("I", new RomanNumeral("I", 1, 3));
        romanNumerals.put("V", new RomanNumeral("V", 5, 0));
        romanNumerals.put("X", new RomanNumeral("X", 10, 2));
        romanNumerals.put("L", new RomanNumeral("L", 50, 0));
        romanNumerals.put("C", new RomanNumeral("C", 100, 3));
        romanNumerals.put("D", new RomanNumeral("D", 500, 0));
        romanNumerals.put("M", new RomanNumeral("M", 1000, 2));

        System.out.println("" + romanToInt("CCCLII"));
    }

    public static int romanToInt(String s) {
        int value = 0;
        int repetitions = 0;
        String reversedRomanNumeral = new StringBuilder(s).reverse().toString();

        for (int i = reversedRomanNumeral.length()-1; i >= 0; i--) {
            RomanNumeral currentNumeral = romanNumerals.get(String.valueOf(s.charAt(i)));

            if (Objects.isNull(currentNumeral)) {
                throw new IllegalArgumentException("Roman numeral not valid: " + s);
            }

            if (i == reversedRomanNumeral.length()-1) {
                value = currentNumeral.getValue();
                repetitions++;
                continue;
            }

            RomanNumeral previousNumeral = romanNumerals.get(String.valueOf(s.charAt(i+1)));

            if (currentNumeral.getValue() == previousNumeral.getValue() && repetitions >= currentNumeral.getMaxRepetition()) {
                throw new IllegalArgumentException("Roman numeral not valid: " + s);
            }

            if (currentNumeral.getValue() == previousNumeral.getValue()) {
                value += currentNumeral.getValue();
                repetitions++;
            } else if (currentNumeral.getValue() < previousNumeral.getValue()) {
                value -= currentNumeral.getValue();
                repetitions = 0;
            } else if (currentNumeral.getValue() > previousNumeral.getValue()) {
                value += currentNumeral.getValue();
                repetitions = 0;
            }
        }

        return value;
    }
}

@AllArgsConstructor
@Getter
class RomanNumeral {
    private final String symbol;
    private final int value;
    private final int maxRepetition;
}
