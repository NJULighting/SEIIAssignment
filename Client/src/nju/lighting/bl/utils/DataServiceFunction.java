package nju.lighting.bl.utils;

import shared.ResultMessage;
import shared.TwoTuple;

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
                return null;
            return poTransformer.apply(po);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    static <C, PO> PO findByToEntity(C condition, DataServiceFunction<C, PO> function) {
        try {
            PO po = function.apply(condition);
            if (po == null)
                return null;
            return po;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
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

    static <Target, Result> TwoTuple<ResultMessage, Result> commit(Target target,
                                                                   DataServiceFunction<Target, TwoTuple<ResultMessage, Result>> function) {
        TwoTuple<ResultMessage, Result> commitResult = new TwoTuple<>();
        commitResult.t = ResultMessage.FAILURE;

        try {
            TwoTuple<ResultMessage, Result> res = function.apply(target);
            if (res.t == ResultMessage.SUCCESS) {
                commitResult.t = res.t;
                commitResult.r = res.r;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return commitResult;
    }

    R apply(T t) throws RemoteException;
}
