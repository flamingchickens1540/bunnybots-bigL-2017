package org.team1540.liam2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.commands.drivetrain.TankDrive;

public class Drivetrain extends Subsystem {

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

    public void setPercent(double leftPercent, double rightPercent) {
        Hardware.leftA.set(leftPercent);
        Hardware.rightA.set(rightPercent);
    }

    public double getLeftPos() {
        return Hardware.leftA.getEncoder().getPosition();
    }

    public double getRightPos() {
        return Hardware.rightA.getEncoder().getPosition();
    }
}
