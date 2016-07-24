import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64OutputStream;
/**
 * Klasa zajmujaca sie konwertowaniem obrazow do typu String
 */
public class ImageConverter {
	/**
	 * Funkcja kodujaca obraz do typu String
	 * @param image obraz
	 * @param type format obrazu, np. jpg
	 * @return String obrazu
	 */
	
	public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
             OutputStream b64 = new Base64OutputStream(bos);
             ImageIO.write(image, type, bos);
             imageString = bos.toString("UTF-8");
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            byte[] decoded = Base64.encodeBase64(bos.toByteArray());
            //System.out.println(new String(decoded, "UTF-8") + "\n");
            //System.out.println(isValidUTF8(decoded));
                    BASE64Encoder encoder = new BASE64Encoder();
            imageString = new String(decoded);//encoder.encode(imageBytes);


            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

	/**
	 * Funkcja dekodujaca obraz z typu String do formatu 
	 * @param imageString String obrazu
	 * @return obraz
	 */
	public static BufferedImage decodeToImage(String imageString) {

		BufferedImage image = null;
		byte[] imageByte;
		try {
		    BASE64Decoder decoder = new BASE64Decoder();
		    imageByte = decoder.decodeBuffer(imageString);
		    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		    image = ImageIO.read(bis);
		    bis.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return image;
	}


}
