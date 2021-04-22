/**
 * 
 */
package elements;

import primitives.*;
import geometries.*;
import static primitives.Util.*;

//import org.graalvm.compiler.hotspot.HotSpotHostBackend;

/**
 * @author
 *
 */
public class Camera {
	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;

	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param p0
	 * @param vUp
	 * @param vTo
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		this.p0 = p0;
		this.vUp = vUp.normalize();
		this.vTo = vTo.normalize();
		if (!isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("the vectors are not orthogonal");
		this.vRight = vTo.crossProduct(vUp);
	}

	/**
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public Camera setViewPlaneSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * 
	 * @param distance
	 * @return
	 */
	public Camera setDistance(double distance) {
		this.distance = distance;
		return this;
	}

	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		Point3D pCenter;
		if (isZero(this.distance))
			pCenter = this.p0;
		else
			pCenter = this.p0.add(vTo.scale(this.distance));
		double rY = this.height / nY;
		double rX = this.width / nX;
		double xJ = (j - 0.5 * (nX - 1)) * rX;
		double yI = -(i - 0.5 * (nY - 1)) * rY;
		Point3D pIJ = pCenter;
		if (!isZero(xJ))
			pIJ = pIJ.add(vRight.scale(xJ));
		if (!isZero(yI))
			pIJ = pIJ.add(vUp.scale(yI));
		return new Ray(this.p0, pIJ.subtract(this.p0));
	}

}
