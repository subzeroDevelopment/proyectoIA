/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import java.awt.Dimension;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.util.jh.JHGrayFilter;
/**
 *
 * @author subzero
 */
public class Prueba implements WebcamImageTransformer{
    private static final Binarizar GRAY = new Binarizar();
    private static final JHGrayFilter gr = new JHGrayFilter();
    public Prueba() {

		Dimension[] nonStandardResolutions = new Dimension[] {
			WebcamResolution.PAL.getSize(),
			WebcamResolution.HD720.getSize(),
			new Dimension(2000, 1000),
			new Dimension(1000, 500),
		};
		//@formatter:on

		// your camera have to support HD720p to run this code
		Webcam webcam = Webcam.getDefault();
		webcam.setCustomViewSizes(nonStandardResolutions);
		webcam.setViewSize(WebcamResolution.HD720.getSize());
                webcam.setImageTransformer(this);
		webcam.open();
		
		JFrame window = new JFrame("Test Transformer");

		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setFillArea(true);
                
		window.add(panel);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
        
    
    
        @Override
	public BufferedImage transform(BufferedImage image) {
		return GRAY.filter(image, null);
	}

	public static void main(String[] args) {
		new Prueba();
	}
   

    
}
    


