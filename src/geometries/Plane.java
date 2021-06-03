/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * represents a plane in space
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Plane extends Geometry {
	private Point3D p0;
	private Vector normal;

	/**
	 * constructor that gets a normal and a point
	 * 
	 * @param p0-a    point in plane
	 * @param normal- the normal to the plane
	 */
	public Plane(Point3D p0, Vector normal) {
		this.p0 = p0;
		this.normal = normal;
		resetBox();
	}

	private void resetBox() {
		box = new Box(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

	}

	/**
	 * constructor that gets 3 points and calculates the normal
	 * 
	 * @param p1- the 1st point
	 * @param p2- the 2nd point
	 * @param p3- the 3rd point
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this.p0 = p1;
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);
		this.normal = (v1.crossProduct(v2)).normalize();
		resetBox();
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

//	@Override
//	public List<GeoPoint> findGeoIntersections(Ray ray) {
//		if(this.p0.equals(ray.getP0()))
//			return null;
//		if(isZero(this.normal.dotProduct(ray.getDir())))//the normal is orthogonal to ray-
//														//the ray parallel to plane or contained
//			return null;
//		double t=alignZero((this.normal.dotProduct(this.p0.subtract(ray.getP0())))
//				/this.normal.dotProduct(ray.getDir()));
//		if(t>0)
//			return List.of(new GeoPoint(this, ray.getPoint(t)));
//		return null;
//	}
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		if (this.p0.equals(ray.getP0()))
			return null;
		if (isZero(this.normal.dotProduct(ray.getDir())))// the normal is orthogonal to ray-
															// the ray parallel to plane or contained
			return null;
		double t = alignZero(
				(this.normal.dotProduct(this.p0.subtract(ray.getP0()))) / this.normal.dotProduct(ray.getDir()));
		if (t > 0)
			if (alignZero(t - maxDistance) <= 0)
				return List.of(new GeoPoint(this, ray.getPoint(t)));
		return null;
	}

}
