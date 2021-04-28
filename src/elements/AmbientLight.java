/**
 * 
 */
package elements;
import primitives.*;

import java.awt.Color;

/**
 * @author Hana Davchkovsky and Sara Tamar Amitai
 *
 */
public class AmbientLight {
	private primitives.Color intensity;

	/**
	 * @param intensity
	 */
	public AmbientLight(primitives.Color ia,double ka) {
		this.intensity = ia.scale(ka);
	}

	/**
	 * @return the intensity
	 */
	public primitives.Color getIntensity() {
		return intensity;
	}

	

}
