/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.media.*;
import javax.media.cdm.CaptureDeviceManager;

/**
 *
 * @author subzero
 */
public class Prueba {
        public Prueba(){}

    public void Escaner(){
        //se recorre la cantidad de Dispositivos que encuentra disponibles
        for(int i=0; i<CaptureDeviceManager.getDeviceList().size();i++){
            //se muestra uno por uno en pantalla
           System.out.println( ( (CaptureDeviceInfo) CaptureDeviceManager.getDeviceList().get(i) ).getName() );
        }
    }
public static void main(String [] args){
    
   
      new Prueba().Escaner();
    
}
    
}

