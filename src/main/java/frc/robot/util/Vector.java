package frc.robot.util;

public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector() {
        this(0, 0, 0);
    }

    public Vector(double x, double y) {
        this(x, y, 0);
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector setX(double x) {
        this.x = x;
        return this;
    }

    public Vector setY(double y) {
        this.y = y;
        return this;
    }

    public Vector setZ(double z) {
        this.z = z;
        return this;
    }
}