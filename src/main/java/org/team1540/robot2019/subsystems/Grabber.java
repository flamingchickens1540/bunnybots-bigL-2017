package org.team1540.robot2019.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.robot2019.Hardware;

public class Grabber extends Subsystem {

    public void set(boolean state) {
        Hardware.arms.set(state);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
