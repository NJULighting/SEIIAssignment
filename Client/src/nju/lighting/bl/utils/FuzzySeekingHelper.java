package nju.lighting.bl.utils;


import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2017/12/28.
 * Description: This class is a helper class to cope with the fuzzy searching.
 * Every instance of the class contains two lists of functions which provided
 * by some DataService, one of them is to store functions which receive a
 * <tt>String</tt> as a parameter and the other is to store functions which
 * receive a <tt>Integer</tt> as a parameter. User should invoke corresponding
 * method to register fuzzy searching methods provided by DataService, and invoke
 * <tt>executeSeeking</tt> to get the result
 * @author Liao
 */
public class FuzzySeekingHelper<PO, VO> {
    private final Function<PO, VO> transformer;
    private List<DataServiceFunction<String, List<PO>>> stringFunctions = new ArrayList<>();
    private List<DataServiceFunction<Integer, List<PO>>> integerFunctions = new ArrayList<>();

    public FuzzySeekingHelper(Function<PO, VO> transformer) {
        this.transformer = transformer;
    }

    /**
     * Register a fuzzy seeking method which received a <tt>String</tt> as a parameter
     * provided by the DataService to the helper.
     * @param function method to be registered
     */
    @SafeVarargs
    public final void registerFunctionForString(DataServiceFunction<String, List<PO>>... function) {
        Collections.addAll(stringFunctions, function);
    }

    /**
     * Register a fuzzy seeking method which received a <tt>Integer</tt> as a parameter
     * provided by the DataService to the helper.
     * @param function method to be registered
     */
    @SafeVarargs
    public final void registerFunctionForInteger(DataServiceFunction<Integer, List<PO>>... function) {
        Collections.addAll(integerFunctions, function);
    }

    /**
     * Execute fuzzy seeking with the keyword you passed.
     * @param keyword keyword of this fuzzy seeking
     * @return list of <tt>VO</tt> objects which match the keyword
     */
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
