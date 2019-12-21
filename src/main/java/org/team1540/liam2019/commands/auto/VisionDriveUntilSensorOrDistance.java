package org.team1540.liam2019.commands.auto;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.Robot;
import org.team1540.liam2019.utils.TrigUtils;

import static org.team1540.liam2019.Hardware.navx;

public class VisionDriveUntilSensorOrDistance extends TimedCommand {

    private double maxDistance;
    private double leftPercent;
    private double rightPercent;
    private double startDistance;
    private double minSensorVoltage;
    private double goalAngle;

    public VisionDriveUntilSensorOrDistance(double percentOutput, double maxDistance, double timeout, double minSensorVoltage) {
        super(timeout);
        this.leftPercent = percentOutput;
        this.rightPercent = percentOutput;
        this.maxDistance = maxDistance;
        this.minSensorVoltage = minSensorVoltage;
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        startDistance = Robot.driveTrain.getLeftPos();
        goalAngle = navx.getYawRadians();
    }

    @Override
    protected void execute() {
        double leftMotors = leftPercent;
        double rightMotors = rightPercent;

        if (Robot.limelight.isTargetFound()) {
            double limelight = NetworkTableInstance.getDefault().getTable("limelight-a").getEntry("tx").getDouble(0) / 100;
            leftMotors += limelight;
            rightMotors -= limelight;
        } else {
            double navxError = -TrigUtils.signedAngleError(goalAngle, navx.getYawRadians()) / 6;
            leftMotors += navxError;
            rightMotors -= navxError;
        }

        Robot.driveTrain.setPercent(leftMotors, rightMotors);
    }

    @Override
    protected boolean isFinished() {
        boolean reachedMaxDistance = Math.abs(Robot.driveTrain.getLeftPos() - startDistance) > maxDistance;
        boolean sensorTripped = Hardware.intakeSensor.getVoltage() < minSensorVoltage;
        return reachedMaxDistance || sensorTripped || isTimedOut();
    }

    @Override
    protected void end() {
        Robot.driveTrain.stop();
    }
}
