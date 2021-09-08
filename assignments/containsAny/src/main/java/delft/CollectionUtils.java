package delft;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionUtils {

    private CollectionUtils() {}

    /**
     * Returns true if and only if at least one element is in both collections.
     *
     * @param coll1
     *            the first collection, must not be null
     * @param coll2
     *            the second collection, must not be null
     * @return true if and only if the intersection of the collections is non-empty.
     */
    public static boolean containsAny(final Collection<?> coll1, final Collection<?> coll2) {
        if (coll1.size() < coll2.size()) {
            for (final Object aColl1 : coll1) {
                if (coll2.contains(aColl1)) {
                    return true;
                }
            }
        } else {
            for (final Object aColl2 : coll2) {
                if (coll1.contains(aColl2)) {
                    return true;
                }
            }
        }
        return false;
    }

}
