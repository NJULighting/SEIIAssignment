package nju.lighting.bl.utils;

import shared.Result;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/13.
 * Description:
 * @author Liao
 */
@FunctionalInterface
public interface DataServiceFunction<T, R> {
    /**
     * Invoke a function of the <tt>DataService</tt> which need one parameter to get an object
     * of the type <tt>PO</tt>, then the the Function <tt>poTransformer</tt> will transform the
     * original object to the object of the type <tt>VO</tt>
     * @param condition     parameter to be passed to the function of DataService
     * @param function      function of DataService
     * @param poTransformer function to transform object returned by DataService to object you want
     * @param <C>           type of the DataService function's parameter
     * @param <VO>          the object of the type you want
     * @param <PO>          original object returned by DataService
     * @return list of VO, or an empty list returned by <tt>Collections.emptyList()</tt>
     */
    static <C, VO, PO> List<VO> findByToList(C condition, DataServiceFunction<C, List<PO>> function, Function<PO, VO> poTransformer) {
        try {
            List<PO> poList = function.apply(condition);
            return CollectionTransformer.toList(poList, poTransformer);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Invoke a function of the <tt>DataService</tt> which need one parameter to get an object
     * of the type <tt>PO</tt>, then the the Function <tt>poTransformer</tt> will transform the
     * original object to the object the type <tt>VO</tt>. If the function of DataService finds
     * no result, this method will throw a <tt>NoSuchElementException</tt>
     * @param condition     parameter to be passed to the function of DataService
     * @param function      function of DataService
     * @param poTransformer function to transform object returned by DataService to object you want
     * @param <C>           type of the DataService function's parameter
     * @param <VO>          the object of the type you want
     * @param <PO>          original object returned by DataService
     * @return list of <tt>VO</tt>
     * @throws NoSuchElementException if the DataService's function find no result
     * @throws IllegalStateException if the network fails
     */
    static <C, VO, PO> VO findToEntity(C condition, DataServiceFunction<C, PO> function, Function<PO, VO> poTransformer) {
        try {
            PO po = function.apply(condition);
            if (po == null)
                throw new NoSuchElementException();
            return poTransformer.apply(po);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new IllegalStateException("Net work fail");
        }
    }

    /**
     * Invoke a function of the <tt>DataService</tt> which need one parameter to get an object
     * of the type <tt>PO</tt>, then the the Function <tt>poTransformer</tt> will transform the
     * original object to the object with the type <tt>VO</tt> and the <tt>Predicate</tt> you
     * passed will perform as a filter to filter the elements of the <tt>VO</tt> list
     * @param condition   parameter to be passed to the function of DataService
     * @param function    function of DataService
     * @param transformer function to transform object returned by DataService to object you want
     * @param filter      predicate to filter the elements
     * @param <C>         type of the DataService function's parameter
     * @param <VO>        the object of the type you want
     * @param <PO>        original object returned by DataService
     * @return list of VO, or an empty list returned by <tt>Collections.emptyList()</tt>
     */
    static <C, VO, PO> List<VO> findAndFilterToList(C condition, DataServiceFunction<C, List<PO>> function,
                                                    Function<PO, VO> transformer, Predicate<VO> filter) {
        try {
            return function.apply(condition).stream()
                    .map(transformer).filter(filter).collect(Collectors.toList());
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
     * @param target object to be inserted or committed to the database
     * @param function DataService's function to implement the insertion
     * @param <Target> type of the object to be inserted to the database
     * @param <Ret> type of the <tt>Result</tt>'s value
     * @return a <tt>Result</tt> object
     */
    static <Target, Ret> Result<Ret> addToDataBase(Target target, DataServiceFunction<Target, Result<Ret>> function) {
        try {
            return function.apply(target);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new Result<>(ResultMessage.NETWORK_FAIL, null);
        }
    }

    R apply(T t) throws RemoteException;
}
