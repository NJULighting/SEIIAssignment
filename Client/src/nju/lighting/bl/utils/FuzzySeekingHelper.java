package nju.lighting.bl.utils;


import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2017/12/28.
 * Description:
 * @author Liao
 */
public class FuzzySeekingHelper<PO, VO> {
    private List<DataServiceFunction<String, List<PO>>> stringFunctions = new ArrayList<>();
    private List<DataServiceFunction<Integer, List<PO>>> integerFunctions = new ArrayList<>();
    private final Function<PO, VO> transformer;

    public FuzzySeekingHelper(Function<PO, VO> transformer) {
        this.transformer = transformer;
    }

    @SafeVarargs
    public final void registerFunctionForString(DataServiceFunction<String, List<PO>>... function) {
        Collections.addAll(stringFunctions, function);
    }

    @SafeVarargs
    public final void registerFunctionForInteger(DataServiceFunction<Integer, List<PO>>... function) {
        Collections.addAll(integerFunctions, function);
    }

    public List<VO> executeSeeking(String keyword) {
        Set<VO> itemSet = new HashSet<>();
        stringFunctions.forEach(f -> itemSet.addAll(DataServiceFunction.findByToList(keyword, f, transformer)));

        // Try to transform it into integers and then execute the functions in integerFunctions
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(keyword);

        if (matcher.matches()) {
            int key = Integer.parseInt(keyword);
            integerFunctions.forEach(f -> itemSet.addAll(DataServiceFunction.findByToList(key, f, transformer)));
        }

        List<VO> result = new ArrayList<>();
        result.addAll(itemSet);
        return result;
    }
}
