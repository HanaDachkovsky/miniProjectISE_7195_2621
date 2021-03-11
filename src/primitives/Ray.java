/**
 * 
 */
package primitives;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Ray {
	private Point3D p0;
	private Vector dir;
	
	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}
	
	/**
	 * @return the direction
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * @param p0 - start point of the ray
	 * @param dir - direction vector
	 */
	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalized();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other= (Ray)obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir);
	}
	

}
