package org.team1540.liam2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.liam2019.Hardware;

public class Intake extends Subsystem {

    public void set(double state) {
        Hardware.leftIntake.set(state);
    }

    public double getCurrent() {
        return (Hardware.leftIntake.getOutputCurrent() + Hardware.rightIntake.getOutputCurrent())/2;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
