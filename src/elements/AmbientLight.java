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
	 * @param intensity
	 */
	public AmbientLight(primitives.Color ia,double ka) {
		super(ia.scale(ka));
	}


	

}
