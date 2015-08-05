/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.github.sarxos.webcam.util.jh.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
/**
 *
 * @author subzero
 */
public class Binarizar extends JHFilter{
    protected boolean canFilterIndexColorModel = true;
    
    @Override
	public BufferedImage filter(BufferedImage src, BufferedImage dst) {

		int width = src.getWidth();
		int height = src.getHeight();
		int type = src.getType();

		WritableRaster srcRaster = src.getRaster();

		if (dst == null) {
			dst = createCompatibleDestImage(src, null);
		}

		WritableRaster dstRaster = dst.getRaster();

		  Color colorAux;
                  int color;
        
        //Recorremos la imagen píxel a píxel
        for( int i = 0; i < width; i++ ){
            for( int j = 0; j < height; j++ ){
                //Almacenamos el color del píxel
                colorAux=new Color(src.getRGB(i, j));
                
                //Calculamos la media de los tres canales (rojo, verde, azul)
                int mediaPixel=(int)((colorAux.getRed()+colorAux.getGreen()+colorAux.getBlue())/3);
                
                //Cambiamos a formato sRGB
                
                //Asignamos el nuevo valor al BufferedImage
                if(mediaPixel<=100 && mediaPixel>50)dst.setRGB(i, j,0);
                else{
                    color=(255 << 16) | (255 << 8) | 255;
                    dst.setRGB(i, j,color);
                } 
                
                //System.out.println("gr
            }
        }
                
		return dst;
	}
        
      
}



	

	

