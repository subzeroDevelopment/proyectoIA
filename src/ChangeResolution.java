/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import javax.swing.JFrame;

/**
 *
 * @author subzero
 */
public class ChangeResolution {
    public static void main(String [] args){
        /**
		 * When you set custom resolutions you have to be sure that your webcam
		 * device will handle them!
		 */

		//@formatter:off
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
}
