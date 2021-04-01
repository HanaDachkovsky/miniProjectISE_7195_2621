/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;


/**
 * Unit tests for Point3D
 * @author Hana Dachkovsky and Sara Tamar Amitai
 */
public class Point3DTests {

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		 // ============ Equivalence Partitions Tests ==============
		//TC01:
		 Point3D p1 = new Point3D(1, 2, 3); 
	      assertEquals("add(Vector) result",Point3D.ZERO,p1.add(new Vector(-1, -2, -3)));
	}

	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		//TC01:
		Point3D p1 = new Point3D(1, 2, 3); 
		assertEquals("subtract(Point3D) result",new Vector(1, 1, 1),new Point3D(2, 3, 4).subtract(p1));
		// ============ Equivalence Partitions Tests ==============
		//TC02:The result of the subtraction is close to zero vector 
		try{
			new Point3D(1.0000000000001, 2, 3).subtract(p1);
			fail("Constructed a vector that diffrent from zero vector");
		}
		catch(IllegalArgumentException e) {}
		}
	}


