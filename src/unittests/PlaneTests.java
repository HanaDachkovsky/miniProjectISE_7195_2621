/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testPlanePoint3DPoint3DPoint3D() {
		// =============== Boundary Values Tests ==================

        // TC01:converging points
		try {
			new Plane(new Point3D(1,2,3) ,new Point3D(1,2,3),new Point3D(1,0,0));
			fail("Constructed a plane with converging points");
		}
		catch(IllegalArgumentException e) {}
		
		 // TC02: points in the same line
		try {
			new Plane(new Point3D(1,0,0) ,new Point3D(2,0,0),new Point3D(3,0,0));
			fail("Constructed a plane with points in the same line");
		}
		catch(IllegalArgumentException e) {}
		
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		// ============ Equivalence Partitions Tests ==============
		//TC01:
			Plane plane=new Plane(new Point3D(1,0,0),new Point3D(0,0,0),new Point3D(0,1,0));
			Vector normal=plane.getNormal(new Point3D(1,0,0));
			assertTrue("getNormal(Point3D) result",normal.equals(new Vector(0,0,1))||normal.equals(new Vector(0,0,-1)));
			
		}
		
	}

