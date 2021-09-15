package delft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DelftCaseUtilities {

    private DelftCaseUtilities() {}

    /**
     * Searches a String for substrings delimited by a start and end tag, returning
     * all matching substrings in an array.
     *
     * A null input String returns null. A null open/close
     * returns null (no match). An empty ("") open/close returns null (no match).
     *
     * @param str
     *            the String containing the substrings, null returns null, empty returns empty.
     * @param open
     *            the String identifying the start of the substring, empty returns null.
     * @param close
     *            the String identifying the end of the substring, empty returns null.
     * @return a String Array of substrings, or {@code null} if no match
     */
    public static String[] substringsBetween(final String str, final String open, final String close) {
        if (str == null || open == null || open.length() == 0 || close == null || close.length() == 0)
            return null;

        final int strLen = str.length();
        if (strLen == 0)
            return new String[0];

        final int openLen  = open.length();
        final int closeLen = close.length();
        final List<String> list = new ArrayList<>();

        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0)
                break;

            start += openLen;

            final int end = str.indexOf(close, start);
            if (end < 0)
                break;

            list.add(str.substring(start, end));
            pos = end + closeLen;
        }

        return (list.isEmpty()) ? null : list.toArray(new String[0]);
    }

}
