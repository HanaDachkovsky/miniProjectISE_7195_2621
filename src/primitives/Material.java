/**
 * 
 */
package primitives;

/**
 * @author 
 *the material of the geometry
 */
public class Material {
	public double kD=0;
	public double kS=0;
	public int nShininess=0;
	/**
	 * @param kD the kD to set
	 */
	public Material setKd(double kD) {
		this.kD = kD;
		return this;
	}
	/**
	 * @param kS the kS to set
	 */
	public Material setKs(double kS) {
		this.kS = kS;
		return this;
	}
	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
	

}
