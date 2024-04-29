package put.io.patterns.implement;

public class USBDeviceObserver implements SystemStateObserver {
    private SystemState lastState;

    @Override
    public void update(SystemMonitor monitor) {
        SystemState newState = monitor.getLastSystemState();
        if (newState.getUsbDevices() != lastState.getUsbDevices()) {
            System.out.println("The number of USB devices have changed");
        }
        lastState = newState;
    }
}