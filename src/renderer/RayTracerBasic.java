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
	 * the distance to move the points over the geometries in order to prevent
	 * intersections with the geometry itself
	 */
	private static final double DELTA = 0.1;

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
		return scene.ambientLight.getIntensity().add(point.geometry.getEmission(), calcLocalEffects(point, ray));

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
				if (unshaded(lightSource,l, n, intersection)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color.add(calcDiffusive(kd, nl, lightIntensity),
							calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
				}
			}
		}
		return color;
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
	 * @param l-the Vector of light
	 * @param n- the normal to point over the geometry
	 * @param geopoint- the point over the geometry
	 * @return true/false- if the point is unshaded
	 */
	private boolean unshaded(LightSource light,Vector l, Vector n, GeoPoint geopoint) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		Point3D point = geopoint.point.add(delta);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		return intersections == null;
	}

}
