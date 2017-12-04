package nju.lighting.bl.logbl;

import shared.OPType;

/**
 * Created on 2017/11/7.
 * Description:
 * @author Liao
 */
public class MockLogger implements Logger {
    @Override
    public void add(OPType type, String message) {
        System.out.println("Log succeed");
    }

    @Override
    public void add(OPType type, Object object) {

    }
}
