/**
 * 
 */
package unittests;
import geometries.*;
import primitives.*;
import elements.*;

/**
 * @author
 *
 */
public abstract class IntegrationTests {
	public void cameraSphereIntersections() {
		// TC01:
		Sphere sphere=new Sphere(new Point3D(0, 0, -3),1);
		Camera camera =new Camera(Point3D.ZERO, new Vector(0,0,-1), new Vector(0,1,0));
		camera.setDistance(1);
		camera.setViewPlaneSize(3, 3);
	}

	private int countIntersections(Iner) {

	}

}
