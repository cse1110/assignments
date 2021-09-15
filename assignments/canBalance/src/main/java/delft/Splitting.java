package delft;

public class Splitting {

    private Splitting() {}

    /**
     * Given an array of at least two elements, return true if there is a place to split the array
     * such that the sum of the numbers on one side is equal to the sum of the numbers on the
     * other side.
     *
     * @param nums Input array, must be non-null and len(nums) > 1.
     * @return true if the input array can be balanced, as described above.
     */
    public static boolean canBalance(int[] nums) {
        if (nums == null || nums.length <= 1)
            return false;

        int sum = 0;
        for (int num : nums)
            sum += num;

        if (sum % 2 == 1)
            return false;

        int half = sum / 2;
        for (int i = 0; half > 0; i++) {
            half -= nums[i];
        }

        return (half == 0);
    }

}
