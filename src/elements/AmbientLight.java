/**
 * 
 */
package elements;
import primitives.*;

import java.awt.Color;

/**
 * @author Hana Davchkovsky and Sara Tamar Amitai
 *
 */
public class AmbientLight extends Light{
	/**
	 * ctor with params
	 * @param intensity
	 */
	public AmbientLight(primitives.Color ia,double ka) {
		super(ia.scale(ka));
	}
	/**
	 * ctor with no params that sets to black
	 * 
	 */
	public AmbientLight() {
		super(primitives.Color.BLACK);
	}


	

}
