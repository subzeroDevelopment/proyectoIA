/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
/**
 *
 * @author subzero
 */
public class Regiones {
    BufferedImage img;
    int [][] matriz;
    int alto;
    int ancho;
    int nregiones;
    ArrayList<ArrayList> equivalencia=new ArrayList(); 
    Regiones(BufferedImage im){
        img=im;
        convertir();
        ochovec();
    
    }
    public void ochovec(){
        int etiqueta=1;
        for(int i=1;i<(alto-1);i++){
            for(int j=1;(j<ancho-1);j++){
                     if(matriz[i][j]==1 && condicion(i,j)==false){
                         //System.out.println("Priemera condicion");
                         int []ar={matriz[i][j-1],matriz[i-1][j-1],matriz[i-1][j],matriz[i-1][j+1]};
                         matriz[i][j]=numero(i,j,ar);
                         //System.out.println(matriz[i][j]);
                     }
                     else if(matriz[i][j]==1 && condicion(i,j)==true){
                         //System.out.println("segunda condicion");
                         matriz[i][j]=etiqueta;
                         etiqueta++;
                          //System.out.println(matriz[i][j]);
                     }
                     
            }
        }
        //System.out.println();
        
        int y=0;
        for(int i=0;i<equivalencia.size();i++){
             if(equivalencia.get(y).size()<2){
                 equivalencia.remove(y);
             }
             y++;
        }
        
        y=0;
        for(int i=0;i<equivalencia.size();i++){
            //System.out.println(iguales(equivalencia.get(y)));
             if(iguales(equivalencia.get(y))){
                 equivalencia.remove(y);
             }
             y++;
        }
        int in=0;
         for(int i=1;i<(alto-1);i++){
            for(int j=1;(j<ancho-1);j++){
                in=0;
                if(matriz[i][j]!=0)
                    while(in<equivalencia.size()){
                        
                        if(equivalencia.get(in).indexOf(matriz[i][j])>-1){
                            matriz[i][j]=(int)equivalencia.get(in).get(0);
                            //System.out.println("in:"+in);
                        }
                        in++;
                    }
                
                }
            
            
            }
         ArrayList arre=new ArrayList(); 
         int cout=0;
         for(int i=1;i<(alto-1);i++){
            for(int j=1;(j<ancho-1);j++){
                if(matriz[i][j]!=0 && arre.indexOf(matriz[i][j])==-1){
                    cout++;
                    arre.add(matriz[i][j]);
                
                }
            
            
            }
         }
         ArrayList areas=new ArrayList();
         cout=0;
      for(int p=0;p<arre.size();p++){
          //System.out.println("arre="+arre.get(p));
          int numero=(int)arre.get(p);
         for(int i=1;i<(alto-1);i++){
            for(int j=1;(j<ancho-1);j++){
                if(matriz[i][j]==numero)cout++;
            }
         }
        // System.out.println("cout="+cout);
         if(cout>=10000)areas.add(cout);
         cout=0;
       }
         
      nregiones=areas.size();
    }
        
    //cout>=10954 && cout<=12954
    
    boolean iguales(ArrayList list){
        if(list.size()==1)return false;
        int i=1;
        int con=0;
        int mue=(int)list.get(0);
        while(i<list.size()){
            if((int)list.get(i)==mue)con++;
            i++;
        }
        if(con==list.size()-1)return true;
        else return false;
    }
    boolean condicion(int i,int j){
        if(matriz[i][j-1]==0 && matriz[i-1][j-1]==0 && matriz[i-1][j]==0 && matriz[i-1][j+1]==0){
            return true;
        }
        return false;
    }
    
    int numero(int i,int j,int [] ar){
        //System.out.println("tamaño equi:"+equivalencia.size());
        int num;
        int indx=-1;
        ArrayList lista=new ArrayList();
        int in=0;
        if(equivalencia.isEmpty()==false){
        for(int c=0;c<ar.length;c++){
            //System.out.print(ar[c]);
            while(in<equivalencia.size()){
                if(equivalencia.get(in).indexOf(ar[c])>-1){
                    indx=in;
                    
                 }
                in++;
            }
        }}
        
        //System.out.println("index:"+indx);
        int regreso=-1;
        ArrayList nue=new ArrayList();
        if(indx==-1){
            for(int d=0;d<ar.length;d++){
               if(ar[d]!=0)nue.add(ar[d]);
                
            }
            regreso=(int)nue.get(0);
            equivalencia.add(nue);
            
        }
        else{
            for(int h=0;h<ar.length;h++){
                if(ar[h]!=0 && equivalencia.get(indx).indexOf(ar[h])<0)equivalencia.get(indx).add(ar[h]);
            }
            regreso=(int)equivalencia.get(indx).get(0);
        }
       
        return regreso;
        
        
    }
    
    
    public static void main(String [] args){
  /*int [][]mat={    {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                   {0,  0,  0,  1,  1,  1,  0,  1,  1,  0,  0,  0},
                   {0,  1,  1,  1,  1,  1,  0,  0,  0,  0,  0,  0},
                   {0,  1,  1,  0,  1,  1,  0,  0,  0,  0,  0,  0},
                   {0,  1,  1,  1,  1,  1,  0,  0,  0,  0,  1,  0},
                   {0,  1,  1,  1,  1,  1,  0,  0,  0,  1,  1,  0},
                   {0,  1,  1,  1,  0,  0,  0,  1,  1,  1,  1,  0},
                   {0,  0,  0,  0,  1,  1,  0,  1,  1,  1,  1,  0},
                   {0,  0,  0,  1,  1,  1,  0,  1,  1,  1,  1,  0},
                   {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0} };*/
  BufferedImage bmp=null;
        try{
        bmp=ImageIO.read(new File("/home/subzero/NetBeansProjects/proyectoIA/foto0.png"));
        ImageIO.write(bmp, "PNG", new File("Prueba.png"));
        }
  catch(Exception e){
   
  }
       
   Regiones obj=new Regiones(bmp);
    
    
    
    for(int i=0;i<obj.alto;i++){
        for(int j=0;j<obj.ancho;j++){
            System.out.print(obj.matriz[i][j]+" ");        
        }
        System.out.println();
    }
    
    System.out.println("el numero de regiones es:"+obj.nregiones);
    
   }
    
    
 
    public void convertir(){
        alto=img.getHeight();
        ancho=img.getWidth();
        matriz=new int[alto][ancho];
        for(int i=0;i<alto;i++){
            for(int j=0;j<ancho;j++){
              if(j==0 || i==0||i==alto-1||j==ancho-1){      
                    if(i==0){
                        matriz[i][j]=0;
                        }
                    if(j==0){
                        matriz[i][j]=0;
                    }
                    if(i==alto-1){
                        matriz[i][j]=0;
                    }
                    if(j==ancho-1){
                        matriz[i][j]=0;
                    }
              }
              else{
                  Color x=new Color(img.getRGB(j, i));
                if(x.equals(Color.BLACK)){
                    matriz[i][j]=1;
                }
                else{
                    matriz[i][j]=0;
                }
                }
            }
        }
        
    
    }
    
    
    
    
}
