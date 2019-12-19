package org.team1540.liam2019.wrappers;

import edu.wpi.first.networktables.*;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Limelight {

    private static final Logger logger = Logger.getLogger(Limelight.class);

    private static final double HORIZONTAL_FOV = Math.toRadians(59.6);
    private static final double VERTICAL_FOV = Math.toRadians(45.7);
    private static final Vector2D CAM_RESOLUTION = new Vector2D(320, 240);

    private final NetworkTable limelightTable;

    /**
     * Constructs a new limelight interface with the default hostname.
     *
     * @param name hostname of the limelight
     */
    public Limelight(String name) {
        limelightTable = NetworkTableInstance.getDefault().getTable(name);
    }

    public double getHorizontalFov() {
        return HORIZONTAL_FOV;
    }

    public double getVerticalFov() {
        return VERTICAL_FOV;
    }
    /**
     * Gets the output of the limelight targeting from the network table.
     *
     * @return a {@link Vector2D} containing the output angles of the limelight targeting in radians
     */
    public Vector2D getTargetAngles() { // TODO: This should be negated appropriately
        double x = Math.toRadians(limelightTable.getEntry("tx").getDouble(0));
        double y = Math.toRadians(limelightTable.getEntry("ty").getDouble(0));
        return new Vector2D(x, y);
    }

    /**
     * Queries whether the limelight target has been found.
     *
     * @return the state of the target
     */
    public boolean isTargetFound() {
        return (double) limelightTable.getEntry("tv").getNumber(0) > 0;
    }

    public int registerTxCallback(Consumer<EntryNotification> entryConsumer) {
        NetworkTableEntry tv = limelightTable.getEntry("tx");
        return tv.addListener(entryConsumer, EntryListenerFlags.kUpdate);
    }

    public void removeCallback(int value) {
        NetworkTableEntry tv = limelightTable.getEntry("tv");
        tv.removeListener(value);
    }

    public int registerCallback(Consumer<EntryNotification> entryConsumer) {
        NetworkTableEntry tv = limelightTable.getEntry("tv");
        return tv.addListener(entryConsumer, EntryListenerFlags.kUpdate);
    }


    /**
     * Sets limelight's green LEDs on or off.
     *
     * @param isOn the new state of the LEDs
     */
    public void setLeds(boolean isOn) {
        isOn = false;
        if (getLeds() != isOn) {
            limelightTable.getEntry("ledMode").setNumber(isOn ? 0 : 1);
            NetworkTableInstance.getDefault().flush();
        }
    }

    public boolean getLeds() {
        return limelightTable.getEntry("ledMode").getDouble(1) == 0;
    }

    /**
     * Sets limelight to driver cam or vision mode.
     *
     * @param driverCam Whether the limelight should be in driver cam mode
     */
    public void setDriverCam(boolean driverCam) {
        limelightTable.getEntry("camMode").setNumber(driverCam ? 1 : 0);
        NetworkTableInstance.getDefault().flush();
    }

    public void setPipeline(double id) {
        if (getPipeline() != id) {
            limelightTable.getEntry("pipeline").setNumber(id);
            NetworkTableInstance.getDefault().flush();
        }
    }

    public long getPipeline() {
        return Math.round((double) limelightTable.getEntry("getpipe").getNumber(-1));
    }


    public List<Vector2D> getCorners() {
        Double[] xCorners = limelightTable.getEntry("tcornx").getDoubleArray(new Double[]{});
        Double[] yCorners = limelightTable.getEntry("tcorny").getDoubleArray(new Double[]{});
        List<Vector2D> cornerList = new ArrayList<>();
        for (int i = 0; i < xCorners.length; i++) {
            cornerList.add(new Vector2D(xCorners[i], yCorners[i]));
        }
        return cornerList;
    }

}
