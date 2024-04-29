package put.io.patterns.implement;

public class SystemGarbageCollectorObserver implements SystemStateObserver {
    @Override
    public void update(SystemMonitor monitor) {
        SystemState state = monitor.getLastSystemState();
        if (state.getAvailableMemory() < 100) {
            runGarbageCollector();
        }
    }

    private void runGarbageCollector() {
        System.gc();
        System.out.println("Garbage collector memory");
    }

}
