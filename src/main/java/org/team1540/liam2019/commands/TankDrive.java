package org.team1540.liam2019.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import org.team1540.liam2019.Robot;
import org.team1540.liam2019.utils.ChickenXboxController;
import org.team1540.rooster.Utilities;

import static org.team1540.liam2019.OI.driver;

public class TankDrive extends Command {

    public TankDrive() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void execute() {
        double leftJoystick = Utilities.processDeadzone(driver.getRectifiedX(GenericHID.Hand.kLeft), 0.1);
        double rightJoystick = Utilities.processDeadzone(driver.getRectifiedX(GenericHID.Hand.kRight), 0.1);
        double forward = Utilities.processDeadzone(driver.getRawAxis(ChickenXboxController.XboxAxis.LEFT_TRIG), 0.1);
        double reverse = Utilities.processDeadzone(driver.getRawAxis(ChickenXboxController.XboxAxis.RIGHT_TRIG), 0.1);

        double leftMotors = leftJoystick + forward - reverse;
        double rightMotors = rightJoystick + forward - reverse;
        Robot.driveTrain.setThrottle(leftMotors, rightMotors);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
