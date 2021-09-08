package delft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberUtils {

    private NumberUtils() {}

    /**
     * This method receives two numbers, `left` and `right`, both represented as a
     * list of digits. It adds these numbers and returns the results also as a list
     * of digits.
     *
     * For example, if you want to add the numbers 23 and 42, you would need to
     * create a `left` list with two elements [2,3] and a `right` list with two
     * elements [4,2]. 23 + 42 = 65, so the program would produce another list with
     * two elements [6,5].
     *
     * [2,3] + [4,2] = [6,5]
     *
     * Each element in the left and right lists should be a number from [0-9]. An
     * `IllegalArgumentException` is throw in case the pre-condition does not hold.
     *
     * Leading zeroes are removed from the result, so [0,3] + [2] = [5], not [0,5].
     *
     * @param left
     *      a list containing the left number. `null` returns `null`, empty means 0.
     * @param right
     *      a list containing the right number. `null` returns `null`, empty means 0.
     * @return the sum of left and right, as a list.
     */
    public static List<Integer> add(List<Integer> left, List<Integer> right) {
        if (left == null || right == null)
            return null;

        List<Integer> reversedLeft  = new ArrayList<>(left);
        List<Integer> reversedRight = new ArrayList<>(right);
        Collections.reverse(reversedLeft);
        Collections.reverse(reversedRight);

        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;
        for (int i = 0; i < Math.max(reversedLeft.size(), reversedRight.size()); i++) {
            int leftDigit  = reversedLeft.size()  > i ? reversedLeft .get(i) : 0;
            int rightDigit = reversedRight.size() > i ? reversedRight.get(i) : 0;

            if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9)
                throw new IllegalArgumentException();

            int sum = leftDigit + rightDigit + carry;
            carry = sum / 10;

            result.addFirst(sum % 10);
        }

        result.addFirst(carry);

        while (result.size() > 1 && result.get(0) == 0)
            result.removeFirst();

        return result;
    }

}
