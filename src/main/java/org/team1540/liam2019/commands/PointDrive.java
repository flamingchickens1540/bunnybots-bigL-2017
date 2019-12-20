package org.team1540.liam2019.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.liam2019.Hardware;
import org.team1540.liam2019.OI;
import org.team1540.liam2019.Robot;
import org.team1540.liam2019.Utils;
import org.team1540.rooster.Utilities;

public class PointDrive extends PIDCommand {

    private double goalAngle;

    public PointDrive() {
        super(SmartDashboard.getNumber("micahGank/P", 0), SmartDashboard.getNumber("micahGank/I", 0), SmartDashboard.getNumber("micahGank/D", 0));
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        getPIDController().setPID(SmartDashboard.getNumber("micahGank/P", 0), SmartDashboard.getNumber("micahGank/I", 0), SmartDashboard.getNumber("micahGank/D", 0));
        setSetpoint(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected double returnPIDInput() {
        return Utils.signedAngleError(getAngleR(), Hardware.gyro.getYawRadians());
    }

    @Override
    protected void usePIDOutput(double output) {
        double leftThrottle = Utilities.processDeadzone(OI.driver.getY(GenericHID.Hand.kLeft), 0.1);
        Robot.driveTrain.setThrottle(-output - leftThrottle, output - leftThrottle);
        System.out.println("drive PID output used");
    }

    @Override
    protected void end() {
//        Robot.driveTrain.brake();
    }

    //returns an updated value if the joystick is not in the center
    public double getAngleR() {
//        if (!(driver.getY(GenericHID.Hand.kRight) < 0.1 && driver.getX(GenericHID.Hand.kRight) < 0.1)) {
//            joyAngleR = Math.atan2(driver.getY(GenericHID.Hand.kRight), driver.getX(GenericHID.Hand.kRight));
//        }
//        return joyAngleR;

        if(OI.driver.get2DJoystickMagnitude(GenericHID.Hand.kRight) > 0.1) this.goalAngle = OI.driver.get2DJoystickAngle(GenericHID.Hand.kRight);

        return this.goalAngle;
    }

    @Override
    protected void execute() {
        SmartDashboard.putNumber("micahGank/target", getAngleR());
    }
}
