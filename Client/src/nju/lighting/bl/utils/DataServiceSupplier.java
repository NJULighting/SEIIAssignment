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

    static <VO, PO> List<VO> getAll(DataServiceSupplier<List<PO>> supplier, Function<PO, VO> poTransformer) {
        try {
            List<PO> poList = supplier.get();
            return CollectionTransformer.toList(poList, poTransformer);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

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
