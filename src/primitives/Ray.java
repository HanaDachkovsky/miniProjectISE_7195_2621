/**
 * 
 */
package primitives;

import java.util.List;
import geometries.Intersectable.GeoPoint;


/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Ray {
	private Point3D p0;
	private Vector dir;
	/**
	 * the distance to move the points over the geometries in order to prevent
	 * intersections with the geometry itself
	 */
	private static final double DELTA = 0.1;

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
	public Ray(Point3D head, Vector direction, Vector normal) {
		Vector delta = normal.scale(normal.dotProduct(direction) > 0 ? DELTA : -DELTA);
		this.p0 = head.add(delta);
		this.dir = direction.normalized();
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

	
	@Override
	public String toString() {
		return "Ray [p0=" + p0.toString() + ", dir=" + dir.toString() + "]";
	}

	/**
	 * 
	 * @param t-scalar
	 * @return p0+t*v
	 */
	public Point3D getPoint(double t) {
		return p0.add(dir.scale(t));
	}

	
	/**
	 * 
	 * @param list- the list of points that are on the ray that intersect the geometry
	 * @return the closet point to p0
	 */
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
	
	/**
	 * 
	 * @param list- the list of points that are on the ray that intersect the geometry
	 * @return the closet point to p0
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> list) {
		// In the intersections - find the point with minimal distance from the ray
		// head and return it
		if (list == null || list.size() == 0)
			return null;
		double minDistance = Double.POSITIVE_INFINITY;
		GeoPoint closePoint = null;
		double distance;
		for (GeoPoint geoPoint : list) {
			distance = p0.distance(geoPoint.point);
			if (distance < minDistance) {
				closePoint = geoPoint;
				minDistance = distance;
			}
		}
		return closePoint;
	}
}
