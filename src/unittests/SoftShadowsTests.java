package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Testing basic shadows
 * 
 * @author Dan
 */
public class SoftShadowsTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * soft shadows improvement picture
	 */
	@Test
	public void softTest() {
		int a = 250;
		Vector vto = new Vector(-1000, 0, -a);
		Vector vup = vto.orthogonal().crossProduct(vto);
		Camera camera = new Camera(new Point3D(1000, 0, a), vto, vup) //
				.setViewPlaneSize(200, 200).setDistance(1000);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.geometries.add( //
				new Polygon(new Point3D(200, 200, 0), new Point3D(200, -200, 0), new Point3D(-200, -200, 0),
						new Point3D(-200, 200, 0)) //
								.setEmission(new Color(java.awt.Color.BLACK)) //
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(0, 0, 5), 5) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(20, 30, 10), 10) //
						.setEmission(new Color(java.awt.Color.PINK)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(-40, -60, 30), 30) //
						.setEmission(new Color(java.awt.Color.YELLOW)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(40, -20, 12), 12) //
						.setEmission(new Color(java.awt.Color.GRAY)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(-30, 50, 20), 20) //
						.setEmission(new Color(java.awt.Color.DARK_GRAY)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(70, 70, 15), 15) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(-60, 0, 20), 20) //
						.setEmission(new Color(java.awt.Color.PINK)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(160, 0, 20), 20) //
						.setEmission(new Color(java.awt.Color.CYAN)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(120, -60, 20), 20) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
				new Sphere(new Point3D(-30, 50, 20), 20) //
						.setEmission(new Color(java.awt.Color.DARK_GRAY)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)));
		scene.lights.add(new SpotLight(new Color(700, 700, 700), new Point3D(200, -200, 200), new Vector(-1, 1, -1)) //
				.setKl(4E-4).setKq(2E-5).setRaduis(30));
		scene.lights.add(new DirectionalLight(new Color(100, 100, 100), new Vector(-1, 1, -1)));
		scene.lights.add(new PointLight(new Color(500, 250, 250), new Point3D(150, 0, 100)) //
				.setKl(0.0005).setKq(0.0005));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("softShadowsBeforeImprovement", 600, 600)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene)).setDebugPrint().setMultithreading(3);
		render.renderImage();
		render.writeToImage();
		render = new Render() //
				.setImageWriter(new ImageWriter("softShadowsWithImprovement", 600, 600)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene).setImproveSoftSadows().setNumberRays(500)).setDebugPrint()
				.setMultithreading(3);
		render.renderImage();
		render.writeToImage();
	}

}
