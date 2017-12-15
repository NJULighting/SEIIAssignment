package nju.lighting.bl.utils;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Created on 2017/12/13.
 * Description:
 * @author Liao
 */
@FunctionalInterface
public interface DataServiceBiFunction<T, R, Y> {
    Y apply(T t, R r) throws RemoteException;

    static <C1, C2, VO, PO> List<VO> findToList(C1 condition1, C2 condition2,
                                                DataServiceBiFunction<C1, C2, List<PO>> biFunction, Function<PO, VO> poTransformer) {
        try {
            List<PO> poList = biFunction.apply(condition1, condition2);
            return VPOTransformer.toVPOList(poList, poTransformer);
        } catch (RemoteException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
