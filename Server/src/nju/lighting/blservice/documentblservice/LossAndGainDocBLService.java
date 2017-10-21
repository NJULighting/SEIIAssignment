package nju.lighting.blservice.documentblservice;

import nju.lighting.po.LossAndGainDocPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LossAndGainDocBLService extends Remote {

    boolean addLossAndGainDoc(LossAndGainDocPO lossAndGainDocPO) throws RemoteException;
}
