/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * represents a tube in space
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Tube extends Geometry {
	protected Ray axisRay;
	protected double radius;

	/**
	 * constructor gets a ray and a radius
	 * 
	 * @param axisRay- the central ray of the tube(the direction)
	 * @param radius-  the radius of the tube
	 */
	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}

	public Ray getAxisRay() {
		return axisRay;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point3D point) {
		double t = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
		Point3D O;
		if (Util.isZero(t)) {
			O = axisRay.getP0();
		} else {
			O = axisRay.getP0().add(axisRay.getDir().scale(t));
		}
		return point.subtract(O).normalize();
	}

	@Override
	public String toString() {
		return "Tube [axisRay=" + axisRay.toString() + ", radius=" + radius + "]";
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
