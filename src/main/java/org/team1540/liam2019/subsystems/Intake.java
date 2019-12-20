package org.team1540.liam2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.liam2019.Hardware;

public class Intake extends Subsystem {

    public void set(double state) {
        Hardware.leftIntake.set(state);
    }

    @Override
    protected void initDefaultCommand() {
    }
}
