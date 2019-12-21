package org.team1540.liam2019;


import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.liam2019.commands.auto.AxisButton;
import org.team1540.liam2019.commands.auto.NoVisionAuto;
import org.team1540.liam2019.commands.grabber.SetGrabber;
import org.team1540.liam2019.commands.intake.EjectBin;
import org.team1540.liam2019.commands.intake.SensorIntakeBin;
import org.team1540.liam2019.commands.intake.SetIntakeForTime;
import org.team1540.liam2019.commands.wrist.ManualWrist;
import org.team1540.liam2019.commands.wrist.MoveWrist;
import org.team1540.liam2019.commands.wrist.MoveWristToBottom;
import org.team1540.liam2019.utils.ChickenXboxController;
import org.team1540.liam2019.utils.SimpleCommand;
import org.team1540.rooster.triggers.DPadAxis;

public class OI {

    static double joyAngleR;

    public static ChickenXboxController driver = new ChickenXboxController(0);
    public static Button zeroPointDrive = driver.getButton(ChickenXboxController.XboxButton.Y);
    public static Button autoTest = driver.getButton(ChickenXboxController.XboxButton.A);


    public static ChickenXboxController copilot = new ChickenXboxController(1);
    public static Button toggleGrabber = copilot.getButton(ChickenXboxController.XboxButton.A);

    public static Button intakeBin = copilot.getButton(DPadAxis.DOWN);
    public static Button stowIntake = copilot.getButton(DPadAxis.LEFT);
    public static Button dumpBin = copilot.getButton(DPadAxis.UP);
    public static Button ejectBin = copilot.getButton(ChickenXboxController.XboxButton.B);

    public static Button enableManualWrist = new AxisButton(copilot, 0.15, ChickenXboxController.XboxAxis.LEFT_Y.value);
    public static Button zeroWrist = copilot.getButton(ChickenXboxController.XboxButton.BACK);

    public static void init() {
        initDriver();
        initCopilot();
    }

    private static void initDriver() {
        Command noVisionAuto = new NoVisionAuto();
        autoTest.whenPressed(noVisionAuto);
        autoTest.whenReleased(new SimpleCommand(noVisionAuto::cancel));

    }

    private static void initCopilot() {
        toggleGrabber.whenPressed(new SimpleCommand(() -> Robot.grabber.toggleGrabbed(), Robot.grabber));

        FloorIntake floorIntake = new FloorIntake();
        intakeBin.whenPressed(floorIntake);
        intakeBin.whenReleased(new SimpleCommand(floorIntake::cancel));

        dumpBin.whenPressed(new DumpBin());

        stowIntake.whenPressed(new CommandGroup() {{
            addSequential(new MoveWrist(MoveWrist.WristPosition.UP, 1));
        }});

        ejectBin.whileHeld(new EjectBin(1));

        enableManualWrist.whileHeld(new ManualWrist());
        zeroWrist.whenPressed(new SimpleCommand(() -> {
            Hardware.wrist.set(ControlMode.PercentOutput, 0);
            Hardware.wrist.setSelectedSensorPosition(0);
        }));
    }

    public static class FloorIntake extends CommandGroup {
        {
            addSequential(new SetGrabber(false));
            addParallel(new MoveWristToBottom());
            addSequential(new SensorIntakeBin(-0.5, 2));
            addSequential(new SetIntakeForTime(-1, 0.15));
            addSequential(new SetGrabber(true));
            addSequential(new MoveWrist(MoveWrist.WristPosition.CARRY, 1));
        }
    }

    public static class DumpBin extends CommandGroup {
        {
            addSequential(new SetGrabber(true));
            addSequential(new MoveWrist(MoveWrist.WristPosition.BACK, 1));
            addSequential(new MoveWrist(MoveWrist.WristPosition.SHAKE, 1));
            addSequential(new MoveWrist(MoveWrist.WristPosition.BACK, 1));
            addSequential(new MoveWrist(MoveWrist.WristPosition.YEET, 1));
            addSequential(new SetIntakeForTime(1, 0.5));
            addSequential(new SetGrabber(false));
        }
    }
}
