package org.team1540.liam2019;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.liam2019.subsystems.DriveTrain;
import org.team1540.liam2019.subsystems.Grabber;
import org.team1540.liam2019.subsystems.Intake;
import org.team1540.liam2019.subsystems.Wrist;

public class Robot extends TimedRobot {

    public static DriveTrain driveTrain = new DriveTrain();
    public static Grabber grabber = new Grabber();
    public static Intake intake = new Intake();
    public static Wrist wrist = new Wrist();

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("micahGank/gyro", Hardware.gyro.getYawRadians());
    }

    @Override
    public void robotInit() {
        Hardware.init();
        OI.init();
        SmartDashboard.putNumber("micahGank/P", 0.2);
        SmartDashboard.putNumber("micahGank/I", 0.0);
        SmartDashboard.putNumber("micahGank/D", 0.2);
    }

    @Override
    public void teleopPeriodic() {
    }
}
