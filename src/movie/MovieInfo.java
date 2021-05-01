/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.json.JSONArray;
import org.json.JSONObject;
import txt.TxtFile;
import web.ClientHttp;
import static web.JsonReader.readJsonFromUrl;

/**
 *
 * @author 19428
 */
public class MovieInfo {

    private static final String api_key = "?api_key=af5e4518e54d0bebc64182c27b6887f4";
    private static final String api_Title = "https://api.themoviedb.org/3/";
    private static final String api_Img="https://image.tmdb.org/t/p/original";

    //Decompose the search result to return a usable JSONObject which contains the info of the movie
    public static JSONObject searchResult(String name, String type) {
        JSONObject js = new JSONObject();
        JSONObject result = new JSONObject();
        name = replaceWithPlus(name);
        try {
            js = readJsonFromUrl(api_Title + "search/" + type + api_key + "&query=" + name);
            JSONArray res = js.getJSONArray("results");//The 'results' array contains a bunch of search result, we need to extract one
            result = res.getJSONObject(0);//Normally the first is the one we want, normally
        } catch (Exception e) {
            System.out.println("Search failed");
            return null;
        }
        return result;
    }

    public static JSONObject getDetail(int id, String type) {
        JSONObject js = new JSONObject();
        try {
            js = readJsonFromUrl(api_Title + type + "/" + id + api_key);
        } catch (Exception e) {
            System.out.println("Detail failed");
            return null;
        }
        return js;
    }

    //Make a string type which ready for the detail 
    public static String getStringDetail_Movie(JSONObject obj) {
        String res = "";

        res += addInfo("title", obj);

        res += addInfo("original_title", obj);
        
        res += addInfoSub("genres", "name", obj);

        res += addInfoSub("spoken_languages", "name", obj);

        res += addInfo("release_date", obj);

        res += addInfo("runtime", obj);

        res += addInfo("overview", obj);

        res += addInfo("budget", obj);

        res += addInfo("revenue", obj);

        res += addInfoSub("production_countries", "name", obj);

        //res += addInfoSub("production_companies", "name", obj);

        return res;
    }
    
    public static String getStringDetail_Person(JSONObject obj){
        String[] gender=new String[3];
        gender[0]="unknown";
        gender[1]="female";
        gender[2]="male";
        String res = "";

        res += addInfo("name", obj);

        try{
            String str="";           
            str="gender: "+gender[obj.getInt("gender")]+"\r\n";
            res+=str;
        }catch(Exception e){
            System.out.println("U serious?");
        }            

        res += addInfo("place_of_birth", obj);
        
        res += addInfo("birthday", obj);
        
        res += addInfo("deathday", obj);
        
        res += addInfo("known_for_department", obj);

        res += addInfo("biography", obj);      
        
        return res;
    }
    
    //To check the credit of movie contains the person
    //Won't it be toooo long? I dont have choice
    public static boolean containPerson(String name,int movieId){
        JSONArray jss=getMovieCredits(movieId);
        try{
            for (int i = 0; i < jss.length(); i++) {
                JSONObject js=jss.getJSONObject(i);
                if(js.getString("name").toLowerCase().contains(name.toLowerCase())){
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println("Contain Person Check Failed");
        }
        return false;
    }
    
    //Check generes
    public static boolean containGenre(String genre, int mid){
        JSONObject js=getDetail(mid, "movie");
        try{
            JSONArray genres=js.getJSONArray("genres");
            for (int i = 0; i < genres.length(); i++) {
                JSONObject jss=genres.getJSONObject(i);
                String mg=jss.getString("name").toLowerCase();
                if(mg.contains(genre.toLowerCase())){
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println("Contain genres Check Failed");
        }
        return false;
    }
    
    public static JSONArray getMovieCredits(int movieId) {       
        try{
            JSONObject js=readJsonFromUrl(api_Title+"movie/"+movieId+"/credits"+api_key);
            JSONArray jss=js.getJSONArray("cast");
            return jss;
        }catch(Exception e){
            System.out.println("Get Credits failed");
        }
        return null;
    }

    //Cause it need to repeat but not the same way, gotta make this to type easier
    public static String addInfo(String aim, JSONObject obj) {
        String str = "";
        try {

           str = aim + ": " + obj.get(aim) + "\r\n";
  
        } catch (Exception e) {
            System.out.println("addInfo failed or dont have such data"+aim);
        }
        return str;
    }

    //The one from arrrrrrrrray, can't be access directly(Reaaly?), usually one we want is the 'name' though add the sub   
    public static String addInfoSub(String aim, String sub, JSONObject obj) {
        String str = "";
        try {
            JSONArray res = obj.getJSONArray(aim);
            for (int i = 0; i < res.length(); i++) {
                JSONObject jss = res.getJSONObject(i);
                str += aim + ": " + jss.get(sub).toString() + "\r\n";
            }
        } catch (Exception e) {
            System.out.println("Add from array failed");
        }
        return str;
    }
    
    //Get the imageIcon whcih can be used for the jlabel
    public static ImageIcon getPosterIcon(String poster_path, JLabel jLabel_poster){
        ClientHttp cl=new ClientHttp();       
        cl.se_connecter(api_Img+poster_path, "GET");
        cl.recevoirImage();
        
        try {
            BufferedImage pic = ImageIO.read(new File("src/images/image.jpg"));
            pic = getScaledImage(pic, jLabel_poster.getWidth(), jLabel_poster.getHeight());    
            ImageIcon im = new ImageIcon(pic); 
            return im;
        } catch (Exception e) {
            System.out.println("909: Poster set wrong");
        }
        return null;       
    }
    
    //Use for new data which been added to the class 
    public static int refreshYear(int id) {
        JSONObject js=MovieInfo.getDetail(id, "movie");
        int year=0;
        try{
            String strYear=js.getString("release_date").split("-")[0];
            year=Integer.parseInt(strYear);
        }catch(Exception e){
            System.out.println("year get faield");
        }
        return year;
    }

    //Use this to make the name available in url: remplace space with +
    private static String replaceWithPlus(String name) {
        String[] seprate = name.split(" ");
        String out = "";
        for (int i = 0; i < seprate.length; i++) {
            out += seprate[i];
            if (i != seprate.length - 1) {
                out += "+";
            }
        }
        return out;
    }
    
    //Resize the image
    private static BufferedImage getScaledImage(BufferedImage srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    
    
    public void testSearchJson(String name, String type){
        JSONObject js = new JSONObject();       
        name = replaceWithPlus(name);
        try {
           // js = readJsonFromUrl(api_Title + "search/" + type + api_key + "&query="+name+"&year=" + 2019);
            js = readJsonFromUrl("https://api.themoviedb.org/3/movie/550/credits?api_key=af5e4518e54d0bebc64182c27b6887f4");
            //JSONArray res = js.getJSONArray("results");//The 'results' array contains a bunch of search result, we need to extract one
            File file=new File("test.json");            
            TxtFile.createFile(file);
            TxtFile.writeFile(js.toString(),file);
        } catch (Exception e) {
            System.out.println("Json file failed");
            
        }       
    }
    
    public void testDetailJson(int id, String type) {
        JSONObject js = new JSONObject();
        try {
            js = readJsonFromUrl(api_Title + type + "/" + id + api_key);
            File file=new File("test.json");
            TxtFile.createFile(file);
            TxtFile.writeFile(js.toString(),file);
        } catch (Exception e) {
            System.out.println("Detail failed");
            
        }      
    }
}
