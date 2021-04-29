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
 *  this class calculate the color of each intersection point (that is the closet point)
 */
public class RayTracerBasic extends RayTracerBase {
	
	/**
	 * @param scene - include the geometries the camera and the colors of the image
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
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
	/**
	 * 
	 * @param point-the point that we want to calculate its color
	 * @return the color in the point
	 */
	private Color calcColor(Point3D point) {
		return scene.ambientLight.getIntensity();
	}

}
