package unittests;

import java.util.List;

import javax.naming.SizeLimitExceededException;

import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Polygon;
import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

/**
 * Tests for improvements mp2- bounding region
 *
 */
public class BoundingTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * soft shadows improvement picture 
	 */
	@Test
	public void softTest()  {
		int a = 250;
		int b = 3000;
		Vector vto = new Vector(-b, 0, -a);
		Vector vup = vto.orthogonal().crossProduct(vto);
		Camera camera = new Camera(new Point3D(b, 0, a), vto, vup) //
				.setViewPlaneSize(200, 200).setDistance(1000);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		List<Point3D> moves = List.of(new Point3D(0, 0, 0), new Point3D(100, 100, 100), new Point3D(-100, 120, -90),
				new Point3D(-150, -185 - 30, 130), new Point3D(0, 0, -200));
		for (Point3D move : moves) {
		//Geometries geometriesGroup=new Geometries();
      	//geometriesGroup.add( 
			scene.geometries.add(
					new Polygon(new Point3D(200 + move.getX(), 200 + move.getY(), 0 + move.getZ()),
							new Point3D(200 + move.getX(), -200 + move.getY(), 0 + move.getZ()),
							new Point3D(-200 + move.getX(), -200 + move.getY(), 0 + move.getZ()),
							new Point3D(-200 + move.getX(), 200 + move.getY(), 0 + move.getZ())) //
									.setEmission(new Color(java.awt.Color.BLACK)) //
									.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(0 + move.getX(), 0 + move.getY(), 5 + move.getZ()), 5) //
							.setEmission(new Color(java.awt.Color.BLUE)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(20 + move.getX(), 30 + move.getY(), 10 + move.getZ()), 10) //
							.setEmission(new Color(java.awt.Color.PINK)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(-40 + move.getX(), -60 + move.getY(), 30 + move.getZ()), 30) //
							.setEmission(new Color(java.awt.Color.YELLOW)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(40 + move.getX(), -20 + move.getY(), 12 + move.getZ()), 12) //
							.setEmission(new Color(java.awt.Color.GRAY)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(-30 + move.getX(), 50 + move.getY(), 20 + move.getZ()), 20) //
							.setEmission(new Color(java.awt.Color.DARK_GRAY)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(70 + move.getX(), 70 + move.getY(), 15 + move.getZ()), 15) //
							.setEmission(new Color(java.awt.Color.BLUE)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(-60 + move.getX(), 0 + move.getY(), 20 + move.getZ()), 20) //
							.setEmission(new Color(java.awt.Color.PINK)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(160 + move.getX(), 0 + move.getY(), 20 + move.getZ()), 20) //
							.setEmission(new Color(java.awt.Color.CYAN)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(120 + move.getX(), -60 + move.getY(), 20 + move.getZ()), 20) //
							.setEmission(new Color(java.awt.Color.RED)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)),
					new Sphere(new Point3D(-30 + move.getX(), 50 + move.getY(), 20 + move.getZ()), 20) //
							.setEmission(new Color(java.awt.Color.DARK_GRAY)) //
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)));
			// scene.geometries.add(geometriesGroup);
		}
		scene.geometries.kMeansBoxes(5);
		scene.lights.add(new SpotLight(new Color(700, 700, 700), new Point3D(200, -200, 200), new Vector(-1, 1, -1)) //
				.setKl(4E-4).setKq(2E-5).setRaduis(30));
		scene.lights.add(new DirectionalLight(new Color(100, 100, 100), new Vector(-1, 1, -1)));
		scene.lights.add(new PointLight(new Color(500, 250, 250), new Point3D(150, 0, 100)) //
				.setKl(0.0005).setKq(0.0005));
		scene.lights.add(new SpotLight(new Color(700, 700, 700), new Point3D(0, -200, -90), new Vector(0.1, 1, -0.1)) //
				.setKl(4E-4).setKq(2E-5).setRaduis(30));
		scene.lights.add(new PointLight(new Color(250, 250, 250), new Point3D(0, 150, 50)) //
				.setKl(0.0005).setKq(0.0005));

		// );

//		Render render = new Render() //
//				.setImageWriter(new ImageWriter("boundingBeforeImprovement", 600, 600)) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene)).setDebugPrint().setMultithreading(3);
//		render.renderImage();
//		render.writeToImage();
		Render render = new Render() //
				.setImageWriter(new ImageWriter("boundingWithImprovement", 600, 600)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene).setImproveSoftSadows().setNumberRays(500).setImproveBoundingRegion()).setDebugPrint()
				.setMultithreading(3);
		render.renderImage();
		render.writeToImage();
	}

}
