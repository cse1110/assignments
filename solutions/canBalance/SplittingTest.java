package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class SplittingTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("generator")
    void split(String name, int[] nums, boolean expected) {
        assertThat(Splitting.canBalance(nums)).isEqualTo(expected);
    }

    public static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of("Two equal elements", new int[] { 100, 100 }, true),
                Arguments.of("Two add up to single", new int[] { 101, 50, 51 }, true),
                Arguments.of("Three add up to single", new int[] { 11, 22, 33, 66 }, true),
                Arguments.of("Three add up to two", new int[] { 67, 33, 11, 42, 69 }, true),
                Arguments.of("Split outside middle", new int[] { 1, 1, 1, 1, 4 }, true),
                Arguments.of("Multiple non-split elements odd sum", new int[] { 1, 2, 3, 4, 5 }, false),
                Arguments.of("Multiple non-split elements even sum", new int[] { 1, 2, 3, 4, 5, 6, 7 }, false),
                Arguments.of("Half is 0 with odd sum", new int[] { 3, 3, 1 }, false),
                Arguments.of("Null test", null, false),
                Arguments.of("Empty input", new int[0], false),
                Arguments.of("Single element 0", new int[] { 0 }, false),
                Arguments.of("Single element", new int[] { 12345 }, false)
        );
    }

}
