package org.team1540.liam2019.utils;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.jetbrains.annotations.NotNull;
import org.team1540.rooster.functional.Executable;

import java.util.Objects;

/**
 * A simple way to construct an {@link InstantCommand}. <p> To create a {@code SimpleCommand}
 * easily, simply pass it a no-argument lambda containing the code you would like to execute. For
 * example, to create a {@link InstantCommand} that requires the {@code Robot.shifter} {@link
 * Subsystem}, simply write:
 * <pre>
 *   Command shift = new SimpleCommand(() -&gt; Robot.shifter.shift(), Robot.shifter);
 * </pre>
 * <p>
 * Multiple {@code Subystems} can be added onto the end of the constructor to add multiple
 * requirements. <p> This can be used to quickly and easily put a button on the
 * SmartDashboard/Shuffleboard to run some code. Use {@link SmartDashboard#putData(Sendable)} to
 * pass it a newly created instance of this class.
 */
public class SimpleCommand extends InstantCommand {

    private Executable executable;

    /**
     * Creates a new {@code SimpleCommand}.
     *
     * @param action       The code to run when the command executes.
     * @param requirements The {@link Subsystem Subsystems} required by the command (if any).
     * @throws IllegalArgumentException If any parameters are {@code null}.
     */
    public SimpleCommand(@NotNull Executable action,
                         @NotNull Subsystem... requirements) {
        super("");
        executable = Objects.requireNonNull(action);

        for (Subsystem requirement : requirements) {
            requires(Objects.requireNonNull(requirement));
        }
    }

    @Override
    protected void execute() {
        executable.execute();
    }
}
