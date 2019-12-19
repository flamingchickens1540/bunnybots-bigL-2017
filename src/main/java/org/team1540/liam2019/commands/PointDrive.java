package org.team1540.liam2019.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.OI;
import org.team1540.liam2019.Robot;
import org.team1540.liam2019.Utils;

public class PointDrive extends PIDCommand {

    public PointDrive() {
        super(0.1, 0, 0.1);
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        setSetpoint(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected double returnPIDInput() {
        return Utils.signedAngleError(OI.getAngleR(), Math.toRadians(Hardware.gyro.getAngle()));
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.driveTrain.setThrottle(-output + OI.getThrottleL(), output + OI.getThrottleL());
        System.out.println("drive PID output used");
    }

    @Override
    protected void end() {
//        Robot.driveTrain.brake();
    }
}
