/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author subzero
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
public class convertirGris {
       private BufferedImage imageActual;
       
       Color[][] rgb;
       int[][] rojo;
       int[][] verde;
       int[][] azul;
       int[][] gris;
       int alto;
       int ancho;
       int promedio=0;
       int minimo=255;
       int maximo=0;
     
    //Método que devuelve una imagen abierta desde archivo
    //Retorna un objeto BufferedImagen
    public BufferedImage abrirImagen(){
        //Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp=null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector=new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP & PNG", "jpg", "gif", "bmp","png");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada=selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }
                  
        }
        alto=bmp.getHeight();
        ancho=bmp.getWidth();
        rgb=new Color[ancho][alto];
        rojo=new int[ancho][alto];
        verde=new int[ancho][alto];
        azul=new int[ancho][alto];
        gris=new int[ancho][alto];
        //Asignamos la imagen cargada a la propiedad imageActual
        imageActual=bmp;
        //Retornamos el valor
        return bmp;
    }
    
    public BufferedImage escalaGrises(){
        //Variables que almacenarán los píxeles
        int mediaPixel,color;
        
        Color colorAux;
      
        
        //Recorremos la imagen píxel a píxel
        for( int i = 0; i < imageActual.getWidth(); i++ ){
            for( int j = 0; j < imageActual.getHeight(); j++ ){
                //Almacenamos el color del píxel
                colorAux=new Color(this.imageActual.getRGB(i, j));
                
                //Calculamos la media de los tres canales (rojo, verde, azul)
                mediaPixel=(int)((colorAux.getRed()+colorAux.getGreen()+colorAux.getBlue())/3);
                rojo[i][j]=colorAux.getRed();
                verde[i][j]=colorAux.getGreen();
                azul[i][j]=colorAux.getBlue();
                promedio=promedio+mediaPixel;
                if(mediaPixel>maximo)maximo=mediaPixel;
                if(mediaPixel<minimo)minimo=mediaPixel;
                //Cambiamos a formato sRGB
                int colorSRGB=(mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
                //Asignamos el nuevo valor al BufferedImage
                /*if(colorAux.getRed()<90 && colorAux.getRed()>59)imageActual.setRGB(i, j,0);
                else{
                    color=(255 << 16) | (255 << 8) | 255;
                    imageActual.setRGB(i, j,color);
                } */
                
                //System.out.println("gr
            }
        }
        //Retornamos la imagen
        promedio=(int)promedio/(imageActual.getWidth()*imageActual.getHeight());
        return imageActual;
    }
    public static void main(String[] arre){
        convertirGris img=new convertirGris();
        img.abrirImagen();
        img.escalaGrises();
       
        
        
       
        System.out.println("promedio:"+img.promedio);
        System.out.println("maximo:"+img.maximo);
        System.out.println("Minimo"+img.minimo);
    
    }

    
}
