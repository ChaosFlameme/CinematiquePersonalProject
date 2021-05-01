/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comment;

public class Comment {

    String user;
    int movieId;
    int note;
    String content;

    public Comment(int movieId, String user, int note, String content) {
        this.user = user;
        this.movieId = movieId;
        this.note = note;
        this.content = content;
    }

    public Comment() {
        this.user = null;
        this.movieId = 0;
        this.note = 0;
        this.content = null;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
