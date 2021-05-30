/**
 * 
 */
package renderer;

import java.util.List;

import elements.DirectionalLight;
import elements.LightSource;
import elements.PointLight;
import elements.SpotLight;
import geometries.Intersectable.GeoPoint;

import geometries.*;
import primitives.*;
import scene.*;

import static java.lang.System.out;
import static primitives.Util.*;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai this class calculate the color
 *         of each intersection point (that is the closet point)
 */
public class RayTracerBasic extends RayTracerBase {
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double INITIAL_K = 1.0;
	private boolean improve = false;
	private int numberRays = 20;

	/**
	 * ctor
	 * 
	 * @param scene - include the geometries the camera and the colors of the image
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		if (closestPoint == null)
			return scene.background;
		return calcColor(closestPoint, ray);

	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray, Vector n, double k) {
		Vector v = ray.getDir();
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD, ks = material.kS;
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			// double ktr = averageTransparency(lightSource, l, n, intersection);
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				double ktr;
				if (improve == true)
					ktr = averageTransparency(lightSource, l, n, intersection);
				else
					ktr = transparency(lightSource, l, n, intersection);
				if (ktr * k > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
					color = color.add(calcDiffusive(kd, nl, lightIntensity),
							calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
				}
			}
		}
		return color;
	}

	/**
	 * function that casts beam of ray in a circle around the light source
	 * 
	 * @param lightSource  the light source
	 * @param l            the vector of light
	 * @param n            normal to point
	 * @param intersection the point of geometry
	 * @return average of ktr
	 */
	private double averageTransparency(LightSource lightSource, Vector l, Vector n, GeoPoint intersection) {
		double sum = 0;// sum of ktr
		int counter = 1;// amount of ray
		sum += transparency(lightSource, l, n, intersection);// the original ray
		if (lightSource instanceof DirectionalLight)
			return sum;
		Vector noraml;
		if (lightSource instanceof SpotLight)// in spot light the light source is orthogonal to its direction
			noraml = ((SpotLight) lightSource).getDirection();
		else
			noraml = l;
		Vector u = noraml.orthogonal();
		Vector v = noraml.crossProduct(u).normalize();// orthogonal base to the light source's plane
		Point3D p = ((PointLight) lightSource).getPosition();// center of light source
		double r = ((PointLight) lightSource).getRaduis();// radius
		double jump = Math.sqrt(Math.PI * r * r / numberRays);// the distance between 2 close points- according to the
																// number of rays and area of circle
		Point3D point = p.add(u.scale(-r));// 1 axis
		for (double i = 0; i < 2 * r / jump; i++) {
			double lenght = alignZero(Math.sqrt(r * r - p.distanceSquared(point)));// according to Pythagoras- the
																					// distance on axis 2
			Point3D point2 = point;//the point in space
			if (lenght != 0)
				point2 = point.add(v.scale(-lenght));//edge of axis2
			for (int j = 0; j < 2 * lenght / jump; j++) {
				sum += transparency(lightSource, intersection.point.subtract(point2).normalize(), noraml, intersection);
				counter++;
				point2 = point2.add(v.scale(jump));//jump in axis 2
			}
			point = point.add(u.scale(jump));//jump in axis 1
		}
		return sum / counter;//return average
	}

	private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess,
			Color lightIntensity) {
		Vector r = l.subtract(n.scale(2 * nl));
		return lightIntensity.scale(ks * Math.pow(Math.max(0, -v.dotProduct(r)), nShininess));
	}

	private Color calcDiffusive(double kd, double nl, Color lightIntensity) {
		return lightIntensity.scale(kd * Math.abs(nl));
	}

	/**
	 * function that checks if the point is unshaded or gets the light
	 * 
	 * @param l-the     Vector of light
	 * @param n-        the normal to point over the geometry
	 * @param geopoint- the point over the geometry
	 * @return true/false- if the point is unshaded
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geopoint.point, lightDirection, n);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,
				light.getDistance(geopoint.point));
		if (intersections == null)
			return true;
		for (GeoPoint gp : intersections) {
			if (gp.geometry.getMaterial().kT == 0)
				return false;
		}
		return true;
	}

	/**
	 * functions that calculates the amount of shadow in point
	 * 
	 * @param light    light source
	 * @param l        the vector of light source
	 * @param n        the normal to point
	 * @param geopoint the point to calculate shadow
	 * @return the amount of shadow in point
	 */
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geopoint.point, lightDirection, n);
		var intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(geopoint.point));
		if (intersections == null)
			return 1.0;
		double ktr = 1.0;
		for (GeoPoint gp : intersections) {
			ktr *= gp.geometry.getMaterial().kT;
			if (ktr < MIN_CALC_COLOR_K)
				return 0.0;
		}
		return ktr;
	}

	private Ray constructReflectedRay(Vector n, Point3D point, Vector v) {
		return new Ray(point, v.subtract(n.scale(2 * v.dotProduct(n))), n);
	}

	private Ray constructRefractedRay(Vector n, Point3D point, Vector v) {
		return new Ray(point, v, n);
	}

	/**
	 * finds the intersection between the ray and the scene and finds the closest
	 * point
	 * 
	 * @param ray ray that intersects the scene
	 * @return the closest intersection
	 */
	GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);
	}

	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	/**
	 * 
	 * @param point-the point that we want to calculate its color
	 * @return the color in the point
	 */
	private Color calcColor(GeoPoint geopoint, Ray ray, int maxCalcColorLevel, double initialK) {
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		Color color = geopoint.geometry.getEmission();
		color = color.add(calcLocalEffects(geopoint, ray, n, initialK));
		return 1 == maxCalcColorLevel ? color
				: color.add(calcGlobalEffects(geopoint, ray, maxCalcColorLevel, initialK, n));

	}

	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k, Vector n) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.kR, kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(n, geopoint.point, ray.getDir());
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
			else
				color = color.add(scene.background.scale(kr));
//
		}
		double kt = material.kT, kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(n, geopoint.point, ray.getDir());
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null)
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
			else
				color = color.add(scene.background.scale(kt));
		}
		return color;
	}

	/**
	 * @param improve the improve to set
	 */
	public RayTracerBasic setImprove() {
		this.improve = true;
		return this;
	}

	/**
	 * @return the numberRays
	 */
	public int getNumberRays() {
		return numberRays;
	}

	/**
	 * @param numberRays the numberRays to set
	 */
	public RayTracerBasic setNumberRays(int numberRays) {
		this.numberRays = numberRays;
		return this;
	}

}
