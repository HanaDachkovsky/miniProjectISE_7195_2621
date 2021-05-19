/**
 * 
 */
package elements;
import primitives.*;

/**
 * @author 
 *Interface for all light sources for calculate intensity in point
 */
public interface LightSource {
	/**
	 * returns the intensity in point(its color)
	 * @param p- point to calculate intensity
	 * @return the intensity of color in point
	 */
	public Color getIntensity(Point3D p);
	/**
	 * return the vector of the light
	 * @param p- point
	 * @return the vector of the light
	 */
	public Vector getL(Point3D p);
	public double getDistance(Point3D point);
}
