package frc.robot.util;

/**
 * Representation of a 3D Vector
 * 
 * @author Adrian Wowk
 * @since 1/12/2022
 */
public class Vector3D {
    private double x;
    private double y;
    private double z;

    /**
     * Construct a 3D Vector with 0 initial values
     * 
     * All components of the Vector (x, y, z) are all initialized to 0
     */
    public Vector3D() {
        this(0, 0, 0);
    }

    /**
     * Construct a 3D Vector from 3 values
     * @param x
     * @param y
     * @param z
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
     * Get the Z component of this Vector
     * 
     * @return The z component
     */
    public double getZ() {
        return z;
    }

    /**
     * Set the X component of this Vector
     * 
     * @param x
     * @return The updated Vector
     */
    public Vector3D setX(double x) {
        this.x = x;
        return this;
    }

    /**
     * Set the Y component of this Vector
     * 
     * @param y
     * @return The updated Vector
     */
    public Vector3D setY(double y) {
        this.y = y;
        return this;
    }

    /**
     * Set the Z component of this Vector
     * 
     * @param z
     * @return The updated Vector
     */
    public Vector3D setZ(double z) {
        this.z = z;
        return this;
    }

    /**
     * Add each component from the provided vector to the coresponding component in
     * this Vector
     * 
     * @param other The Vector to add to every component
     * @return The updated Vector
     */
    public Vector3D add(Vector3D other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    /**
     * Add the scalar provided to each component of the Vector
     * 
     * @param scalar The constant to add to every component
     * @return The updated Vector
     */
    public Vector3D add(double scalar) {
        this.x += scalar;
        this.y += scalar;
        this.z += scalar;
        return this;
    }

    /**
     * Add the scalars provided to each component of the Vector
     * 
     * x is added to x, y is added to y, etc.
     * 
     * @param x
     * @param y
     * @param z
     * @return The updated Vector
     */
    public Vector3D add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    /**
     * Subtract each component from the provided vector from the coresponding
     * component in this Vector
     * 
     * @param other The Vector to subtract from every component
     * @return The updated Vector
     */
    public Vector3D sub(Vector3D other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }

    /**
     * Subtract the scalar provided from each component of the Vector
     * 
     * @param scalar The constant to subtract from every component
     * @return The updated Vector
     */
    public Vector3D sub(double scalar) {
        this.x -= scalar;
        this.y -= scalar;
        this.z -= scalar;
        return this;
    }

    /**
     * Subtract the scalars provided from each component of the Vector
     * 
     * x is subtracted from x, y is subtracted from y, etc.
     * 
     * @param x
     * @param y
     * @param z
     * @return The updated Vector
     */
    public Vector3D sub(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    /**
     * Multiply each component of the Vector by the scalar provided
     * 
     * @param scalar The constant to multiply every component by
     * @return The updated Vector
     */
    public Vector3D mult(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }

    /**
     * Multiply each component of the Vector by the scalars provided
     * 
     * x is multiplied by x, y is multiplied by y, etc.
     * 
     * @param x
     * @param y
     * @param z
     * @return The updated Vector
     */
    public Vector3D mult(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    /**
     * Divide each component of the Vector by the scalar provided
     * 
     * @param scalar The constant to divide every component by
     * @return The updated Vector
     */
    public Vector3D div(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        return this;
    }

    /**
     * Divide each component of the Vector by the scalars provided
     * 
     * x is divided by x, y is divided by y, etc.
     * 
     * @param x
     * @param y
     * @param z
     * @return The updated Vector
     */
    public Vector3D div(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    /**
     * Calculated the magnitude of this vector
     * 
     * @return The magnitude (Distance to the origin)
     */
    public double magnitude() {
        // Calc distance in 3D
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * Sets the magnitude of the Vector
     * 
     * @param magnitude The magnitude to change the vector to
     * @return The new Vector with its magnitude altered
     */
    public Vector3D setMagnitude(double magnitude) {
        return this.normalize().mult(magnitude);
    }

    /**
     * Normalizes this Vector into a Unit Vector (magnitude of 1)
     * 
     * @return The normalized Vector
     */
    public Vector3D normalize() {
        return this.div(this.magnitude());
    }

    /**
     * Calculates the dot product of this Vector and another Vector
     * 
     * @param other The Vector to dot multiply with this Vector
     * @return The dot product of both Vectors
     */
    public double dot(Vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }
}