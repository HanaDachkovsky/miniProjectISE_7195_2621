/**
 * 
 */
package primitives;

import java.util.List;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Ray {
	private Point3D p0;
	private Vector dir;

	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * @return the direction
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * @param p0  - start point of the ray
	 * @param dir - direction vector
	 */
	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalized();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir);
	}

	/**
	 * 
	 * @param t-scalar
	 * @return p0+t*v
	 */
	public Point3D getPoint(double t) {
		return p0.add(dir.scale(t));
	}

	public Point3D findClosestPoint(List<Point3D> list) {
		// In the intersections - find the point with minimal distance from the ray
		// head and return it
		if (list == null || list.size() == 0)
			return null;
		double minDistance = Double.POSITIVE_INFINITY;
		Point3D closePoint = null;
		double distance;
		for (Point3D point : list) {
			distance = p0.distance(point);
			if (distance < minDistance) {
				closePoint = point;
				minDistance = distance;
			}
		}
		return closePoint;
	}
}
