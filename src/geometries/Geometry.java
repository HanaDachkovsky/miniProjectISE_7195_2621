package geometries;
import primitives.*;
/**
 * An interface of all geometries
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public interface Geometry extends Intersectable {
	/**
	 * returns a normal to geometry in point
	 * @param point- a point in the geometry
	 * @return a normal
	 */
	public Vector getNormal(Point3D point);
}
