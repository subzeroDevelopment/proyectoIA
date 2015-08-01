/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author subzero
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.*;
import javax.media.cdm.CaptureDeviceManager;
import java.io.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class jmfVideo {
    //Controlador universal de windows
    private String dispositivo = "vfw:Microsoft WDM Image Capture (Win32):0";
    private Player player = null;

    public Component Componente(){
    Component componente_video;
        try {
            // Se obtiene el dispositivo
            CaptureDeviceInfo device = CaptureDeviceManager.getDevice(dispositivo);
            //se obtiene la fuente de datos de captura
            MediaLocator localizador = device.getLocator();            
            //El localizador es del tipo "vfw://0" video para windows
            //se crea el PLAYER y se ejecuta
            player = Manager.createRealizedPlayer(localizador);
            player.start();            
        } catch (IOException ex) {
            Logger.getLogger(jmfVideo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(jmfVideo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotRealizeException ex) {
            Logger.getLogger(jmfVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Si se pudo crear el PLAYER, se obtiene el componente de video
        if ((componente_video = player.getVisualComponent()) != null) {
                //se da un tamaño al componente
                componente_video.setSize(320, 240);
                return componente_video;
            } else {
                JOptionPane.showMessageDialog(null,"No se pudo crear el video...");
                return null;
            }
    }

}