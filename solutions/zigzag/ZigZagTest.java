package delft;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.*;
import java.util.stream.*;

class ZigZagTest {

    @ParameterizedTest
    @CsvSource({ "A,A", "AB,AB" })
    void singleRow(String s, String expected) {
        assertThat(ZigZag.zigzag(s, 1)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("testCaseGenerator")
    void test(String s, int n, String expected) {
        assertThat(ZigZag.zigzag(s, n)).isEqualTo(expected);
    }

    public static Stream<Arguments> testCaseGenerator() {
        return Stream.of(
                Arguments.of("PAYPALISHIRING", 2, "PYAIHRN\nAPLSIIG"),
                Arguments.of("PAYPALISHIRING", 3, "P A H N\nAPLSIIG\nY I R"),
                Arguments.of("PAYPALISHIRING", 4, "P  I  N\nA LS IG\nYA HR\nP  I"),
                Arguments.of("PAYPALISHIRING", 5, "P   H\nA  SI\nY I R\nPL  IG\nA   N"),
                Arguments.of("ABC", 4, "A\nB\nC")
        );
    }

    @ParameterizedTest
    @MethodSource("exceptionGenerator")
    void testException(String s, int n) {
        assertThatThrownBy(() -> ZigZag.zigzag(s, n)).isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> exceptionGenerator() {
        return Stream.of(
                Arguments.of("", 1),
                Arguments.of(new String(new char[1001]).replace('\0', ' '), 1),
                Arguments.of("A", 0),
                Arguments.of("A", 1001)
        );
    }

}
