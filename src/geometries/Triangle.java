/**
 * 
 */
package geometries;

import java.util.List;

//import org.graalvm.compiler.nodes.ReturnNode;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * represents a triangle in space
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Triangle extends Polygon {
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
	}

	@Override
	public String toString() {
		return "Triangle [vertices=" + vertices + ", plane=" + plane.toString() + "]";
	}

	

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		if (this.plane.findIntersections(ray) == null)
			return null;
		try {
			Vector v1 = vertices.get(0).subtract(ray.getP0());
			Vector v2 = vertices.get(1).subtract(ray.getP0());
			Vector v3 = vertices.get(2).subtract(ray.getP0());
			Vector N1 = v1.crossProduct(v2).normalize();
			Vector N2 = v2.crossProduct(v3).normalize();
			Vector N3 = v3.crossProduct(v1).normalize();

			double t1, t2, t3;
			t1 = alignZero(ray.getDir().dotProduct(N1));
			t2 = alignZero(ray.getDir().dotProduct(N2));
			t3 = alignZero(ray.getDir().dotProduct(N3));
			if (t1 == 0 || t2 == 0 || t3 == 0)
				return null;
			if (t1 < 0 && t2 < 0 && t3 < 0 || t1 > 0 && t2 > 0 && t3 > 0) {
				List<GeoPoint> list = this.plane.findGeoIntersections(ray);
				list.forEach(shape -> shape.geometry = this);
				return list;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
