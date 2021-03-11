/**
 * 
 */
package geomtries;

import primitives.Ray;

/**
  * represents a cylinder in space
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Cylinder extends Tube {
	private double height;

	/**
	 * constructor gets ray radius and height
	 * @param axisRay- the central ray of the tube(the direction)
	 * @param radius- the radius of the tube
	 * @param height- the height of the cylinder
	 */
	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "Cylinder [height=" + height + ", axisRay=" + axisRay.toString() + ", radius=" + radius + "]";
	}
	
	
}
