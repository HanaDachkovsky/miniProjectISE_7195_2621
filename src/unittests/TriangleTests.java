/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

/**
 * Unit tests for Triangle
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

}
