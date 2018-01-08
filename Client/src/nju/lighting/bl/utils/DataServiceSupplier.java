package nju.lighting.bl.utils;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created on 2017/12/13.
 * Description:
 * @author Liao
 */
@FunctionalInterface
public interface DataServiceSupplier<T> {

    /**
     * Invoke a parameter-less function of the <tt>DataService</tt> to get a list of object,
     * then the the Function <tt>poTransformer</tt> will transform the list of original object to the
     * list of the objects you want.
     * @param supplier      function of DataService with no parameters
     * @param poTransformer function to transform object returned by DataService to object you want
     * @param <VO>          the object of the type you want
     * @param <PO>          original object returned by DataService
     * @return list of the objects you want
     */

    static <VO, PO> List<VO> getAll(DataServiceSupplier<List<PO>> supplier, Function<PO, VO> poTransformer) {
        try {
            List<PO> poList = supplier.get();
            return CollectionTransformer.toList(poList, poTransformer);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Invoke a parameter-less function of the <tt>DataService</tt> to get an object
     * of the type <tt>PO</tt>, then the the Function <tt>poTransformer</tt> will transform the
     * original object to the object with the type <tt>VO</tt> and the <tt>Predicate</tt> you
     * passed will perform as a filter to filter the elements of the <tt>VO</tt> list
     * @param supplier    function of DataService
     * @param transformer function to transform object returned by DataService to object you want
     * @param filter      predicate to filter the elements
     * @param <VO>        the object of the type you want
     * @param <PO>        original object returned by DataService
     * @return list of VO, or an empty list returned by <tt>Collections.emptyList()</tt>
     */
    static <VO, PO> List<VO> getAllAndFilter(DataServiceSupplier<List<PO>> supplier, Function<PO, VO> transformer, Predicate<PO> filter) {
        try {
            List<PO> poList = supplier.get();
            return CollectionTransformer.filterToList(poList, transformer, filter);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    T get() throws RemoteException;
}
