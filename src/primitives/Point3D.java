/**
 * 
 */
package primitives;

import java.util.List;

/**
 * Point3D represents a point in 3D space
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Point3D {
	final Coordinate x;
	final Coordinate y;
	final Coordinate z;
	public final static Point3D ZERO = new Point3D(0, 0, 0);

	/**
	 * Point3D constructor receiving a 3 coordinates
	 * 
	 * @param x - x value of the point
	 * @param y - y value of the point
	 * @param z - z value of the point
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Point3D constructor receiving a 3 values
	 * 
	 * @param x - x value of the point
	 * @param y - y value of the point
	 * @param z - z value of the point
	 */
	public Point3D(double x, double y, double z) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
	}

	/**
	 * 
	 * @param vec- vector that added to the point
	 * @return - new point
	 */
	public Point3D add(Vector vec) {
		return new Point3D(this.x.coord + vec.getHead().x.coord, this.y.coord + vec.getHead().y.coord,
				this.z.coord + vec.getHead().z.coord);
	}

	/**
	 * 
	 * @param point- point that is subtracted from the current point to create a new
	 *               vector
	 * @return - a new vector
	 */
	public Vector subtract(Point3D point) {
		return new Vector(this.x.coord - point.x.coord, this.y.coord - point.y.coord, this.z.coord - point.z.coord);

	}

	/**
	 * 
	 * @param point- the point that we want to know the distance between it and the
	 *               current point
	 * @return the squared distance between the points
	 */
	public double distanceSquared(Point3D point) {
		return (this.x.coord - point.x.coord) * (this.x.coord - point.x.coord)
				+ (this.y.coord - point.y.coord) * (this.y.coord - point.y.coord)
				+ (this.z.coord - point.z.coord) * (this.z.coord - point.z.coord);
	}

	/**
	 * 
	 * @param point- the point that we want to know the distance between it and the
	 *               current point
	 * @return the distance between the points
	 */
	public double distance(Point3D point) {
		return Math.sqrt(distanceSquared(point));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return this.x.equals(other.x) && this.y.equals(other.y) && this.z.equals(other.z);
	}

	@Override
	public String toString() {
		return "Point3D [x=" + x.toString() + ", y=" + y.toString() + ", z=" + z.toString() + "]";
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x.coord;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y.coord;
	}

	/**
	 * @return the z
	 */
	public double getZ() {
		return z.coord;
	}

	

}
