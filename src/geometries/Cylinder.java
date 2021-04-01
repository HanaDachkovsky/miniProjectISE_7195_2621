/**
 * 
 */
package geometries;




import primitives.*;

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
	@Override
	public Vector getNormal(Point3D point) {
		if(Util.isZero(axisRay.getDir().dotProduct(new Vector(point))-axisRay.getDir().dotProduct(new Vector(axisRay.getP0()))))//if the point is in the bottom base
		  return axisRay.getDir().scale(-1);
		if(Util.isZero(axisRay.getDir().dotProduct(new Vector(point))-axisRay.getDir().dotProduct(axisRay.getDir().scale(height).add(new Vector(axisRay.getP0())))))
		  return axisRay.getDir();
		return super.getNormal(point);
	}

	
}
