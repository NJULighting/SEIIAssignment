package nju.lighting.bl.logbl;

import shared.OPType;

/**
 * Created on 2017/11/7.
 * Description:
 * @author Liao
 */
public class MockLogger implements Logger {
    @Override
    public void add(OPType type, String itemName, String id) {
        System.out.println("Log other succeed");
    }

    @Override
    public void add(OPType type, Describable description) {
        System.out.println("Log describable succeed");
    }
}
