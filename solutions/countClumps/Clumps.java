package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class ClumpsTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("generator")
    void clumpsTest(String name, int[] nums, int res) {
        assertThat(Clumps.countClumps(nums)).isEqualTo(res);
    }

    public static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of("Array with one element", new int[] { 9 }, 0),
                Arguments.of("Array with multiple elements, no clump", new int[] { 3, 6, 2, 7, 4, 2 }, 0),
                Arguments.of("Array with one continuous clump", new int[] { 42, 42, 42, 42 }, 1),
                Arguments.of("Array with multiple clumps", new int[] { 1, 1, 3, 0, 0 }, 2),
                Arguments.of("Array with multiple clumps, side-by-side", new int[] { 1, 1, 0, 0, 3 }, 2)
        );
    }

}