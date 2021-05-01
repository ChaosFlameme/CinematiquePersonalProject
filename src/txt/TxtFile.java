/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package txt;

import comment.Comment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.*;
import movie.Movie;
import movie.MovieInfo;

public class TxtFile {

    //whether create new file
    public static boolean createFile(File fileName) throws Exception {
        try {
            if (!fileName.exists()) {
                fileName.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    //Read txt file, need to be customize for the project
    public static String readFile(File file) {
        String result = "";

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));

            BufferedReader br = new BufferedReader(reader);

            String s = null;

            //need to be modified
            while ((s = br.readLine()) != null) {
                result = result + "\r\n" + s;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //Use the string that got from the readFile and turn that into a movieList
    public static ArrayList<Movie> getMovieList(File file) {
        String result = readFile(file);
        if (result == "") {
            System.out.println("The Result in Movie file is null!");
            return new ArrayList();
        }

        ArrayList<Movie> movieList = new ArrayList();

        Movie movie = new Movie();
        //First spilt by line
        String[] s = result.split("\\r?\\n");

        for (int i = 1; i < s.length; i++) {
            //Second spilt by ","
            String[] ss = s[i].split(",");
            String movieName = new String();
            String movieType = new String();
            int movieID = 0;
            int movieYear = 0;

            for (int j = 0; j < ss.length; j++) {

                switch (j) {
                    case 0://name
                        movieName = ss[j];
                        break;

                    case 1://Type
                        movieType = ss[j];
                        break;

                    case 2://ID in movieDB
                        movieID = Integer.parseInt(ss[j]);
                        break;

                    case 3://Release year                       
                        movieYear = Integer.parseInt(ss[j]);
                        if (movieYear == 0) {
                            movieYear = MovieInfo.refreshYear(movieID);
                        }
                        break;

                    default:
                        System.out.println("Got error in sswithc case");
                        break;
                }

            }
            movie = new Movie(movieName, movieType, movieID, movieYear);
            movieList.add(movie);
        }

        return movieList;
    }

    public static HashMap<String, String> getUserMap(File file) {
        String result = readFile(file);

        if (result == "") {
            System.out.println("The Result in Map file is null!");
            return null;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        String[] s = result.split("\\r?\\n");
        String[] ss = new String[2];
        for (int i = 0; i < s.length; i++) {
            ss = s[i].split(",");
            try {
                String username = ss[0];
                String password = ss[1];
                map.put(username, password);
            } catch (Exception e) {
                //It will pass once, ignore it
            }

        }
        return map;
    }
    
    static final String splitP="\r?\n";//For paragraph
    static final String splitI=";;";//For items

    public static ArrayList<Comment> getCommentList(File file) {
        String result = readFile(file);
        if (result == "") {
            System.out.println("Comment file null!");
            return new ArrayList();
        }

        ArrayList<Comment> cList = new ArrayList();
        Comment comment = new Comment();
        String[] s = result.split(splitP);
        for (int i = 0; i < s.length; i++) {
            String[] ss = s[i].split(splitI);
            int movieId = 0;
            int note = 0;
            String userName = new String();
            String content = new String();
            for (int j = 0; j < ss.length; j++) {

                switch (j) {
                    case 0://user name
                        userName = ss[j];
                        break;

                    case 1://movieId                        
                        movieId = Integer.parseInt(ss[j]);
                        break;

                    case 2://note
                        note = Integer.parseInt(ss[j]);
                        break;

                    case 3://content                    
                        content = ss[j];
                        break;

                    default:
                        System.out.println("Got error in sswithc case");
                        break;
                }

            }
            comment = new Comment(movieId, userName, note, content);
            cList.add(comment);
        }
        return cList;
    }

    public static boolean writeCFile(ArrayList<Comment> clist, File fileName) throws Exception {
        boolean flag = false;

        String convertedString = new String();
        FileOutputStream fileOutputStream = null;

        for (int i = 0; i < clist.size(); i++) {
            convertedString += clist.get(i).getUser() + splitI
                    + clist.get(i).getMovieId() + splitI
                    + clist.get(i).getNote() + splitI
                    + clist.get(i).getContent() + splitP;
        }

        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(convertedString.getBytes());
            fileOutputStream.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean writeFile(Map<String, String> map, File fileName) throws Exception {
        boolean flag = false;

        String convertedString = new String();
        FileOutputStream fileOutputStream = null;

        for (String key : map.keySet()) {
            convertedString += key + "," + map.get(key) + "\n";
        }

        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(convertedString.getBytes());
            fileOutputStream.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //Overwrite the file
    public static boolean writeFile(ArrayList<Movie> mlist, File fileName) throws Exception {

        boolean flag = false;

        String convertedString = "";//Use for converting the movieList to the string

        //!!!!!Here to extend the movie type! 
        for (int i = 0; i < mlist.size(); i++) {
            convertedString += mlist.get(i).getName() + ","
                    + mlist.get(i).getType() + ","
                    + mlist.get(i).getID() + ","
                    + mlist.get(i).getYear();
            convertedString += "\n";
        }

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(convertedString.getBytes());
            fileOutputStream.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean writeFile(String str, File fileName) throws Exception {

        boolean flag = false;

        String convertedString = str;//Use for converting the movieList to the string

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(convertedString.getBytes());
            fileOutputStream.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
