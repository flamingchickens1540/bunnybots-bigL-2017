package org.team1540.liam2019.commands.auto;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.liam2019.Robot;

public class DriveForTime extends TimedCommand {

    private double leftPercent;
    private double rightPercent;

    public DriveForTime(double leftPercent, double rightPercent, double time) {
        super(time);
        this.leftPercent = leftPercent;
        this.rightPercent = rightPercent;
        requires(Robot.driveTrain);
    }

    public DriveForTime(double percent, double time) {
        this(percent, percent, time);
    }

    @Override
    protected void execute() {
        double leftMotors = leftPercent;
        double rightMotors = rightPercent;
        Robot.driveTrain.setPercent(leftMotors, rightMotors);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
