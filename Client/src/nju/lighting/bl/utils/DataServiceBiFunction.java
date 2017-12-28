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
public interface DataServiceBiFunction<T, R, Y> {
    static <C1, C2, VO, PO> List<VO> findToList(C1 condition1, C2 condition2,
                                                DataServiceBiFunction<C1, C2, List<PO>> biFunction,
                                                Function<PO, VO> poTransformer) {
        try {
            List<PO> poList = biFunction.apply(condition1, condition2);
            return ListTransformer.toList(poList, poTransformer);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

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

    static <Target1, Target2, Result> TwoTuple<ResultMessage, Result> commit(Target1 target1, Target2 target2,
                                                                    DataServiceBiFunction<Target1, Target2, TwoTuple<ResultMessage, Result>> function) {
        TwoTuple<ResultMessage, Result> commitResult = new TwoTuple<>();
        commitResult.t = ResultMessage.FAILURE;

        try {
            TwoTuple<ResultMessage, Result> res = function.apply(target1, target2);
            if (res.t == ResultMessage.SUCCESS) {
                commitResult.t = res.t;
                commitResult.r = res.r;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return commitResult;
    }

    Y apply(T t, R r) throws RemoteException;
}
