/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
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
		return (point.subtract(center)).normalize();
	}

	@Override
	public String toString() {
		return "Sphere [center=" + center.toString() + ", radius=" + radius + "]";
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
