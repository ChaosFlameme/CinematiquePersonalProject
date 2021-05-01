/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class ClientHttp {

    private HttpURLConnection connexion;
    private String reception;
    private String api_key="af5e4518e54d0bebc64182c27b6887f4";

    public ClientHttp() {
    }
    
    //Using example
    /*
    ClientHttp cl = new ClientHttp();
    cl.se_connecter("https://api.themoviedb.org/3/search/person?api_key=af5e4518e54d0bebc64182c27b6887f4&query=brad+pitt", "GET");
    cl.recevoir();
    System.out.println(cl.getReception());
        
    cl.se_connecter("https://image.tmdb.org/t/p/original/bptfVGEQuv6vDTIMVCHjJ9Dz8PX.jpg", "GET");
    cl.recevoirImage();//sauvegardée dans src/images (image.jpg)
*/
    
//Connect to the URL
    public boolean se_connecter(String adresse, String methode) {
        URL url = null;
        try {
            url = new URL(adresse);
        } catch (MalformedURLException ex) {
            return false;
        }
        try {
            connexion = (HttpURLConnection) url.openConnection();
            connexion.setDoOutput(true);
            connexion.setDoInput(true);
            connexion.setRequestMethod(methode);
            connexion.connect();
        } catch (IOException ex) {
            return false;
        }
        //System.out.println("connexion OK");
        return true;
    }

    public boolean recevoir() {
        InputStream inp = null;
        total = "";
        try {
            inp = connexion.getInputStream();
        } catch (IOException ex) {
            System.out.println("reception pas OK");
            return false;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(inp));
        try {
            int i = 0;
            while ((reception = in.readLine()) != null) {
                total += reception + "\n";
                //System.out.println("ligne " + i + " " + reception);
                i++;
            }
        } catch (IOException ex) {
            System.out.println("erreur reception");
            return false;
        }
        return true;
    }
    private String total = "";

    public String getReception() {
        return total;
    }

    public void recevoirImage() {
        InputStream is = null;
        try {
            is = new BufferedInputStream(connexion.getInputStream());
// Read the image and close the stream
            BufferedImage image = ImageIO.read(is);
            is.close();
            ImageIO.write(image, "jpg", new File("src/images/image.jpg"));
            //System.out.println("Receive img succeed");
        } catch (IOException ex) {
            Logger.getLogger(ClientHttp.class.getName()).log(Level.SEVERE, null,
                    ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHttp.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }
}
