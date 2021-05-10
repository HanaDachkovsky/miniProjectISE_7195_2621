/**
 * 
 */
package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;

import geometries.*;
import primitives.*;
import scene.*;
import static primitives.Util.*;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai this class calculate the color
 *         of each intersection point (that is the closet point)
 */
public class RayTracerBasic extends RayTracerBase {

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
		List<GeoPoint> intersectionsList = scene.geometries.findGeoIntersections(ray);
		if (intersectionsList == null) {
			return scene.background;
		}
		GeoPoint closetPoint = ray.findClosestGeoPoint(intersectionsList);
		return calcColor(closetPoint, ray);
	}

	/**
	 * 
	 * @param point-the point that we want to calculate its color
	 * @return the color in the point
	 */
	private Color calcColor(GeoPoint point, Ray ray) {
		return  scene.ambientLight.getIntensity().add(point.geometry.getEmission(),calcLocalEffects(point, ray));
		
	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
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
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, nl, lightIntensity),
						calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
			}
		}
		return color;
	}

	private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(2 * nl));
		return lightIntensity.scale(ks*Math.pow(Math.max(0, -v.dotProduct(r)), nShininess));
	}

	private Color calcDiffusive(double kd, Vector l, Vector n, double nl, Color lightIntensity) {
		return lightIntensity.scale(kd * Math.abs(nl));
	}

}
