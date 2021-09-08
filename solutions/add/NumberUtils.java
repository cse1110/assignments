package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class NumberUtilsTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void shouldReturnCorrectResult(List<Integer> left, List<Integer> right, List<Integer> expected) {
        assertThat(NumberUtils.add(left, right)).isEqualTo(expected);
    }

    static Stream<Arguments> testCases() {
        /**
         * left:
         *  - empty
         *  - null
         *  - single digit
         *  - multiple digits
         *  - zeroes on the left
         *
         * right:
         *  - empty
         *  - null
         *  - single digit
         *  - multiple digits
         *  - zeroes on the left
         *
         * (left, right):
         *  - len(left) > len(right)
         *  - len(left) < len(right)
         *  - len(left) = len(right)
         *
         * carry:
         *  - sum without carry
         *  - sum with carry:
         *    - one carry at the beginning
         *    - one carry in the middle
         *    - one carry at the end
         *    - many carries
         *    - many carries, not in a row
         *
         *
         * Test Cases:
         *  Exceptional cases:
         *   - T1 = left null
         *   - T2 = left empty
         *   - T3 = right null
         *   - T4 = right empty
         *   - T5 = both empty
         *
         *  Single digit:
         *   - T6 = no carry
         *   - T7 = carry
         *
         *  Multiple digits:
         *   - T8  = no carry
         *   - T9  = carry in the least significant digit
         *   - T10 = carry in the middle
         *   - T11 = many carries
         *   - T12 = many carries, not in a row
         *   - T13 = leftover
         *
         *  Multiple digits, different length:
         *   - T14 = no carry
         *   - T15 = carry in the least significant digit
         *   - T16 = carry in the middle
         *   - T17 = many carries
         *   - T18 = many carries, not in a row
         *   - T19 = leftover
         *
         *  Zeroes on the left:
         *   - T20 = no carry
         *   - T21 = carry
         */

        return Stream.of(
                // Exceptional cases
                Arguments.of(null, numbers(1, 2), null),                                       // T1
                Arguments.of(numbers(), numbers(1, 2), numbers(1, 2)),                          // T2
                Arguments.of(numbers(1, 2), null, null),                                       // T3
                Arguments.of(numbers(1, 2), numbers(), numbers(1, 2)),                          // T4
                Arguments.of(numbers(), numbers(), numbers(0)),                               // T5

                // Single digit
                Arguments.of(numbers(1), numbers(2), numbers(3)),                             // T6
                Arguments.of(numbers(9), numbers(2), numbers(1, 1)),                           // T7

                // Multiple digits
                Arguments.of(numbers(2, 2), numbers(3, 3), numbers(5, 5)),                       // T8
                Arguments.of(numbers(2, 9), numbers(2, 3), numbers(5, 2)),                       // T9
                Arguments.of(numbers(2, 9, 3), numbers(1, 8, 3), numbers(4, 7, 6)),                 // T10
                Arguments.of(numbers(1, 7, 9), numbers(2, 6, 8), numbers(4, 4, 7)),                 // T11
                Arguments.of(numbers(1, 9, 1, 7, 1), numbers(1, 8, 1, 6, 1), numbers(3, 7, 3, 3, 2)),     // T12
                Arguments.of(numbers(9, 9, 8), numbers(1, 7, 2), numbers(1, 1, 7, 0)),               // T13

                // Multiple digits, different length, from both sides
                Arguments.of(numbers(2, 2), numbers(3), numbers(2, 5)),                         // T14, left
                Arguments.of(numbers(3), numbers(2, 2), numbers(2, 5)),                         // T14, right
                Arguments.of(numbers(2, 2), numbers(9), numbers(3, 1)),                         // T15, left
                Arguments.of(numbers(9), numbers(2, 2), numbers(3, 1)),                         // T15, right
                Arguments.of(numbers(1, 7, 3), numbers(9, 2), numbers(2, 6, 5)),                   // T16, left
                Arguments.of(numbers(9, 2), numbers(1, 7, 3), numbers(2, 6, 5)),                   // T16, right
                Arguments.of(numbers(3, 1, 7, 9), numbers(2, 6, 8), numbers(3, 4, 4, 7)),             // T17, left
                Arguments.of(numbers(2, 6, 8), numbers(3, 1, 7, 9), numbers(3, 4, 4, 7)),             // T17, right
                Arguments.of(numbers(1, 9, 1, 7, 1), numbers(2, 1, 8, 1, 6, 1), numbers(2, 3, 7, 3, 3, 2)), // T18, left
                Arguments.of(numbers(2, 1, 8, 1, 6, 1), numbers(1, 9, 1, 7, 1), numbers(2, 3, 7, 3, 3, 2)), // T18, right
                Arguments.of(numbers(9, 9, 8), numbers(9, 1, 7, 2), numbers(1, 0, 1, 7, 0)),           // T19, left
                Arguments.of(numbers(9, 1, 7, 2), numbers(9, 9, 8), numbers(1, 0, 1, 7, 0)),           // T19, right

                // Zeroes on the left
                Arguments.of(numbers(0, 0, 0, 1, 2), numbers(0, 2, 3), numbers(3, 5)),               // T20
                Arguments.of(numbers(0, 0, 0, 1, 2), numbers(0, 2, 9), numbers(4, 1))                // T21
        );
    }

    @ParameterizedTest
    @MethodSource("digitsOutOfRange")
    void shouldThrowExceptionWhenDigitsAreOutOfRange(List<Integer> left, List<Integer> right) {
        assertThatThrownBy(() -> NumberUtils.add(left, right)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> digitsOutOfRange() {
        return Stream.of(
                Arguments.of(numbers(1, -1, 1), numbers(1, 1, 1)),
                Arguments.of(numbers(1, 1, 1), numbers(1, -1, 1)),
                Arguments.of(numbers(1, 10, 1), numbers(1, 1, 1)),
                Arguments.of(numbers(1, 1, 1), numbers(1, 10, 1))
        );
    }

    /**
     * Helper method to create a list of Integers.
     *
     * @param nums Array of numbers to put in a list.
     * @return List containing all the numbers of the input array.
     */
    private static List<Integer> numbers(int... nums) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums)
            list.add(n);
        return list;
    }

}