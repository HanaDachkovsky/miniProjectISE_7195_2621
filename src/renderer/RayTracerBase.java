/**
 * 
 */
package renderer;

import primitives.*;
import scene.Scene;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 * 
 */
public abstract class RayTracerBase {
	protected Scene scene;

	/**
	 * ctor
	 * @param scene- include the camera the colors and the geometries 
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * @param ray- the ray that intersect the geometry 
	 * @return the color of the intersections
	 */
	public abstract Color traceRay (Ray ray );
		
	

}
