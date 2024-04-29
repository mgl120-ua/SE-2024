package put.io.patterns.implement;

public class SystemInfoObserver implements SystemStateObserver{
    @Override
    public void update(SystemMonitor monitor) {
        SystemState state = monitor.getLastSystemState();
        System.out.println("Info:" + state.toString());
    }
}