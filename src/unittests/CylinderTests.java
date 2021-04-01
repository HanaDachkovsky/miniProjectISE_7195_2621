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
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		//TC01:The point is on the lateral surface
		Cylinder cylinder=new Cylinder(new Ray(Point3D.ZERO,new Vector(0,0,1)),1,5);
		Vector normal=cylinder.getNormal(new Point3D(1,0,1));
		assertEquals("getNormal(Point3D) result",new Vector(1,0,0),normal);
		//TC02:The point is on the bottom base
		normal=cylinder.getNormal(new Point3D(0.1,0.1,0));
		assertEquals("getNormal(Point3D) result",new Vector(0,0,-1),normal);
		//TC03:The point is on the top base
		normal=cylinder.getNormal(new Point3D(0.1,0.1,5));
		assertEquals("getNormal(Point3D) result",new Vector(0,0,1),normal);
		// =============== Boundary Values Tests ==================
		//TC04:the point is on the center of the bottom base
		normal=cylinder.getNormal(new Point3D(0,0,0));
		assertEquals("getNormal(Point3D) result",new Vector(0,0,-1),normal);
		//TC05:the point is on the center of the top base
		normal=cylinder.getNormal(new Point3D(0,0,5));
		assertEquals("getNormal(Point3D) result",new Vector(0,0,1),normal);
	}

}
