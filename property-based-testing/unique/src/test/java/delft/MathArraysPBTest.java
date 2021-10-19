package delft;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MathArraysPBTest {

    @Property
    void unique() {
      // write your test here
    }

    /** Use this method to convert a list of integers to an array */
    private double[] convertListToArray(List<Double> numbers) {
        double[] array = numbers.stream().mapToDouble(x -> x).toArray();
        return array;
    }
}