package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class MathArraysTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("generator")
    public void concatenate(String description, double[][] arrays, double[] expected) {
        assertThat(MathArrays.concatenate(arrays)).isEqualTo(expected);
    }

    public static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of("single array", doublesOf(doubles(1d, 2d, 3d)), doubles(1d, 2d, 3d)),
                Arguments.of("two arrays", doublesOf(doubles(1d, 2d, 3d), doubles(4d, 5d, 6d)), doubles(1d, 2d, 3d, 4d, 5d, 6d)),
                Arguments.of("multiple arrays", doublesOf(doubles(1d, 2d, 3d), doubles(4d, 5d), doubles(6d)), doubles(1d, 2d, 3d, 4d, 5d, 6d)),
                Arguments.of("empty array", doublesOf(doubles(1), new double[]{}), doubles(1)),
                Arguments.of("no arrays", doublesOf(), doubles())
        );
    }

    @Test
    public void testNull() {
        assertThatThrownBy(() -> MathArrays.concatenate(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MathArrays.concatenate(doubles(1), null)).isInstanceOf(NullPointerException.class);
    }

    /**
     * Method to create an array of doubles.
     *
     * @param ds
     *      All the doubles an array should be created of.
     * @return an array of doubles.
     */
    private static double[] doubles(double... ds) {
        return ds;
    }

    /**
     * Method to create an array of arrays of doubles.
     *
     * @param ds
     *      All the double arrays an array should be created of.
     * @return an array of array of doubles.
     */
    private static double[][] doublesOf(double[]... ds) {
        return ds;
    }

}
