/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static geometries.Intersectable.GeoPoint;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Unit tests for Plane
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class PlaneTests {

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testPlanePoint3DPoint3DPoint3D() {
		// =============== Boundary Values Tests ==================

		// TC01:converging points
		try {
			new Plane(new Point3D(1, 2, 3), new Point3D(1, 2, 3), new Point3D(1, 0, 0));
			fail("Constructed a plane with converging points");
		} catch (IllegalArgumentException e) {
		}

		// TC02: points in the same line
		try {
			new Plane(new Point3D(1, 0, 0), new Point3D(2, 0, 0), new Point3D(3, 0, 0));
			fail("Constructed a plane with points in the same line");
		} catch (IllegalArgumentException e) {
		}

	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		Plane plane = new Plane(new Point3D(1, 0, 0), new Point3D(0, 0, 0), new Point3D(0, 1, 0));
		Vector normal = plane.getNormal(new Point3D(1, 0, 0));
		assertTrue("getNormal(Point3D) result",
				normal.equals(new Vector(0, 0, 1)) || normal.equals(new Vector(0, 0, -1)));

	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:Ray intersects the plane
		Plane plane = new Plane(new Point3D(1, 1, 1), new Vector(0, 0, 1));
		Ray ray = new Ray(new Point3D(1, 1, 2), new Vector(4, 2, -1));
		assertEquals("findIntersections(Ray) result", List.of(new Point3D(5, 3, 1)), plane.findIntersections(ray));
		// TC02:Ray not intersects the plane
		ray = new Ray(new Point3D(1, 1, 2), new Vector(4, 5, 1));
		assertNull("findIntersections(Ray) result", plane.findIntersections(ray));
		// =============== Boundary Values Tests ==================
		// TC03:The ray is included in the plane
		ray = new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 0));
		assertNull("fintIntersections(Ray) result", plane.findIntersections(ray));
		// TC04:The ray parallel to the plane
		ray = new Ray(new Point3D(1, 1, 2), new Vector(1, 1, 0));
		assertNull("findtIntersections(Ray) result", plane.findIntersections(ray));
		// TC05:ray is orthogonal to the plane and P0 is before the plane
		ray = new Ray(new Point3D(1, 1, 0), new Vector(0, 0, 1));
		assertEquals("findIntersections(Ray) result", List.of(new Point3D(1, 1, 1)), plane.findIntersections(ray));
		// TC05:ray is orthogonal to the plane and P0 is after the plane
		ray = new Ray(new Point3D(1, 1, 2), new Vector(0, 0, -1));
		assertEquals("findIntersections(Ray) result", List.of(new Point3D(1, 1, 1)), plane.findIntersections(ray));
		// TC06:ray is orthogonal to the plane and P0 is in the plane
		ray = new Ray(new Point3D(1, 1, 1), new Vector(0, 0, -1));
		assertNull("findIntersections(Ray) result", plane.findIntersections(ray));
	}

	/**
	 * Test method for
	 * {@link geometries.Plane#findGeoIntersections(primitives.Ray, double)}.
	 */
	@Test
	public void testFindGeoIntersections() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:Ray intersects the plane
		Plane plane = new Plane(new Point3D(1, 1, 1), new Vector(0, 0, 1));
		Ray ray = new Ray(new Point3D(1, 1, 2), new Vector(4, 2, -1));
		assertEquals("findIntersections(Ray) result", List.of(new GeoPoint(plane, new Point3D(5, 3, 1))),
				plane.findGeoIntersections(ray, 5));
		// TC02:Ray not intersects the plane
		assertNull("findIntersections(Ray) result", plane.findGeoIntersections(ray, 1));
	}

}
