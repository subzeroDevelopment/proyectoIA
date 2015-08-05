/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.FlowLayout;
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
    JLabel etiqueta=new JLabel();
    ImageIcon [] imgs={new ImageIcon("0.jpg"),new ImageIcon("1.png"),new ImageIcon("2.png"),new ImageIcon("3.png"),
                       new ImageIcon("4.jpg"),new ImageIcon("5.png"),new ImageIcon("6.jpg"),new ImageIcon("7.jpg"),
                        new ImageIcon("8.png"),new ImageIcon("9.png"),new ImageIcon("10.png")};
    public Prueba() {

	JFrame window=new JFrame("1,2,3.....");
        JFrame window2=new JFrame("Numero");
		
        
        
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
        
        ImageIcon ico=imgs[0 ];
        etiqueta.setIcon(ico);
         webcam.open();
         
       FlowLayout lay=new FlowLayout();
         window.setLayout(lay);
        window.add(panel);
        window.add(etiqueta);
	window.setResizable(true);
	window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	window.pack();
	window.setVisible(true);
                
		
	}
        
        @Override
	public void motionDetected(WebcamMotionEvent wme) {
		contar=new Regiones(webcam.getImage());
                System.out.println(contar.nregiones);
                etiqueta.setIcon(imgs[contar.nregiones]);
	}
        
        @Override
	public BufferedImage transform(BufferedImage image) {
		return GRAY.filter(image, null);
	}

        public static void main(String[]args){
            Prueba p=new Prueba();
        }
   

    
}
    


