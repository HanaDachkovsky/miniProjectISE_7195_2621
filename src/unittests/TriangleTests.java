/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;


/**
 * Unit tests for Triangle
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		Triangle triangle = new Triangle(new Point3D(1, 0, 0), new Point3D(0, 0, 0), new Point3D(0, 1, 0));
		Vector normal = triangle.getNormal(new Point3D(0.1, 0.1, 0.1));
		assertTrue("getNormal(Point3D) result",
				normal.equals(new Vector(0, 0, 1)) || normal.equals(new Vector(0, 0, -1)));
	}

	/**
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
	Triangle triangle=new Triangle(new Point3D(1, 2, 3),new Point3D(4, 5, 3), new Point3D(2, 7, 3))	;
	// ============ Equivalence Partitions Tests ==============
	// TC01:the intersection with the plane is outside the triangle and against the edge
	assertNull("Ray doesn't cross the triangle",triangle.findIntersections(new Ray(new Point3D(5,-3,7), new Vector(-2,5,-4))));
	// TC02:the intersection with the plane is outside the triangle and against the vertex
	assertNull("Ray doesn't cross the triangle",triangle.findIntersections(new Ray(new Point3D(6,6,7), new Vector(-9,-11,-4))));
	// TC03:the intersection with the plane is inside the triangle
	List<Point3D> result= triangle.findIntersections(new Ray(new Point3D(6,5,4), new Vector(-4,-1,-1)));
	assertEquals("Wrong number of points", 1, result.size());
	assertEquals("Ray crosses triangle",List.of(new Point3D(2,4,3)), result);
	// =============== Boundary Values Tests ==================
	// TC04:the intersection with the plane on an edge
	assertNull("Ray doesn't cross the triangle",triangle.findIntersections(new Ray(new Point3D(6,5,4), new Vector(-4,-2,-1))));
	// TC05:the intersection with the plane on a vertex
	assertNull("Ray doesn't cross the triangle",triangle.findIntersections(new Ray(new Point3D(6,5,4), new Vector(-5,-3,-1))));
	// TC05:the intersection with the plane on the continuation of edge
	assertNull("Ray doesn't cross the triangle",triangle.findIntersections(new Ray(new Point3D(6,5,4), new Vector(1,3,-1))));
	}
}
