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
	 * 
	 * @param p- point to calculate intensity
	 * @return the intensity of color in point
	 */
	public Color getIntensity(Point3D p);
	/**
	 * 
	 * @param p- point
	 * @return
	 */
	public Vector getL(Point3D p);
}
