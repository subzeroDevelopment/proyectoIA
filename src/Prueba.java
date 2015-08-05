/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.util.jh.JHGrayFilter;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;
/**
 *
 * @author subzero
 */
public class Prueba implements WebcamImageTransformer, WebcamMotionListener{
    private static final Binarizar GRAY = new Binarizar();
    private static final JHGrayFilter gr = new JHGrayFilter();
    Regiones contar;
    Webcam webcam = Webcam.getDefault();
    public Prueba() {

	JFrame window=new JFrame("1,2,3.....");
        
		
		//@formatter:on

		// your camera have to support HD720p to run this code  
        
        
        
        webcam.setViewSize(WebcamResolution.VGA.getSize());
       
        WebcamPanel panel = new WebcamPanel(webcam);
        webcam.setImageTransformer(this);
        WebcamMotionDetector detector = new WebcamMotionDetector(webcam);
		detector.setInterval(2000); // one check per 500 ms
		detector.addMotionListener(this);
		detector.start();
	panel.setFPSDisplayed(true);
	panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
	panel.setMirrored(true);
        
         webcam.open();
        window.add(panel);
	window.setResizable(true);
	window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	window.pack();
	window.setVisible(true);
                
		
	}
        
        @Override
	public void motionDetected(WebcamMotionEvent wme) {
		contar=new Regiones(webcam.getImage());
                System.out.println("numero: "+contar.nregiones);
	}
        
        @Override
	public BufferedImage transform(BufferedImage image) {
		return GRAY.filter(image, null);
	}

        public static void main(String[]args){
            Prueba p=new Prueba();
        }
   

    
}
    


