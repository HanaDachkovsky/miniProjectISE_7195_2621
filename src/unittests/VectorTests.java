/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;

/**
 * Unit tests for primitives.Vector class
 * 
 * @author Hana Dachkovsky and Sara Tamar Amitai
 * @emails Hana Dachkovsky- dachkovs@g.jct.ac.il and Sara Tamar
 *         Amitai-saratamar100@gmail.com
 * @ID Hana Dachkovsky- 211752621 and Sara Tamar Amitai-212207195
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		assertEquals("add(Vector) result", new Vector(4, 0, 4), new Vector(1, 2, 3).add(new Vector(3, -2, 1)));
		// =============== Boundary Values Tests ==================
		// TC02: add the opposite vector
		try {
			new Vector(1, 2, 3).add(new Vector(-1, -2, -3));
			fail("addition of the opposite vector create a zero vector");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtruct() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		assertEquals("subtract(Vector) result", new Vector(-2, 4, 2),
				new Vector(1, 2, 3).subtract(new Vector(3, -2, 1)));
		// =============== Boundary Values Tests ==================
		// TC02: subtract the equal vector
		try {
			new Vector(1, 2, 3).subtract(new Vector(1, 2, 3));
			fail("subtraction of the equal vector create a zero vector");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		assertEquals("scale(double) result", new Vector(2, 4, 6), new Vector(1, 2, 3).scale(2));
		// =============== Boundary Values Tests ==================
		// TC02: multiply by zero
		try {
			new Vector(1, 2, 3).scale(0);
			fail("multiply vector by zero create a zero vector");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:the angle between the vectors is between 0-90
		assertEquals("crossProduct(Vector) result", new Vector(0, 0, 1),
				new Vector(1, 0, 0).crossProduct(new Vector(1, 1, 0)));
		// TC02:the angle between the vectors is between 90 -180
		assertEquals("crossProduct(Vector) result", new Vector(0, 0, 1),
				new Vector(1, 0, 0).crossProduct(new Vector(-1, 1, 0)));
		// =============== Boundary Values Tests ==================
		// TC03: multiply parallel vectors
		try {
			new Vector(1, 2, 3).crossProduct(new Vector(2, 4, 6));
			fail("multiply vector by parallel vector create a zero vector");
		} catch (IllegalArgumentException e) {
		}
		// TC04: multiply by opposite vector
		try {
			new Vector(1, 2, 3).crossProduct(new Vector(-1, -2, -3));
			fail("multiply vector by opposite vector create a zero vector");
		} catch (IllegalArgumentException e) {
		}
		// TC05: multiply by the equal vector
		try {
			new Vector(1, 2, 3).crossProduct(new Vector(1, 2, 3));
			fail("multiply vector by the equal vector create a zero vector");
		} catch (IllegalArgumentException e) {
		}
		// TC06:multiply by orthogonal vector
		assertEquals("crossProduct(Vector) result", new Vector(0, 0, 1),
				new Vector(1, 0, 0).crossProduct(new Vector(0, 1, 0)));
		// TC07:multiply vectors with the same length
		assertEquals("crossProduct(Vector) result", new Vector(0, 0, -20),
				new Vector(3, 4, 0).crossProduct(new Vector(5, 0, 0)));
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:the angle between the vectors is between 0-90
		assertTrue("dotProduct(Vector) result", Util.isZero(1 - new Vector(1, 0, 0).dotProduct(new Vector(1, 1, 0))));
		// TC02:the angle between the vectors is between 90 -180
		assertTrue("dotProduct(Vector) result", Util.isZero(-1 - new Vector(1, 0, 0).dotProduct(new Vector(-1, 1, 0))));
		// =============== Boundary Values Tests ==================
		// TC03: multiply parallel vectors
		assertTrue("dotProduct(Vector) result", Util.isZero(28 - new Vector(1, 2, 3).dotProduct(new Vector(2, 4, 6))));
		// TC04: multiply by opposite vector
		assertTrue("dotProduct(Vector) result",
				Util.isZero(-14 - new Vector(1, 2, 3).dotProduct(new Vector(-1, -2, -3))));
		// TC05: multiply by the equal vector
		assertTrue("dotProduct(Vector) result", Util.isZero(14 - new Vector(1, 2, 3).dotProduct(new Vector(1, 2, 3))));
		// TC06:multiply by orthogonal vector
		assertTrue("dotProduct(Vector) result", Util.isZero(new Vector(1, 0, 0).dotProduct(new Vector(0, 1, 0))));
		// TC07:multiply vectors with the same length
		assertTrue("dotProduct(Vector) result", Util.isZero(15 - new Vector(3, 4, 0).dotProduct(new Vector(5, 0, 0))));
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		assertTrue("lengthSquared() result", Util.isZero(14 - new Vector(1, 2, 3).lengthSquared()));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		assertTrue("length() result", Util.isZero(5 - new Vector(3, 4, 0).length()));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		Vector v1 = new Vector(5, 0, 0);
		Vector v2 = v1.normalize();
		assertTrue("normalize() function creates a new vector", v1 == v2);
		assertEquals("normalize() result", new Vector(1, 0, 0), v2);
		// =============== Boundary Values Tests ==================
		// TC02:The vector's length is 1
		Vector v3 = new Vector(1, 0, 0);
		assertEquals("normalize() result", v3, v3.normalize());
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		// ============ Equivalence Partitions Tests ==============
		// TC01:
		Vector v1 = new Vector(5, 0, 0);
		Vector v2 = v1.normalized();
		assertTrue("normalizated() function does not create a new vector", v1 != v2);
		assertEquals("normalized() result", new Vector(1, 0, 0), v2);
		// =============== Boundary Values Tests ==================
		// TC02:The vector's length is 1
		Vector v3 = new Vector(1, 0, 0);
		assertEquals("normalized() result", v3, v3.normalized());

	}

}