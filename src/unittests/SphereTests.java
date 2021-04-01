/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;
import primitives.*;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		//TC01:
		Sphere sphere =new Sphere (Point3D.ZERO,2);
		assertEquals("getNormal(Point3D) result",new Vector(0,0,1),sphere.getNormal(new Point3D(0,0,2)));
		
	}

}
