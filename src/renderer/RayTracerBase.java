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
	 * @param scene
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	public abstract Color traceRay (Ray ray );
		
	

}
