/**
 * 
 */
package renderer;

import primitives.*;
import java.awt.Color;
import java.awt.image.renderable.RenderableImage;
import java.util.MissingResourceException;

import elements.Camera;
import scene.Scene;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class Render {
	public Scene scene;
	private Camera camera;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;

	/**
	 * @param scene the scene to set
	 */
	public Render setScene(Scene scene) {
		this.scene = scene;
		return this;
	}

	/**
	 * @param camera the camera to set
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	/**
	 * @param imageWriter the imageWriter to set
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * @param rayTracer the rayTracer to set
	 */
	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	public void renderImage() {

		if (scene == null) {
			throw new MissingResourceException("scene is null", "Scene", "scene");
		}
		if (camera == null) {
			throw new MissingResourceException("camera is null", "Camera", "camera");
		}
		if (imageWriter == null) {
			throw new MissingResourceException("imageWriter is null", "ImageWriter", "imageWriter");
		}
		if (rayTracer == null) {
			throw new MissingResourceException("rayTracer is null", "RayTracerBase", "rayTracer");
		}
		for (int i = 0; i < imageWriter.getNx(); i++) {
			for (int j = 0; j < imageWriter.getNy(); j++) {
				Ray ray = camera.constructRayThroughPixel(imageWriter.getNx(), imageWriter.getNy(), j, i);
				primitives.Color color=rayTracer.traceRay(ray);
				imageWriter.writePixel(i, j, color);

			}
		}
	}

	public void printGrid(int interval, primitives.Color color) {
		if (imageWriter == null) {
			throw new MissingResourceException("imageWriter is null", "ImageWriter", "imageWriter");
		}
		for (int i = 0; i < imageWriter.getNx(); i++) {
			for (int j = 0; j < imageWriter.getNy(); j++) {
				if (i % interval == 0 || j % interval == 0) {
					imageWriter.writePixel(i, j, color);
				}
			}
		}
	}

	public void writeToImage() {
		if (imageWriter == null) {
			throw new MissingResourceException("imageWriter is null", "ImageWriter", "imageWriter");
		}
		imageWriter.writeToImage();
	}
}
