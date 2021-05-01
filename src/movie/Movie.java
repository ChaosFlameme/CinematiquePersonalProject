/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import org.json.JSONObject;

public class Movie {

    String name;

    String type;

    int ID;

    int year;

    //float stars;//Recommend rate
    public Movie(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Movie(String name, String type, int id) {
        this.name = name;
        this.type = type;
        this.ID = id;
    }

    public Movie(String name, String type, int id, int year) {
        this.name = name;
        this.type = type;
        this.ID = id;
        this.year = year;
    }

    public Movie() {
        this.name = null;
        this.type = null;
        this.ID = 0;
        this.year = 0;
    }

    
//GEEEEEEEEEEEEEEEEEEEEEETTTTTTTTTTTTTT ANNNNNNNNNNNNNNNNNNNNND SEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEETTTTTTTTTTTTTTTTTT

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
