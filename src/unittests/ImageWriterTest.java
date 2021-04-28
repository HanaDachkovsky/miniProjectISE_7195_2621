/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Color;
import renderer.*;

/**
 * @author Hana Dachkovsky and Sara Tamar Amitai
 *
 */
public class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	public void testWriteImage() {
		ImageWriter imageWriter = new ImageWriter("ImageTest", 800, 500);
		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 500; j++) {
				if (i % 50 == 0 || j % 50 == 0) {
					imageWriter.writePixel(i, j, Color.BLACK);
				} else {
					imageWriter.writePixel(i, j, new Color(java.awt.Color.PINK));
				}

			}
		}
		imageWriter.writeToImage();
	}
}
