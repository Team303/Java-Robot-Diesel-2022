package com.team303.diesel.util;

public class Vector2D {
    private double x;
    private double y;

    /**
     * Construct a 2D Vector with 0 initial values
     * 
     * All components of the Vector (x, y) are all initialized to 0
     */
    public Vector2D() {
        this(0, 0);
    }

    /**
     * Construct a 2D Vector from 2 values (x, y)
     * 
     * @param x
     * @param y
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the Y component of this Vector
     * 
     * @return The y component
     */
    public double getX() {
        return x;
    }

    /**
     * Get the Y component of this Vector
     * 
     * @return The y component
     */
    public double getY() {
        return y;
    }

    /**
     * Set the X component of this Vector
     * 
     * @param x
     * @return The updated Vector
     */
    public Vector2D setX(double x) {
        this.x = x;
        return this;
    }

    /**
     * Set the Y component of this Vector
     * 
     * @param y
     * @return The updated Vector
     */
    public Vector2D setY(double y) {
        this.y = y;
        return this;
    }

    /**
     * Add each component from the provided vector to the coresponding component in
     * this Vector
     * 
     * @param other The Vector to add to every component
     * @return The updated Vector
     */
    public Vector2D add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    /**
     * Add the scalar provided to each component of the Vector
     * 
     * @param scalar The constant to add to every component
     * @return The updated Vector
     */
    public Vector2D add(double scalar) {
        this.x += scalar;
        this.y += scalar;
        return this;
    }

    /**
     * Add the scalars provided to each component of the Vector
     * 
     * x is added to x, y is added to y
     * 
     * @param x
     * @param y
     * @return The updated Vector
     */
    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Subtract each component from the provided vector from the coresponding
     * component in this Vector
     * 
     * @param other The Vector to subtract from every component
     * @return The updated Vector
     */
    public Vector2D sub(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    /**
     * Subtract the scalar provided from each component of the Vector
     * 
     * @param scalar The constant to subtract from every component
     * @return The updated Vector
     */
    public Vector2D sub(double scalar) {
        this.x -= scalar;
        this.y -= scalar;
        return this;
    }

    /**
     * Subtract the scalars provided from each component of the Vector
     * 
     * x is subtracted from x, y is subtracted from y
     * 
     * @param x
     * @param y
     * @return The updated Vector
     */
    public Vector2D sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    /**
     * Multiply each component of the Vector by the scalar provided
     * 
     * @param scalar The constant to multiply every component by
     * @return The updated Vector
     */
    public Vector2D mult(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    /**
     * Multiply each component of the Vector by the scalars provided
     * 
     * x is multiplied by x, y is multiplied by y
     * 
     * @param x
     * @param y
     * @return The updated Vector
     */
    public Vector2D mult(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    /**
     * Divide each component of the Vector by the scalar provided
     * 
     * @param scalar The constant to divide every component by
     * @return The updated Vector
     */
    public Vector2D div(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    /**
     * Divide each component of the Vector by the scalars provided
     * 
     * x is divided by x, y is divided by y
     * 
     * @param x
     * @param y
     * @return The updated Vector
     */
    public Vector2D div(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    /**
     * Calculated the magnitude of this vector
     * 
     * @return The magnitude (Distance to the origin)
     */
    public double magnitude() {
        // Calc distance in 2D
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Sets the magnitude of the Vector
     * 
     * @param magnitude The magnitude to change the vector to
     * @return The new Vector with its magnitude altered
     */
    public Vector2D setMagnitude(double magnitude) {
        return this.normalize().mult(magnitude);
    }

    /**
     * Normalizes this Vector into a Unit Vector (magnitude of 1)
     * 
     * @return The normalized Vector
     */
    public Vector2D normalize() {
        return this.div(this.magnitude());
    }

    /**
     * Calculates the dot product of this Vector and another Vector
     * 
     * @param other The Vector to dot multiply with this Vector
     * @return The dot product of both Vectors
     */
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    /**
     * Calculate the angle of the Vector
     * 
     * @return The angle theta of the Vector in <b>RADIANS</b>
     */
    public double theta() {
        return Math.atan(this.y / this.x);
    }

    /**
     * Calculate the angle of the Vector
     * 
     * @return The angle theta of the Vector in <b>DEGREES</b>
     */
    public double thetaDeg() {
        return Math.toRadians(theta());
    }

    /**
     * Rotate a Vector in Cartesian space.
     *
     * @param angle Angle in <b>RADIANS</b> by which to rotate Vector counter-clockwise.
     */
    public Vector2D rotate(double angle) {
        double cosA = Math.cos(angle);
        double sinA = Math.sin(angle);

        double x;
        double y;

        x = this.x * cosA - this.y * sinA;
        y = this.x * sinA + this.y * cosA;

        this.x = x;
        this.y = y;

        return this;
    }

    /**
     * Rotate a Vector in Cartesian space.
     *
     * @param angle Angle in <b>DEGREES</b> by which to rotate Vector counter-clockwise.
     */
    public Vector2D rotateDeg(double angle) {
        return this.rotate(Math.toRadians(angle));
    }
}
