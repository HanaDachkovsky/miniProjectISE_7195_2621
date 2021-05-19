/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author 
 *class for point light- light that its power is equal for each direction
 */
public class PointLight extends Light implements LightSource {
	private Point3D position;
	private double kC=1;
	private double kL=0;
	private double kQ=0;

	/**
	 * @param intensity-the intensity of light in the point
	 * @param position- the location of the light
	 * ctor with params
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
	@Override
	public double getDistance(Point3D point) {
		return point.distance(position);
	}

}
