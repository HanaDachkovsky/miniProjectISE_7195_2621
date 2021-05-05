/**
 * 
 */
package scene;

import elements.AmbientLight;
import geometries.*;
import primitives.Color;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Scene {
	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 0);
	public Geometries geometries = new Geometries();

	/**
	 * @param name
	 */
	public Scene(String name) {
		this.name = name;
	}

	/**
	 * @param background the background to set
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * @param ambientLight the ambientLight to set
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * @param
	 */
	public Scene addGeometry(Intersectable geo) {
		geometries.add(geo);
		return this;
	}

	/**
	 * @return the geometries
	 */
	public Geometries getGeometries() {
		return geometries;
	}

	/**
	 * @param geometries the geometries to set
	 */
	public void setGeometries(Geometries geometries) {
		this.geometries = geometries;
	}
	

}
