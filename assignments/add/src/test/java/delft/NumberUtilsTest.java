package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class NumberUtilsTest {

    // Create tests here

    /**
     * Helper method to create a list of Integers.
     *
     * @param nums Array of numbers to put in a list.
     * @return List containing all the numbers of the input array.
     */
    private static List<Integer> numbers(int... nums) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums)
            list.add(n);
        return list;
    }

}
