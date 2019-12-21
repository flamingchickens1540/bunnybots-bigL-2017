package org.team1540.liam2019.utils;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A button based on a joystick axis. This can be used to emulate a button using a trigger or
 * joystick.
 */
public class DualAxisButton extends Button {

    private GenericHID stick;
    private double threshold;
    private int axis;

    /**
     * Constructs an {@code DualAxisButton}.
     *
     * @param stick     The axis's joystick
     * @param axis      The axis to use as a button
     * @param threshold The threshold for the button to be triggered
     * @throws NullPointerException If {@code stick} is {@code null}.
     */
    public DualAxisButton(@NotNull GenericHID stick, double threshold, int axis) {
        this.stick = Objects.requireNonNull(stick);
        this.threshold = threshold;
        this.axis = axis;
    }

    @Override
    public boolean get() {
        return Math.abs(stick.getRawAxis(axis)) >= Math.abs(threshold);
    }
}
