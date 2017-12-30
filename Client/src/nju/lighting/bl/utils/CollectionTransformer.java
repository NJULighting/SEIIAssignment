package nju.lighting.bl.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/10.
 * Description:
 * @author Liao
 */
public class CollectionTransformer {
    public static <IN, OUT> List<OUT> toList(Collection<IN> collection, Function<IN, OUT> function) {
        return collection.stream().map(function).collect(Collectors.toList());
    }

    public static <IN, OUT> Set<OUT> toSet(Collection<IN> collection, Function<IN, OUT> function) {
        return collection.stream().map(function).collect(Collectors.toSet());
    }

    // Suppress instantiation
    private CollectionTransformer() {
        throw new AssertionError();
    }
}
