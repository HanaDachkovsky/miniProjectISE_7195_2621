/**
 * 
 */
package geometries;

import java.util.List;

//import com.sun.jmx.mbeanserver.Util;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * represents a sphere in space
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 */
public class Sphere extends Geometry {
	private Point3D center;
	private double radius;

	/**
	 * constructor gets center and radius of sphere
	 * 
	 * @param center-       center of sphere
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

//	@Override
//	public List<GeoPoint> findGeoIntersections(Ray ray) {
//		if(ray.getP0().equals(center))
//			return List.of(new GeoPoint(this, ray.getPoint(radius)));
//		Vector u=center.subtract(ray.getP0());
//		double tm=ray.getDir().dotProduct(u);
//		double d=Math.sqrt(alignZero(u.lengthSquared()-(tm*tm)));
//		if(isZero(d-radius)||d>=radius)
//			return null;
//		double th=Math.sqrt(radius*radius-d*d);
//		double t1=alignZero(tm+th);
//		double t2=alignZero(tm-th);
//		if(t1<=0&&t2<=0)
//			return null;
//		if(t1<=0)
//			return List.of(new GeoPoint(this, ray.getPoint(t2)));
//		if(t2<=0)
//			return List.of(new GeoPoint(this, ray.getPoint(t1)));
//		return List.of(new GeoPoint(this, ray.getPoint(t1)),new GeoPoint(this, ray.getPoint(t2)));
//	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		if (ray.getP0().equals(center)) {
			if (alignZero(radius - maxDistance) <= 0) {
				return List.of(new GeoPoint(this, ray.getPoint(radius)));
			} else {
				return null;
			}
		}
		Vector u = center.subtract(ray.getP0());
		double tm = ray.getDir().dotProduct(u);
		double d = Math.sqrt(alignZero(u.lengthSquared() - (tm * tm)));
		if (isZero(d - radius) || d >= radius)
			return null;
		double th = Math.sqrt(radius * radius - d * d);
		double t1 = alignZero(tm + th);
		double t2 = alignZero(tm - th);
		if (t1 <= 0 && t2 <= 0)
			return null;
		if (t1 <= 0)
			return List.of(new GeoPoint(this, ray.getPoint(t2)));
		if (t2 <= 0)
			return List.of(new GeoPoint(this, ray.getPoint(t1)));
		return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
	}

}
