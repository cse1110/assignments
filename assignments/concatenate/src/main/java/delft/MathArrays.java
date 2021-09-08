package delft;

import java.util.*;
import java.util.stream.Collectors;

public class MathArrays {

    private MathArrays() {}

    /**
     * Concatenates a sequence of arrays. The return array consists of the entries
     * of the input arrays concatenated in the order they appear in the argument
     * list. Null arrays cause NullPointerExceptions; zero length arrays are allowed
     * (contributing nothing to the output array).
     *
     * @param xss
     *            list of double[] arrays to concatenate
     * @return a new array consisting of the entries of the argument arrays
     * @throws NullPointerException
     *             if any of the arrays are null
     */
    public static double[] concatenate(double[]... xss) {
        int combinedLength = 0, offset = 0;
        for (double[] xs : xss)
            combinedLength += xs.length;

        final double[] combined = new double[combinedLength];
        for (double[] xs : xss) {
            System.arraycopy(xs, 0, combined, offset, xs.length);
            offset += xs.length;
        }

        return combined;
    }

}
