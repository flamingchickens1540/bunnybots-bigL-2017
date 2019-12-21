package org.team1540.liam2019;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.liam2019.subsystems.Drivetrain;
import org.team1540.liam2019.subsystems.Grabber;
import org.team1540.liam2019.subsystems.Intake;
import org.team1540.liam2019.subsystems.Wrist;

public class Robot extends TimedRobot {

    public static Drivetrain driveTrain = new Drivetrain();
    public static Grabber grabber = new Grabber();
    public static Intake intake = new Intake();
    public static Wrist wrist = new Wrist();

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();

        SmartDashboard.putNumber("debug/yawRad", Hardware.navx.getYawRadians());
        SmartDashboard.putNumber("debug/wristPos", wrist.getPosition());
        SmartDashboard.putNumber("debug/driveTrainLeftPos", driveTrain.getLeftPos());
        SmartDashboard.putNumber("debug/driveTrainRightPos", driveTrain.getRightPos());
        SmartDashboard.putNumber("debug/intakeSensorVoltage", Hardware.intakeSensor.getVoltage());
    }

    @Override
    public void robotInit() {
        Hardware.init();
        OI.init();
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void autonomousInit() {
        Robot.wrist.zero();
    }
}
