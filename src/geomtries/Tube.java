/**
 * 
 */
package geomtries;

import primitives.*;

/**
 * represents a tube in space
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Tube implements Geometry {
	protected Ray axisRay;
	protected double radius;

	/**
	 * constructor gets a ray and a radius
	 * @param axisRay- the central ray of the tube(the direction)
	 * @param radius- the radius of the tube
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
		return null;
	}

	@Override
	public String toString() {
		return "Tube [axisRay=" + axisRay.toString() + ", radius=" + radius + "]";
	}


}
