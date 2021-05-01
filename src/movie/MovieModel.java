/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class MovieModel extends AbstractTableModel{
    
    private static final String[] columnNames=
    {"Name",
    "Type"};//More name will be added
      
    private static final Class[] columnClasses=
    {String.class,
     String.class};
    
    //List of the watched movie
    private ArrayList<Movie> movieList=new ArrayList<Movie>();
    
    //Add a movie to table
    public void addMovie(Movie movie){
        
        movieList.add(movie);
        
        fireTableRowsInserted(getRowCount()-1,getRowCount()-1);
    }
    
    //Remove a movie from table
    public void removeMovie(int row){
        try{
            movieList.remove(row);
        
            fireTableRowsDeleted(row,row);
        }
        catch(Exception e)
        {
            System.out.println("No object can be removed");
        }
        
    }
    
    //Get movie in list
    public Movie getMovie(int row){
        return movieList.get(row);
    }
    
    //Get row name
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return movieList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie=movieList.get(rowIndex);
        switch (columnIndex){
            case 0://name
                return movie.getName();
            case 1://Type
                return movie.getType();
        }
        return null;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }
    
    
    
}
