/**
 * 
 */
package renderer;

import java.util.List;

import geometries.Geometries;
import primitives.*;
import scene.Scene;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 * 
 */
public class RayTracerBasic extends RayTracerBase {
	
	/**
	 * @param scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color traceRay (Ray ray ) {
		List<Point3D> intersectionsList=scene.geometries.findIntersections(ray);
		if(intersectionsList==null) {
			return scene.background;
		}
		Point3D closetPoint=ray.findClosestPoint(intersectionsList);
		return calcColor(closetPoint);
	}
	
	private Color calcColor(Point3D point) {
		return scene.ambientLight.getIntensity();
	}

}
