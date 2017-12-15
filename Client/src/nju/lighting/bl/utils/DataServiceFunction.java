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
public interface DataServiceFunction<T, R> {
    R apply(T t) throws RemoteException;

    static <C, VO, PO> List<VO> findByToList(C condition, DataServiceFunction<C, List<PO>> function, Function<PO, VO> poTransformer) {
        try {
            List<PO> poList = function.apply(condition);
            return VPOTransformer.toVPOList(poList, poTransformer);
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
}
