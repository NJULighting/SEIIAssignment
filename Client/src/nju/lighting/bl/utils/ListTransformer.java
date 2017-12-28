package nju.lighting.bl.utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/10.
 * Description:
 * @author Liao
 */
public class ListTransformer {
    public static <IN, OUT> List<OUT> toList(List<IN> list, Function<IN, OUT> function) {
        return list.stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
