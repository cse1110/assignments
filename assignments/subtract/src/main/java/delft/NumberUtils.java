package delft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberUtils {

    private NumberUtils() {}

    /**
     * This method receives two numbers, `left` and `right`, both represented as a
     * list of digits. It subtracts these numbers and returns the results also as
     * a list of digits.
     *
     * For example, if you want to subtract the numbers 42 and 23, you woudl need to
     * create a `left` list with two elements [4,2] and a `right` list with two
     * elements [2,3]. 42 - 23 = 19, so the program would produce another list with
     * two elements [1,9].
     *
     * [4,2] - [2,3] = [1,9]
     *
     * Each element in the left and right lists should be a number from [0-9]. The
     * method only supports cases when the left number is greater than or equal to
     * the right number, such that the result is always non-negative. An exception
     * is thrown if either of these pre-conditions does not hold.
     *
     * @param left
     *      a list containing the left number. `null` returns `null`, empty means 0.
     * @param right
     *      a list containing the right number. `null` returns `null`, empty means 0.
     * @return the difference between left and right, as a list.
     */
    public static List<Integer> subtract(List<Integer> left, List<Integer> right) {
        if (left == null || right == null)
            return null;

        ArrayList<Integer> reversedLeft  = new ArrayList<>(left);
        ArrayList<Integer> reversedRight = new ArrayList<>(right);
        Collections.reverse(reversedLeft);
        Collections.reverse(reversedRight);

        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;
        for (int i = 0; i < Math.max(reversedLeft.size(), reversedRight.size()); i++) {
            int leftDigit  = reversedLeft.size()  > i ? reversedLeft .get(i) : 0;
            int rightDigit = reversedRight.size() > i ? reversedRight.get(i) : 0;

            if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9)
                throw new IllegalArgumentException();

            int sub = leftDigit - carry - rightDigit;
            if (sub < 0) {
                sub += 10;
                carry = 1;
            } else {
                carry = 0;
            }

            result.addFirst(sub);
        }

        if (carry > 0)
            throw new IllegalArgumentException("Left is less than right.");

        while (result.size() > 0 && result.get(0) == 0)
            result.remove(0);

        if (result.isEmpty())
            result.addFirst(0);

        return result;
    }

}
