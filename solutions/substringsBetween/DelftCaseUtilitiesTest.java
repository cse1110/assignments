package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class DelftCaseUtilitiesTest {

    @ParameterizedTest
    @MethodSource("nullCases")
    public void testCaseNull(String s, String o, String c) {
        assertThat(DelftCaseUtilities.substringsBetween(s, o, c)).isNull();
    }

    public static Stream<Arguments> nullCases() {
        return Stream.of(
                Arguments.of("hello", "e", null),
                Arguments.of("hello", "e", ""),
                Arguments.of("hello", null, "e"),
                Arguments.of("hello", "", "e"),
                Arguments.of(null, "a", "b"),
                Arguments.of("ab", "a", "abc"),
                Arguments.of("ab", "c", "b"),
                Arguments.of("ab", "a", "c")
        );
    }

    @ParameterizedTest
    @MethodSource("correctCases")
    public void testCaseCorrect(String s, String o, String c, String[] expected) {
        assertThat(DelftCaseUtilities.substringsBetween(s, o, c)).containsExactly(expected);
    }

    public static Stream<Arguments> correctCases() {
        return Stream.of(
                Arguments.of("ahellocjoeabyec", "a", "c", new String[] { "hello", "bye" }),
                Arguments.of("ahellocabyec", "a", "c", new String[] { "hello", "bye" }),
                Arguments.of("ahellocdefacdef", "a", "c",  new String[] { "hello", "" }),
                Arguments.of("abchellodeftestabcbyedefbctestdefabctestdeghi", "abc", "def",  new String[] { "hello", "bye" })
        );
    }

    @Test
    public void testEmpty() {
        assertThat(DelftCaseUtilities.substringsBetween("", "a", "b")).isEmpty();
    }

}
