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
    static <C, VO, PO> List<VO> findByToList(C condition, DataServiceFunction<C, List<PO>> function, Function<PO, VO> poTransformer) {
        try {
            List<PO> poList = function.apply(condition);
            return CollectionTransformer.toList(poList, poTransformer);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    static <C, VO, PO> VO findByToEntity(C condition, DataServiceFunction<C, PO> function, Function<PO, VO> poTransformer) {
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
