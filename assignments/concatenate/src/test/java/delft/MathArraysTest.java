package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class MathArraysTest {

    // Create tests here

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
