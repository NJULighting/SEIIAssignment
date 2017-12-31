package nju.lighting.data.analysis;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created on 2017/12/31.
 * Description:
 *
 * @author iznauy
 */
public class Analysis {

    private static class PythonExecutor extends TimerTask {

        @Override
        public void run() {
            String command = "python " + "/Users/iznauy/SEIIAssignment/python/analysis/main.py";
            System.out.println(command);
            try {
                Process process = Runtime.getRuntime().exec(command);
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Timer timer;

    public Analysis() {
        timer = new Timer();
        timer.schedule(new PythonExecutor(), 0L, 3600 * 24 * 1000);
    }

    public Timer getTimer() {
        return timer;
    }
}
