package org.team1540.liam2019;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.team1540.liam2019.subsystems.DriveTrain;
import org.team1540.liam2019.subsystems.Grabber;
import org.team1540.liam2019.subsystems.Intake;
import org.team1540.liam2019.subsystems.IntakeWrist;

public class Robot extends TimedRobot {

    public static DriveTrain driveTrain = new DriveTrain();
    public static Grabber grabber = new Grabber();
    public static Intake intake = new Intake();
    public static IntakeWrist intakeWrist = new IntakeWrist();

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void robotInit() {
        Hardware.init();
        OI.init();
    }

    @Override
    public void teleopPeriodic() {
    }
}
