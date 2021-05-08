/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import static primitives.Util.*;


/**
 * @author 
 *
 */
public class SpotLight extends PointLight {
	private Vector direction;
	/**
	 * ctor with params
	 * @param intensity- the intensity of light in the point
	 * @param position- the location of lighter
	 * @param direction- the direction of light
	 */
	public SpotLight(Color intensity, Point3D position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}
	@Override
	public Color getIntensity(Point3D p) {
		double cosinus=alignZero(direction.dotProduct(getL(p)));
		if(cosinus>0)
			return super.getIntensity().scale(cosinus);
		else {
			return Color.BLACK;
		}
	}

	@Override
	public Vector getL(Point3D p) {
		return super.getL(p);
	} 

}
