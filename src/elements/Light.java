/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author 
 *Abstract class for all light sources
 */
public abstract class Light {
	protected Color intensity;

	/**
	 * ctor gets intensity
	 * @param intensity- the power of light
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

	

}
