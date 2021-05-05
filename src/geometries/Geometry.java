package geometries;
import primitives.*;
/**
 * An interface of all geometries
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public abstract class Geometry implements Intersectable {
	protected Color emmission=Color.BLACK;
	/**
	 * returns a normal to geometry in point
	 * @param point- a point in the geometry
	 * @return a normal
	 */
	public abstract Vector getNormal(Point3D point);
	/**
	 * @return the emmission
	 */
	public Color getEmmission() {
		return emmission;
	}
	/**
	 * @param emmission the emmission to set
	 */
	public Geometry setEmmission(Color emmission) {
		this.emmission = emmission;
		return this;
	}
}
