/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 *  represents a sphere in space
 * @author Hana Dachkovsky and Sara Tamar Amitai
 */
public class Sphere implements Geometry {
	private Point3D center;
	private double radius;

	/**
	 * constructor gets center and radius of sphere
	 * @param center- center of sphere
	 * @param radius-radius of sphere
	 */
	public Sphere(Point3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public Point3D getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		return null;
	}

	@Override
	public String toString() {
		return "Sphere [center=" + center.toString() + ", radius=" + radius + "]";
	}

}
