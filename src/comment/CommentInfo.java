/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comment;

import java.util.ArrayList;


public class CommentInfo {
    private static final String fileName="userComment.txt";
    
    public static String getComments(int movieId,ArrayList<Comment> clist){
        String result="";
        for (int i = 0; i < clist.size(); i++) {
            Comment c=clist.get(i);
            if(movieId==c.getMovieId()){
                result+=c.getUser()+" "+c.getNote()+" stars : "+c.getContent()+"\n";
            }
        }
        return result;
    }
}
