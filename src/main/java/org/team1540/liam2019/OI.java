package org.team1540.liam2019;


import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.liam2019.commands.IntakeBin;
import org.team1540.liam2019.commands.SetGrabber;
import org.team1540.liam2019.commands.SetWrist;
import org.team1540.liam2019.utils.ChickenXboxController;
import org.team1540.liam2019.utils.SimpleCommand;
import org.team1540.rooster.triggers.DPadAxis;

public class OI {

    static double joyAngleR;

    public static ChickenXboxController driver = new ChickenXboxController(0);
    public static Button driverA = driver.getButton(ChickenXboxController.XboxButton.A);
    public static Button driverB = driver.getButton(ChickenXboxController.XboxButton.B);
    public static Button driverX = driver.getButton(ChickenXboxController.XboxButton.X);
    public static Button driverY = driver.getButton(ChickenXboxController.XboxButton.Y);
    public static Button driverLB = driver.getButton(ChickenXboxController.XboxButton.LB);
    public static Button driverRB = driver.getButton(ChickenXboxController.XboxButton.RB);

    public static ChickenXboxController copilot = new ChickenXboxController(1);
    public static Button copilotA = copilot.getButton(ChickenXboxController.XboxButton.A);
    public static Button ejectBin = copilot.getButton(ChickenXboxController.XboxButton.B);
    public static Button copilotX = copilot.getButton(ChickenXboxController.XboxButton.X);
    public static Button copilotY = copilot.getButton(ChickenXboxController.XboxButton.Y);
    public static Button copilotLB = copilot.getButton(ChickenXboxController.XboxButton.LB);
    public static Button copilotRB = copilot.getButton(ChickenXboxController.XboxButton.RB);


    public static Button intakeBin = copilot.getButton(DPadAxis.DOWN);
    public static Button dumpBin = copilot.getButton(DPadAxis.UP);
    public static Button carryBin = copilot.getButton(DPadAxis.LEFT);

    public static void init() {
        copilotA.whenPressed(new SimpleCommand(() -> Robot.grabber.toggleGrabbed(), Robot.grabber));

        intakeBin.whenPressed(new CommandGroup() {{
            addParallel(new SetWrist(SetWrist.WristPosition.DOWN, 1));
            addSequential(new IntakeBin(-0.5));
            addSequential(new SetGrabber(true));
        }});

        dumpBin.whenPressed(new CommandGroup() {{
            addSequential(new SetWrist(SetWrist.WristPosition.BACK, 1));
            addSequential(new SetWrist(SetWrist.WristPosition.SHAKE, 0.5));
            addSequential(new SetWrist(SetWrist.WristPosition.BACK, 0.5));
        }});

        carryBin.whenPressed(new CommandGroup() {{
            addSequential(new SetWrist(SetWrist.WristPosition.CARRY, 1));
        }});

        ejectBin.whenPressed(new SimpleCommand(() -> Robot.intake.set(1), Robot.intake));
        ejectBin.whenReleased(new SimpleCommand(() -> Robot.intake.set(0), Robot.intake));

        copilotX.whenPressed(new SetWrist(SetWrist.WristPosition.DOWN));
        copilotY.whenPressed(new SetWrist(SetWrist.WristPosition.UP));
    }

}
