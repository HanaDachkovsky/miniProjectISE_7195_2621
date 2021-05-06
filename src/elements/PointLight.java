/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author 
 *
 */
public class PointLight extends Light implements LightSource {
	private Point3D position;
	private double kC;
	private double kL;
	private double kQ;

	/**
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
		super(intensity);
		this.position = position;
		this.kC = kC;
		this.kL = kL;
		this.kQ = kQ;
	}

	@Override
	public Color getIntensity(Point3D p) {
		double d=p.distance(position);
		return intensity.reduce(kC+kL*d+kQ*d*d);
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(position).normalize();
	}

}
