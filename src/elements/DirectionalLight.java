/**
 * 
 */
package elements;

import primitives.*;
import static primitives.Util.*;

/**
 * @author 
 *
 */
public class DirectionalLight extends Light implements LightSource {
	private Vector direction;

	/**
	 * @param intensity
	 * @param direction
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point3D p) {
		return intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		return direction;
	}

}
