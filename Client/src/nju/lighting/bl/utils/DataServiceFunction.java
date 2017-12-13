package nju.lighting.bl.utils;

import java.rmi.RemoteException;

/**
 * Created on 2017/12/13.
 * Description:
 * @author Liao
 */
@FunctionalInterface
public interface DataServiceFunction<T, R> {
    R apply(T t) throws RemoteException;
}
