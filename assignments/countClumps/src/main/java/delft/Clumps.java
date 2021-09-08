package delft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Clumps {

    private Clumps() {}

    /**
     * Method to count the number of clumps in an array of numbers,
     * where a clump is a sequence of consecutive elements with the
     * same value.
     *
     * @param nums
     *          Array to count the number of clumps for.
     * @return the number of clumps in the input array.
     */
    public static int countClumps(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int count = 0, prev = nums[0];
        boolean inClump = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev && !inClump) {
                inClump = true;
                count += 1;
            }

            if (nums[i] != prev) {
                prev = nums[i];
                inClump = false;
            }
        }

        return count;
    }

}
