package nju.lighting.bl.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/10.
 * Description:
 * @author Liao
 */
public class CollectionTransformer {
    // Suppress instantiation
    private CollectionTransformer() {
        throw new AssertionError();
    }

    /**
     * Transform a collection of <tt>IN</tt> objects to collections of <tt>OUT</tt> objects
     * @param collection collection of <tt>IN</tt> objects
     * @param function   function to implement the transforming work
     * @param <IN>       type of original object
     * @param <OUT>      type of target object
     * @return list of target object
     */
    public static <IN, OUT> List<OUT> toList(Collection<IN> collection, Function<IN, OUT> function) {
        return collection.stream().map(function).collect(Collectors.toList());
    }

    /**
     * Transform a collection of <tt>IN</tt> objects to collections of <tt>OUT</tt> objects
     * @param collection collection of <tt>IN</tt> objects
     * @param function   function to implement the transforming work
     * @param filter     filter for <tt>IN</tt> objects
     * @param <IN>       type of original object
     * @param <OUT>      type of target object
     * @return list of target object
     */
    public static <IN, OUT> List<OUT> filterToList(Collection<IN> collection, Function<IN, OUT> function, Predicate<IN> filter) {
        return collection.stream().filter(filter).map(function).collect(Collectors.toList());
    }
}
