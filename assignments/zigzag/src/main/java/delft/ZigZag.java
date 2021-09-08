package delft;

import java.util.*;
import java.util.stream.Collectors;

public class ZigZag {

    private ZigZag() {}

    public static String zigzag(String s, int numRows) {
        if (s.length() < 1 || s.length() > 1000)
            throw new IllegalArgumentException("Length of s should be between 1 and 1000.");

        if (numRows < 1 || numRows > 1000)
            throw new IllegalArgumentException("numRows should be between 1 and 1000.");

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);

            boolean topOrBottom = curRow == 0 || curRow == numRows - 1;

            if (!goingDown && !topOrBottom) {
                for (int i = 0; i < rows.size(); i++) {
                    if (i != curRow)
                        rows.get(i).append(" ");
                }
            }

            if (topOrBottom) goingDown = !goingDown;

            curRow += (goingDown ? 1 : -1);
        }

        return rows
                .stream()
                .map(x -> x.toString().trim())
                .collect(Collectors.joining("\n"))
                .trim();
    }

}
