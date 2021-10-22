Apply **domain testing** and **structural testing** to the method below. Automate the test cases as JUnit5 test methods.

This time you _do not_ have to deliver the equivalence and boundary analysis table. You do, however, need to add explanatory comments at the top of every JUnit method (or `ParameterizedTest` argument) that explains what partition it is about.

You should achieve 100% branch+condition coverage.

Method under test: `static String replace(final String text, String searchString, final String replacement, int max, final boolean ignoreCase)` from `exam.DelftStringUtilities`

Source code of the entire class: https://gist.github.com/mauricioaniche/a332477c1c8e8268a025ab9bf6539504#file-delftstringutilities-java-L4859

Snippet of the method you should test:

```java
/**
 * <p>Replaces a String with another String inside a larger String,
 * for the first {@code max} values of the search String,
 * case sensitively/insensitively based on {@code ignoreCase} value.</p>
 *
 * <p>A {@code null} reference passed to this method is a no-op.</p>
 *
 *
 * @param text  text to search and replace in, may be null
 * @param searchString  the String to search for (case insensitive), may be null
 * @param replacement  the String to replace it with, may be null
 * @param max  maximum number of values to replace, or {@code -1} if no maximum
 * @param ignoreCase if true replace is case insensitive, otherwise case sensitive
 * @return the text with any replacements processed,
 *  {@code null} if null String input
 */
public static String replace(final String text, String searchString, final String replacement, int max, final boolean ignoreCase) {
    if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
        return text;
    }
    if (ignoreCase) {
        searchString = searchString.toLowerCase();
    }
    int start = 0;
    int end = ignoreCase ? indexOfIgnoreCase(text, searchString, start) : indexOf(text, searchString, start);
    if (end == INDEX_NOT_FOUND) {
        return text;
    }
    final int replLength = searchString.length();
    int increase = replacement.length() - replLength;
    increase = increase < 0 ? 0 : increase;
    increase *= max < 0 ? 16 : max > 64 ? 64 : max;
    final StringBuilder buf = new StringBuilder(text.length() + increase);
    while (end != INDEX_NOT_FOUND) {
        buf.append(text, start, end).append(replacement);
        start = end + replLength;
        if (--max == 0) {
            break;
        }
        end = ignoreCase ? indexOfIgnoreCase(text, searchString, start) : indexOf(text, searchString, start);
    }
    buf.append(text, start, text.length());
    return buf.toString();
}
```

Examples:

* `replace("abAa", "a", "z", -1, true)` returns `"zbzz"`
* `replace("abAa", "a", "z", 2, true)` returns `"zbza"`



Tips:

* Write your test code in the _Solution_ tab. Make sure the code compiles and all tests pass.