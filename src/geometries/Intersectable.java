/**
 * 
 */
package geometries;

import java.util.List;
import java.util.stream.Collectors;

import primitives.*;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai An interface of all geometries
 *         that are intersectable by ray
 *
 */
public abstract class Intersectable {
	protected Box box;

	/**
	 * This function checks if and where a ray intersect a geometry
	 * 
	 * @param ray- the ray that intersect the geometry
	 * @return a list of the intersections points, or null if there are no
	 *         intersections points
	 */
	public List<Point3D> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

	// List<GeoPoint> findGeoIntersections(Ray ray);

	public List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}

	abstract List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

	public List<GeoPoint> findGeoIntersectionsBox(Ray ray) {
		return findGeoIntersectionsBox(ray, Double.POSITIVE_INFINITY);
	}
	public List<GeoPoint> findGeoIntersectionsBox(Ray ray, double maxDistance) {
		if(box.intersect(ray,maxDistance))
			return findGeoIntersections(ray, maxDistance);
		return null;
	}

	/**
	 * 
	 * @author Hana Dachkovsky and Sara Tamar Amitai point that contains the
	 *         geometry it is on it
	 */
	public static class GeoPoint {
		public Geometry geometry;
		public Point3D point;

		/**
		 * ctor that gets 2 parameters
		 * 
		 * @param geometry- the shape
		 * @param point-    a point on the shape
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return geometry == other.geometry && point.equals(other.point);
		}

		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry.toString() + ", point=" + point.toString() + "]";
		}

	}

	public static class Box {
		private double minX;
		private double minY;
		private double minZ;
		private double maxX;
		private double maxY;
		private double maxZ;

		public Box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
			this.minX = minX;
			this.minY = minY;
			this.minZ = minZ;
			this.maxX = maxX;
			this.maxY = maxY;
			this.maxZ = maxZ;
		}

		public boolean intersect(Ray ray, double maxDistance) {
			Point3D po = ray.getP0();    
	        double dirfra_x =  1.0d / ray.getDir().getHead().getX();
	        double dirfra_y =  1.0d / ray.getDir().getHead().getY();
	        double dirfra_z =  1.0d / ray.getDir().getHead().getZ();


	        // lb is the corner of AABB with minimal coordinates - left bottom, rt is maximal corner
	        // r.org is origin of ray
	        double t1 = (minX - po.getX()) * dirfra_x;
	        double t2 = (maxX - po.getX()) * dirfra_x;
	        double t3 = (minY - po.getY()) * dirfra_y;
	        double t4 = (maxY - po.getY()) * dirfra_y;
	        double t5 = (minZ - po.getZ()) * dirfra_z;
	        double t6 = (maxZ - po.getZ()) * dirfra_z;


	        double tmin = Math.max(Math.max(Math.min(t1, t2), Math.min(t3, t4)), Math.min(t5, t6));
	        double tmax = Math.min(Math.min(Math.max(t1, t2), Math.max(t3, t4)), Math.max(t5, t6));

	        // if tmax < 0, ray (line) is intersecting AABB, but the whole AABB is behind us
	        if (tmax < 0)
	        {
	            return false;
	        }
	        //if the smallest length of ray us bigger than maxDistance- the intersection is out of range
	        if (tmin >maxDistance)
	        {
	            return false;
	        }
	        // if tmin > tmax, ray doesn't intersect AABB
	        if (tmin > tmax)
	        {
	            return false;
	        }
	        return true;
		}

		public void resize(double x, double y, double z) {
			if (x < minX)
				minX = x;
			if (y < minY)
				minY = y;
			if (z < minZ)
				minZ = z;
			if (x > maxX)
				maxX = x;
			if (y > maxY)
				maxY = y;
			if (z > maxZ)
				maxZ = z;
		}

		public void resize(Box box) {
			resize(box.minX, box.minY, box.minZ);
			resize(box.maxX, box.maxY, box.maxZ);
		}
	}

	/**
	 * @return the box
	 */
	public Box getBox() {
		return box;
	}

	/**
	 * @param box the box to set
	 */
	public void setBox(Box box) {
		this.box = box;
	}

}
