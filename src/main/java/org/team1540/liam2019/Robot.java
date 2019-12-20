package org.team1540.liam2019;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.liam2019.commands.wrist.MoveWrist;
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
        SmartDashboard.putNumber("micahGank/navx", Hardware.navx.getYawRadians());
        SmartDashboard.putNumber("wrist", wrist.getPosition());
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

    @Override
    public void autonomousInit() {
        Hardware.wrist.setSelectedSensorPosition(0);
        new MoveWrist(MoveWrist.WristPosition.DOWN);
    }
}
