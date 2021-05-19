/**
 * 
 */
package elements;

import primitives.*;
import static primitives.Util.*;

/**
 * @author 
 *class for directional light- light that its power is not lower in distance
 */
public class DirectionalLight extends Light implements LightSource {
	private Vector direction;
	/**
	 * ctor that gets 2 params
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
	@Override
	public double getDistance(Point3D point) {
		return Double.POSITIVE_INFINITY;
	}
}
