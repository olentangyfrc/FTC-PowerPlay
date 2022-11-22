package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareDevice;

@TeleOp (name="GenerateHardwareMap", group="Utilities")
public class GenerateHardwareConfig extends OpMode {
    @Override
    public void init() {
        HardwareDevice device = null;
        StringBuffer line = new StringBuffer();
        String name = null;
        String className = null;

        for (HardwareDevice hardwareDevice : hardwareMap.unsafeIterable()) {
            line.setLength(0);
            device = hardwareDevice;

            name = hardwareMap.getNamesOf(device).iterator().next();
            className = device.getClass().getName();
            className = className.substring(className.lastIndexOf('.') + 1);

            // ignore Control Hub stuff
            if (!name.startsWith("Control Hub")) {
                line.append('[').append(name)
                        .append("][")
                        .append(className)
                        .append("][")
                        .append(device.getConnectionInfo()).append(']');

                telemetry.addData("", line.toString());
            }
        }

        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addData("Hardware map is generated", "You can stop now");
        telemetry.update();
    }

    @Override
    public void stop() {
        // finish writing config file
    }
}
