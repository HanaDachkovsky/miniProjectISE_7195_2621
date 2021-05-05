/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author aaa Tests for Ray
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() {
		Ray ray = new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: the closest point is in the middle of the list
		List<Point3D> list = List.of(new Point3D(5, 5, 5), new Point3D(7, 7, 7), new Point3D(3, 3, 3),
				new Point3D(9, 9, 9));
		assertEquals("findClosetPoint(List<Point3D) result", new Point3D(3, 3, 3), ray.findClosestPoint(list));

		// =============== Boundary Values Tests ==================
		// TC02:the list is empty
		assertEquals("findClosetPoint(List<Point3D) result when the list is empty", null,
				ray.findClosestPoint(List.of()));
		// TC03:the closest point is the first
		list = List.of(new Point3D(2, 2, 2), new Point3D(7, 7, 7), new Point3D(3, 3, 3), new Point3D(9, 9, 9));
		assertEquals("findClosetPoint(List<Point3D) result", new Point3D(2, 2, 2), ray.findClosestPoint(list));

		// TC04:the closest point is the last
		list = List.of(new Point3D(5, 5, 5), new Point3D(7, 7, 7), new Point3D(6, 6, 6), new Point3D(3, 3, 3));
		assertEquals("findClosetPoint(List<Point3D) result", new Point3D(3, 3, 3), ray.findClosestPoint(list));
	}
	
	/**
	 * Test method for {@link primitives.Ray#findClosestGeoPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestGeoPoint() {
		Ray ray = new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1));
		// ============ Equivalence Partitions Tests ==============
		// TC01: the closest point is in the middle of the list
		List<GeoPoint> list = List.of(new GeoPoint(, new Point3D(5, 5, 5)), new Point3D(7, 7, 7), new Point3D(3, 3, 3),
				new Point3D(9, 9, 9));
		assertEquals("findClosetPoint(List<Point3D) result", new Point3D(3, 3, 3), ray.findClosestPoint(list));

		// =============== Boundary Values Tests ==================
		// TC02:the list is empty
		assertEquals("findClosetPoint(List<Point3D) result when the list is empty", null,
				ray.findClosestPoint(List.of()));
		// TC03:the closest point is the first
		list = List.of(new Point3D(2, 2, 2), new Point3D(7, 7, 7), new Point3D(3, 3, 3), new Point3D(9, 9, 9));
		assertEquals("findClosetPoint(List<Point3D) result", new Point3D(2, 2, 2), ray.findClosestPoint(list));

		// TC04:the closest point is the last
		list = List.of(new Point3D(5, 5, 5), new Point3D(7, 7, 7), new Point3D(6, 6, 6), new Point3D(3, 3, 3));
		assertEquals("findClosetPoint(List<Point3D) result", new Point3D(3, 3, 3), ray.findClosestPoint(list));
	}

}
