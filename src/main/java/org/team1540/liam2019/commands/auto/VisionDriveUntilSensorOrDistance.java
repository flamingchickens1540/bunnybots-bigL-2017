package org.team1540.liam2019.commands.auto;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.Robot;

public class VisionDriveUntilSensorOrDistance extends Command {

    private final double maxDistance;
    private double leftPercent;
    private double rightPercent;
    private double startDistance;
    private double minSensorVoltage;

    public VisionDriveUntilSensorOrDistance(double leftPercent, double maxDistance, double minSensorVoltage) {
        this.leftPercent = leftPercent;
        this.rightPercent = leftPercent;
        this.maxDistance = maxDistance;
        this.minSensorVoltage = minSensorVoltage;
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        startDistance = Robot.driveTrain.getLeftPos();
    }

    @Override
    protected void execute() {
        double leftMotors = leftPercent;
        double rightMotors = rightPercent;

        double limelight = NetworkTableInstance.getDefault().getTable("limelight-a").getEntry("tx").getDouble(0) / 100;
        System.out.println(limelight);
        leftMotors += limelight;
        rightMotors -= limelight;

        Robot.driveTrain.setPercent(leftMotors, rightMotors);
    }

    @Override
    protected boolean isFinished() {
        boolean reachedMaxDistance = Math.abs(Robot.driveTrain.getLeftPos() - startDistance) > maxDistance;
        boolean sensorTripped = Hardware.intakeSensor.getVoltage() < minSensorVoltage;
        return reachedMaxDistance || sensorTripped;
    }
}
