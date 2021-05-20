/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * @author
 *
 */
public class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(Ray ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Geometries geometries = new Geometries();
		geometries.add(new Plane(new Point3D(1, 2, 3), new Vector(0, 0, 1)));
		geometries.add(new Polygon(new Point3D(-4, 1, 1), new Point3D(-4, -2, 1), new Point3D(-1, -5, 1),
				new Point3D(0, 0, 1), new Point3D(-2, 1, 1)));
		geometries.add(new Sphere(new Point3D(0, 1, 0), 1d));
		geometries.add(new Triangle(new Point3D(5, -5, 4), new Point3D(2, 7, 4), new Point3D(-2, -4, 4)));
		// ============ Equivalence Partitions Tests ==============
		// TC01: part of the shapes have intersections
		Ray ray = new Ray(new Point3D(0, 5, 0), new Vector(3, -1, 5));
		assertEquals("Wrong number of points", 2, geometries.findIntersections(ray).size());
		// =============== Boundary Values Tests ==================
		// TC02: only one shape has intersections
		ray = new Ray(new Point3D(0, 5, 0), new Vector(8, -13, 5));
		assertEquals("Wrong number of points", 1, geometries.findIntersections(ray).size());
		// TC03: all shapes have intersections
		ray = new Ray(new Point3D(0, 2, -2), new Vector(-1, -6, 8));
		assertEquals("Wrong number of points", 5, geometries.findIntersections(ray).size());
		// TC04:no shape has intersections
		ray = new Ray(new Point3D(0, 5, 0), new Vector(8, -13, -5));
		assertNull("Wrong number of points", geometries.findIntersections(ray));
		// TC04:no shape has intersections
		Geometries geometries2=new Geometries();
		assertNull("Wrong number of points", geometries2.findIntersections(ray));

	}
	/**
	 * Test method for {@link geometries.Geometries#findGeoIntersections(primitives.Ray, double)}.
	 */
	@Test
	public void testFindGeoIntersections() {
		Geometries geometries = new Geometries();
		geometries.add(new Plane(new Point3D(1, 2, 3), new Vector(0, 0, 1)));
		geometries.add(new Polygon(new Point3D(-4, 1, 1), new Point3D(-4, -2, 1), new Point3D(-1, -5, 1),
				new Point3D(0, 0, 1), new Point3D(-2, 1, 1)));
		geometries.add(new Sphere(new Point3D(0, 1, 0), 1d));
		geometries.add(new Triangle(new Point3D(5, -5, 4), new Point3D(2, 7, 4), new Point3D(-2, -4, 4)));
		// ============ Equivalence Partitions Tests ==============
		// TC01: no shape has intersections
		Ray ray = new Ray(new Point3D(0, 5, 0), new Vector(3, -1, 5));
		assertNull("Wrong number of points", geometries.findGeoIntersections(ray,1));
		// TC02: part of shapes has intersections
		ray = new Ray(new Point3D(0, 2, -2), new Vector(-1, -6, 8));
		assertEquals("Wrong number of points", 3, geometries.findGeoIntersections(ray,4).size());
		// TC04:no shape has intersections
		ray = new Ray(new Point3D(0, 2, -2), new Vector(-1, -6, 8));
		assertNull("Wrong number of points", geometries.findGeoIntersections(ray,1));
	}

}
