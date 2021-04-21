/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Sphere;
import primitives.*;

/**
 * Unit tests for Sphere
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		Sphere sphere = new Sphere(Point3D.ZERO, 2);
		assertEquals("getNormal(Point3D) result", new Vector(0, 0, 1), sphere.getNormal(new Point3D(0, 0, 2)));
	}

	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is outside the sphere (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

		// TC02: Ray starts before and crosses the sphere (2 points)
		Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
		List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", List.of(p1, p2), result);

		// TC03: Ray starts inside the sphere (1 point)
		result = sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0.5), new Vector(1, 3, -7)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere",
				List.of(new Point3D(0.620043655846709, 0.860130967540128, -0.340305590926965)), result);
		// TC04: Ray starts after the sphere (0 points)
		result = sphere.findIntersections(new Ray(new Point3D(-2, -1, 0), new Vector(-2, -1, 0)));
		assertNull("Ray not cross sphere", result);

		// =============== Boundary Values Tests ==================

		// **** Group: Ray's line crosses the sphere (but not the center)
		// TC05: Ray starts at sphere and goes inside (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(1 + Math.sqrt(0.5), 0.5, 0.5), new Vector(-1, -1, -1)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere",List.of(new Point3D(0.56903559372885, -0.638071187457698,-0.638071187457698)), result);
		// TC06: Ray starts at sphere and goes outside (0 points)
		result = sphere.findIntersections(new Ray(new Point3D(1 + Math.sqrt(0.5), 0.5, 0.5), new Vector(1, 1, 1)));
		assertNull("Ray not cross sphere", result);

		// **** Group: Ray's line goes through the center
		// TC07: Ray starts before the sphere (2 points)
		p1 = new Point3D(0.701857603000028, -0.596284793999944, -0.745355992499929);
		p2 = new Point3D(1.298142396999972, 0.596284793999944, 0.74535599249993);
		result = sphere.findIntersections(new Ray(new Point3D(3, 4, 5), new Vector(-2, -4, -5)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", List.of(p1, p2), result);
		// TC08: Ray starts at sphere and goes inside (1 points)
		result = sphere.findIntersections(
				new Ray(new Point3D(1 + Math.sqrt(0.5), 0.5, 0.5), new Vector(-Math.sqrt(0.5), -0.5, -0.5)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", List.of(new Point3D(0.292893218813452, -0.5, -0.5)), result);

		// TC09: Ray starts inside (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0.5), new Vector(-1, 1, 1)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere",
				List.of(new Point3D(0.422649730810374, 0.577350269189626, 0.577350269189626)), result);
		// TC10: Ray starts at the center (1 points)
		result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 1)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere", List.of(new Point3D(1, 0.707106781186547, 0.707106781186547)), result);
		// TC11: Ray starts at sphere and goes outside (0 points)
		result = sphere.findIntersections(
				new Ray(new Point3D(1 + Math.sqrt(0.5), 0.5, 0.5), new Vector(Math.sqrt(0.5), 0.5, 0.5)));
		assertNull("Ray not cross sphere", result);
		// TC12: Ray starts after sphere (0 points)
		result = sphere.findIntersections(
				new Ray(new Point3D(1 + 2 * Math.sqrt(0.5), 1, 1), new Vector(Math.sqrt(0.5), 0.5, 0.5)));
		assertNull("Ray not cross sphere", result);

		// **** Group: Ray's line is tangent to the sphere (all tests 0 points)
		// TC13: Ray starts before the tangent point
		result = sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0)));
		assertNull("Ray not cross sphere", result);
		// TC14: Ray starts at the tangent point
		result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0)));
		assertNull("Ray not cross sphere", result);
		// TC15: Ray starts after the tangent point
		result = sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0)));
		assertNull("Ray not cross sphere", result);

		// **** Group: Special cases
		// TC16: Ray's line is outside, ray is orthogonal to ray start to sphere's
		// center line
		result = sphere.findIntersections(new Ray(new Point3D(1, 3, 0), new Vector(1, 0, 0)));
		assertNull("Ray not cross sphere", result);
	}

}
