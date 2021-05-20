/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import static geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * Testing Polygons
 * 
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Colocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
    /**
	 * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
	Polygon polygon=new Polygon(new Point3D(-4,1,1),new Point3D(-4,-2,1),new Point3D(-1,-5,1),new Point3D(0,0,1),new Point3D(-2,1,1))	;
	// ============ Equivalence Partitions Tests ==============
	// TC01:the intersection with the plane is outside the polygon and against the edge
	assertNull("Ray doesn't cross the triangle",polygon.findIntersections(new Ray(new Point3D(6,5,4), new Vector(-5,-9,-3))));
	// TC02:the intersection with the plane is outside the polygon and against the vertex
	assertNull("Ray doesn't cross the triangle",polygon.findIntersections(new Ray(new Point3D(6,5,4), new Vector(-8,-3,-3))));
	// TC03:the intersection with the plane is inside the polygon
	List<Point3D> result= polygon.findIntersections(new Ray(new Point3D(6,5,4), new Vector(-7,-6,-3)));
	assertEquals("Wrong number of points", 1, result.size());
	assertEquals("Ray crosses triangle",List.of(new Point3D(-1,-1,1)), result);
	// =============== Boundary Values Tests ==================
	// TC04:the intersection with the plane on an edge
	assertNull("Ray doesn't cross the triangle",polygon.findIntersections(new Ray(new Point3D(6,5,4), new Vector(-13,-15,-6))));
	// TC05:the intersection with the plane on a vertex
	assertNull("Ray doesn't cross the triangle",polygon.findIntersections(new Ray(new Point3D(6,5,4), new Vector(-10,-4,-3))));
	// TC06:the intersection with the plane on the continuation of edge
	assertNull("Ray doesn't cross the triangle",polygon.findIntersections(new Ray(new Point3D(6,5,4), new Vector(-5,0,-3))));
	}
	/**
	 * Test method for {@link geometries.Polygon#findGeoIntersections(primitives.Ray, double)}.
	 */
	@Test
	public void testFindGeoIntersections() {
		Polygon polygon=new Polygon(new Point3D(-4,1,1),new Point3D(-4,-2,1),new Point3D(-1,-5,1),new Point3D(0,0,1),new Point3D(-2,1,1))	;
		// ============ Equivalence Partitions Tests ==============
		// TC01:1 intersection
		List<GeoPoint> result= polygon.findGeoIntersections(new Ray(new Point3D(6,5,4), new Vector(-7,-6,-3)),10);
		assertEquals("Ray crosses triangle",List.of(new GeoPoint(polygon, new Point3D(-1,-1,1))), result);
		//TC02: no intersections
		result= polygon.findGeoIntersections(new Ray(new Point3D(6,5,4), new Vector(-7,-6,-3)),3);
		assertNull("Ray crosses triangle", result);
		
	}
}

