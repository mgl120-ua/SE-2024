package put.io.patterns.implement;

public class SystemCoolerObserver implements SystemStateObserver {
    @Override
    public void update(SystemMonitor monitor) {
        SystemState state = monitor.getLastSystemState();
        if (state.getCpuTemp() > 85) {
            activateCooling();
        }
    }

    private void activateCooling() {
        System.out.println("Start cooling");
    }
}

