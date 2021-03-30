/**
 * 
 */
package geometries;

import primitives.Point3D;

/**
 * represents a triangle in space
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Triangle extends Polygon {
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
	}

	@Override
	public String toString() {
		return "Triangle [vertices=" + vertices + ", plane=" + plane.toString() + "]";
	}

	

}
