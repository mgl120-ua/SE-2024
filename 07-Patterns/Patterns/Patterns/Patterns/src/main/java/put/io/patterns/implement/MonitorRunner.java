package put.io.patterns.implement;

public class MonitorRunner {

    public static void main(String args[]){

        // first create the monitor
        SystemMonitor monitor = new SystemMonitor();
        // create the observer and add it to the monitor
        SystemStateObserver infObserver = new SystemInfoObserver();
        monitor.addSystemStateObserver(infObserver);
        while (true) {
            monitor.probe();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
