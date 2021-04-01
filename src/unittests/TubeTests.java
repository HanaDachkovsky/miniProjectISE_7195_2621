/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;


/**
 * @author yuiya
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		//TC01:the point is on the lateral surface
		Tube tube=new Tube(new Ray(Point3D.ZERO,new Vector(0,0,1)),1);
		Vector normal=tube.getNormal(new Point3D(1,0,1));
		assertEquals("getNormal(Point3D) result",new Vector(1,0,0),normal);
		// =============== Boundary Values Tests ==================
		//TC02:the point is front of the start point of the ray
		normal=tube.getNormal(new Point3D(1,0,0));
		assertEquals("getNormal(Point3D) result",new Vector(1,0,0),normal);
		}

}
