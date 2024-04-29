package put.io.patterns.implement;

import java.util.ArrayList;
import java.util.List;

public class SystemState {
    // CPU load in percentage
    private double cpu = 0.0;

    // CPU temperature in C
    private double cpuTemp = 0.0;

    // available memory in Mega Bytes
    private double memory = 0.0;

    // number of USB devices
    private int usbDevices = 0;


    public SystemState(double cpu, double cpuTemp, double memory, int usbDevices) {
        this.cpu = cpu;
        this.cpuTemp = cpuTemp;
        this.memory = memory;
        this.usbDevices = usbDevices;
    }

    public double getCpu() {
        return cpu;
    }

    public double getAvailableMemory() {
        return memory;
    }


    public int getUsbDevices() {
        return usbDevices;
    }


    public double getCpuTemp() {
        return cpuTemp;
    }
}
