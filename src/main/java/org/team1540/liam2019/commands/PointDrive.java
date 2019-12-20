package org.team1540.liam2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.liam2019.Robot;
import org.team1540.liam2019.Utils;
import org.team1540.liam2019.utils.ChickenXboxController;
import org.team1540.liam2019.utils.MiniPID;
import org.team1540.rooster.Utilities;
import org.team1540.rooster.util.SimpleCommand;

import static edu.wpi.first.wpilibj.GenericHID.Hand;
import static org.team1540.liam2019.Hardware.navx;
import static org.team1540.liam2019.OI.driver;

public class PointDrive extends Command {

    private double goalAngle;
    private MiniPID pointController;
    private double angleOffset;

    public PointDrive() {
        requires(Robot.driveTrain);

        SmartDashboard.putNumber("PointDrive/P", 2);
        SmartDashboard.putNumber("PointDrive/I", 0);
        SmartDashboard.putNumber("PointDrive/D", 0);

        pointController = new MiniPID(0, 0, 0);
        pointController.setOutputLimits(1);
        driver.getButton(ChickenXboxController.XboxButton.Y).whenPressed(new SimpleCommand("", this::zeroAngle));
    }

    @Override
    protected void initialize() {
        double p = SmartDashboard.getNumber("PointDrive/P", 0);
        double i = SmartDashboard.getNumber("PointDrive/I", 0);
        double d = SmartDashboard.getNumber("PointDrive/D", 0);
        pointController.setPID(p, i, d);
    }

    public void zeroAngle() {
        angleOffset = navx.getYawRadians();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void execute() {
        if (driver.get2DJoystickMagnitude(Hand.kRight) > 0.1) goalAngle = driver.get2DJoystickAngle(Hand.kRight);
        double angleOutput = pointController.getOutput(Utils.signedAngleError(goalAngle+angleOffset, navx.getYawRadians()));
        double throttle = Utilities.processDeadzone(driver.getRectifiedX(Hand.kLeft), 0.1);

        double leftMotors = throttle-angleOutput;
        double rightMotors = throttle+angleOutput;
        Robot.driveTrain.setThrottle(leftMotors, rightMotors);
    }
}
