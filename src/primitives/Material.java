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
	public void setkD(double kD) {
		this.kD = kD;
	}
	/**
	 * @param kS the kS to set
	 */
	public void setkS(double kS) {
		this.kS = kS;
	}
	/**
	 * @param nShininess the nShininess to set
	 */
	public void setnShininess(int nShininess) {
		this.nShininess = nShininess;
	}
	

}
