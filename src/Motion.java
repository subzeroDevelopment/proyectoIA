/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;

/**
 *
 * @author subzero
 */
public class Motion implements WebcamMotionListener{
    
    public Motion() {
		WebcamMotionDetector detector = new WebcamMotionDetector(Webcam.getDefault());
		detector.setInterval(500); // one check per 500 ms
		detector.addMotionListener(this);
		detector.start();
	}
        
    @Override
	public void motionDetected(WebcamMotionEvent wme) {
		System.out.println("Detected motion I, alarm turn on you have");
	}

        public static void main(String[] args) throws IOException {
		new Motion();
		System.in.read(); // keep program open
	}
    
    
}
