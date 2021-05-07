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
	private double kC=1;
	private double kL=0;
	private double kQ=0;

	/**
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public PointLight(Color intensity, Point3D position) {
		super(intensity);
		this.position = position;
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

	/**
	 * @param kC the kC to set
	 */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * @param kL the kL to set
	 */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * @param kQ the kQ to set
	 */
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}

}
