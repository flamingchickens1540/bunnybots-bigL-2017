package org.team1540.liam2019.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.liam2019.OI;
import org.team1540.liam2019.Robot;
import org.team1540.liam2019.utils.ControlUtils;
import org.team1540.liam2019.utils.MiniPID;
import org.team1540.liam2019.utils.TrigUtils;
import org.team1540.rooster.Utilities;
import org.team1540.rooster.util.SimpleCommand;

import static edu.wpi.first.wpilibj.GenericHID.Hand;
import static org.team1540.liam2019.Hardware.navx;
import static org.team1540.liam2019.OI.driver;

public class PointDrive extends Command {

    private double goalAngle;
    private MiniPID pointController;
    private double angleOffset;
    private double max;
    private double min;
    private double deadzone;

    public PointDrive() {
        requires(Robot.driveTrain);

        SmartDashboard.putNumber("PointDrive/P", 0.5);
        SmartDashboard.putNumber("PointDrive/I", 0);
        SmartDashboard.putNumber("PointDrive/D", 0);
        SmartDashboard.putNumber("PointDrive/max", 0.5);
        SmartDashboard.putNumber("PointDrive/min", 0);
        SmartDashboard.putNumber("PointDrive/deadzone", 0.02);

        pointController = new MiniPID(0, 0, 0);
        pointController.setOutputLimits(1);
        OI.zeroPointDrive.whenPressed(new SimpleCommand("", this::zeroAngle));
    }

    @Override
    protected void initialize() {
        double p = SmartDashboard.getNumber("PointDrive/P", 0);
        double i = SmartDashboard.getNumber("PointDrive/I", 0);
        double d = SmartDashboard.getNumber("PointDrive/D", 0);
        max = SmartDashboard.getNumber("PointDrive/max", 0);
        min = SmartDashboard.getNumber("PointDrive/min", 0);
        deadzone = SmartDashboard.getNumber("PointDrive/deadzone", 0);
        pointController.setPID(p, i, d);
    }

    public void zeroAngle() {
        angleOffset = navx.getYawRadians();
        goalAngle = 0;
    }

    @Override
    protected void execute() {
        if (driver.get2DJoystickMagnitude(Hand.kRight) > 0.5) goalAngle = driver.get2DJoystickAngle(Hand.kRight);
        double error = TrigUtils.signedAngleError(goalAngle + angleOffset, navx.getYawRadians());
        SmartDashboard.putNumber("PointDrive/error", error);
        double rawPIDOutput = pointController.getOutput(error);
        double angleOutput = -ControlUtils.allVelocityConstraints(rawPIDOutput, max, min, deadzone);

        double throttle = Utilities.processDeadzone(driver.getRectifiedX(Hand.kLeft), 0.1);

        double leftMotors = throttle-angleOutput;
        double rightMotors = throttle+angleOutput;
        Robot.driveTrain.setPercent(leftMotors, rightMotors);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
