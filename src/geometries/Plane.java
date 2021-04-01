/**
 * 
 */
package geometries;

import primitives.*;

/**
 * represents a plane in space
 * @author  Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Plane implements Geometry {
	private Point3D p0;
	private Vector normal;
	/**
	 * constructor that gets a normal and a point
	 * @param p0-a point in plane
	 * @param normal- the normal to the plane
	 */
	public Plane(Point3D p0, Vector normal) {
		this.p0 = p0;
		this.normal = normal;
	}
	/**
	 * constructor that gets 3 points and calculates the normal
	 * @param p1- the 1st point
	 * @param p2- the 2nd point
	 * @param p3- the 3rd point
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this.p0 = p1;
		Vector v1=p2.subtract(p1);
		Vector v2=p3.subtract(p1);
		this.normal=(v1.crossProduct(v2)).normalize();
	}
	@Override
	public Vector getNormal(Point3D point) {
		return normal;
	}
	public Vector getNormal() {
		return normal;
	}
	public Point3D getP0() {
		return p0;
	}
	@Override
	public String toString() {
		return "Plane [p0=" + p0.toString() + ", normal=" + normal.toString() + "]";
	}
	
	
	
}
