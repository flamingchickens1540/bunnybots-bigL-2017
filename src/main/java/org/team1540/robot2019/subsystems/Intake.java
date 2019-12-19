package org.team1540.robot2019.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.robot2019.Hardware;

public class Intake extends Subsystem {

    //accepts -1,0,1
    public void set(int state) {
        Hardware.leftIntake.set(state);
    }

    public double getCurrent() {
        return (Hardware.leftIntake.getOutputCurrent() + Hardware.rightIntake.getOutputCurrent())/2;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
