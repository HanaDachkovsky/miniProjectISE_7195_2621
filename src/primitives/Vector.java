/**
 * 
 */
package primitives;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Vector {
	private Point3D head ;

	/**
	 * @return the head
	 */
	public Point3D getHead() {
		return head;
	}

	/**
	 * @param head- head of the vector
	 */
	public Vector(Point3D head) {
		this.head = head;
	}
	
	/**
	 * Vector constructor receiving a 3 coordinates
	 * @param x - x value of the point
	 * @param y - y value of the point
	 * @param z - z value of the point
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D point=new Point3D(x,y,z);
		if(point.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Can not create zero Vector");
		this.head=point;
	}
	
	/**
	 * Vector constructor receiving a 3 values
	 * @param x - x value of the point
	 * @param y - y value of the point
	 * @param z - z value of the point
	 */
	public Vector(double x, double y, double z) {
		Point3D point=new Point3D(x,y,z);
		if(point.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Can not create zero Vector");
		this.head=point;
	}
	
	/**
	 * 
	 * @param vector- the vector to add
	 * @return - a new vector (the sum of the two vectors)
	 */
	
	public Vector add(Vector vector){
		return new Vector(this.head.x.coord+vector.head.x.coord,this.head.y.coord+vector.head.y.coord,this.head.z.coord+vector.head.z.coord);
	}
	
	/**
	 * 
	 * @param vector- the vector to subtract
	 * @return a new vector
	 */
	public Vector subtract(Vector vector) {
		return new Vector(this.head.x.coord-vector.head.x.coord,this.head.y.coord-vector.head.y.coord,this.head.z.coord-vector.head.z.coord);
	}
	
	/**
	 * 
	 * @param number- scalar
	 * @return a new vector 
	 */
	public Vector scale(double number) {
		return new Vector(this.head.x.coord*number,this.head.y.coord*number,this.head.z.coord*number);
	}
	
	/**
	 * 
	 * @param vector- the vector to cross
	 * @return a new vector 
	 */
	public Vector crossProduct(Vector vector) {
		return new Vector(this.head.y.coord*vector.head.z.coord-this.head.z.coord*vector.head.y.coord,this.head.z.coord*vector.head.x.coord-this.head.x.coord*vector.head.z.coord,this.head.x.coord*vector.head.y.coord-this.head.y.coord*vector.head.x.coord);
	}
	
	/**
	 * 
	 * @param vector
	 * @return the dot product of the vectors
	 */
	public double dotProduct(Vector vector) {
		return this.head.x.coord*vector.head.x.coord+this.head.y.coord*vector.head.y.coord+this.head.z.coord*vector.head.z.coord;
	}
	
	/**
	 * 
	 * @return the squared length of the vector
	 */
	public double lengthSquared() {
		return this.head.distanceSquared(Point3D.ZERO);
	}
	
	/**
	 * 
	 * @return the length of the vector
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}
	
	/**
	 * 
	 * @return the current vector normlized
	 */
	public Vector normalize() {
		double length=length();
		head=new Point3D(head.x.coord/length, head.y.coord/length, head.z.coord/length);
		return this;
	}
	
	/**
	 * 
	 * @return a new vector normlized
	 */
	public Vector normalized(){
		return new Vector(this.head).normalize();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other= (Vector)obj;
		return this.head.equals(other.head);
	}

	@Override
	public String toString() {
		return "Vector [head=" + head.toString() +"]";
	}
	
	
	
	

	
 
}
