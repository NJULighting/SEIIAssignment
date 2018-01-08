package nju.lighting.bl.utils;

import shared.Result;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/13.
 * Description:
 * @author Liao
 */
@FunctionalInterface
public interface DataServiceBiFunction<T, R, Y> {
    /**
     * Invoke a function of the <tt>DataService</tt> which need two parameters to get a list of object,
     * then the the Function <tt>poTransformer</tt> will transform the list of original object to the
     * list of the objects you want.
     * @param condition1    first parameter to be passed to the function of DataService
     * @param condition2    second parameter to be passed to the function of DataService
     * @param biFunction    function of DataService which need two parameters
     * @param poTransformer function to transform object returned by DataService to object you want
     * @param <C1>          type of the DataService function's parameter
     * @param <C2>          type of the DataService function's parameter
     * @param <VO>          the object of the type you want
     * @param <PO>          original object returned by DataService
     * @return list of the objects you want, or an empty list returned by <tt>Collections.emptyList()</tt>
     */

    static <C1, C2, VO, PO> List<VO> findToList(C1 condition1, C2 condition2,
                                                DataServiceBiFunction<C1, C2, List<PO>> biFunction,
                                                Function<PO, VO> poTransformer) {
        try {
            List<PO> poList = biFunction.apply(condition1, condition2);
            return CollectionTransformer.toList(poList, poTransformer);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Invoke a function of the <tt>DataService</tt> which need two parameters to get an object
     * of the type <tt>PO</tt>, then the the Function <tt>poTransformer</tt> will transform the
     * original object to the object with the type <tt>VO</tt> and the <tt>Predicate</tt> you
     * passed will perform as a filter to filter the elements of the <tt>VO</tt> list
     * @param condition1  first parameter to be passed to the function of DataService
     * @param condition2  second parameter to be passed to the function of DataService
     * @param biFunction  function of DataService
     * @param transformer function to transform object returned by DataService to object you want
     * @param filter      predicate to filter the elements
     * @param <C1>        type of the DataService function's first parameter
     * @param <C2>        type of the DataService function's second parameter
     * @param <VO>        the object of the type you want
     * @param <PO>        original object returned by DataService
     * @return list of VO, or an empty list returned by <tt>Collections.emptyList()</tt>
     */
    static <C1, C2, VO, PO> List<VO> findAndFilterToList(C1 condition1, C2 condition2,
                                                         DataServiceBiFunction<C1, C2, List<PO>> biFunction,
                                                         Function<PO, VO> transformer,
                                                         Predicate<VO> filter) {
        try {
            return biFunction.apply(condition1, condition2)
                    .stream().map(transformer).filter(filter).collect(Collectors.toList());
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Invoke a DataService's function to insert or commit an object of the type <tt>Target</tt>
     * to the data base. The return type of the function should be a <tt>Result</tt> which contains
     * an object of the type <tt>Ret</tt>. If the <tt>RemoteException</tt> is caught, the value of
     * the <tt>Result</tt> will be null
     * @param target1 first object to be inserted or committed to the database
     * @param target2 second object to be inserted or committed to the database
     * @param function DataService's function to implement the insertion
     * @param <Target1> type of the first object to be inserted to the database
     * @param <Target2> type of the second object to be inserted to the database
     * @param <Ret> type of the <tt>Result</tt>'s value
     * @return a <tt>Result</tt> object
     */
    static <Target1, Target2, Ret> Result<Ret> addToDataBase(Target1 target1, Target2 target2,
                                                             DataServiceBiFunction<Target1, Target2, Result<Ret>> function) {
        try {
            return function.apply(target1, target2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new Result<>(ResultMessage.NETWORK_FAIL, null);
        }
    }

    Y apply(T t, R r) throws RemoteException;
}
