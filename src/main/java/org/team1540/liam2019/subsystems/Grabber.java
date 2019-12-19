package org.team1540.liam2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.liam2019.Hardware;

public class Grabber extends Subsystem {

    public void setGrabbed(boolean state) {
        Hardware.arms.set(state);
    }

    public boolean isGrabbed() {
        return Hardware.arms.get();
    }

    public void toggleGrabbed() {
        setGrabbed(!isGrabbed());
    }

    @Override
    protected void initDefaultCommand() {
    }
}
