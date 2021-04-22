/**
 * 
 */
package unittests;

import geometries.*;
import primitives.*;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import elements.*;

/**
 * @author
 *
 */
public class IntegrationTests {
	@Test
	public void cameraSphereIntersections() {
		// TC01:only one ray intersects
		Sphere sphere = new Sphere(new Point3D(0, 0, -3), 1);
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 2, countIntersections(sphere, camera));
		// TC02:all ray intersect
		sphere = new Sphere(new Point3D(0, 0, -2.5), 2.5);
		camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 18, countIntersections(sphere, camera));
		// TC03:part of rays intersect
		sphere = new Sphere(new Point3D(0, 0, -2), 2);
		camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 10, countIntersections(sphere, camera));
		// TC04:the view plane is inside the sphere
		sphere = new Sphere(new Point3D(0, 0, -2), 4);
		camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 9, countIntersections(sphere, camera));
		// TC05:no intersections. The sphere is behind the camera.
		sphere = new Sphere(new Point3D(0, 0, 1), 0.5);
		camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 0, countIntersections(sphere, camera));
	}
	@Test
	public void cameraPlaneIntersections() {
		// TC01:the plane is parallel to view plane. 9 intersections.
		Plane plane = new Plane(new Point3D(0, 0, -5), new Vector(0, 0, -1));
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 9, countIntersections(plane, camera));
		// TC02: 9 intersections
		plane = new Plane(new Point3D(0, 0, -5), new Vector(0, 0.25, -1));
		camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 9, countIntersections(plane, camera));
		// TC03: 6 intersections
		plane = new Plane(new Point3D(0, 0, -5), new Vector(0, 2, -1));
		camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 6, countIntersections(plane, camera));
	}
	@Test
	public void cameraTriangleIntersections() {
		// TC01: 1 intersection.
		Triangle triangle = new Triangle(new Point3D(0, 1, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 1, countIntersections(triangle, camera));
		// TC01: 2 intersections.
		triangle = new Triangle(new Point3D(0, 20, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
		camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
		assertEquals("Wrong number of points", 2, countIntersections(triangle, camera));

	}

	private int countIntersections(Intersectable shape, Camera camera) {
		int counter = 0;
		List<Point3D> list;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				list = shape.findIntersections(camera.constructRayThroughPixel(3, 3, j, i));
				if (list != null)
					counter += list.size();
			}
		return counter;

	}

}
