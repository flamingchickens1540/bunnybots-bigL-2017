package org.team1540.liam2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.commands.PointDrive;
import org.team1540.liam2019.commands.TankDrive;

public class DriveTrain extends Subsystem {

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

    public void setThrottle(double leftThrottle, double rightThrottle) {
        Hardware.leftA.set(leftThrottle);
        Hardware.rightA.set(rightThrottle);
    }
}
