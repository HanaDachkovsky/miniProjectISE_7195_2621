package geometries;
import primitives.*;
/**
 * An interface of all geometries
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public abstract class Geometry implements Intersectable {
	protected Color emission=Color.BLACK;
	private Material material=new Material();
	/**
	 * returns a normal to geometry in point
	 * @param point- a point in the geometry
	 * @return a normal
	 */
	public abstract Vector getNormal(Point3D point);
	/**
	 * @return the emission
	 */
	public Color getEmission() {
		return emission;
	}
	/**
	 * @param emission the emission to set
	 */
	public Geometry setEmission(Color emmission) {
		this.emission = emmission;
		return this;
	}
	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}
	/**
	 * @param material the material to set
	 * @return this
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
	
}
