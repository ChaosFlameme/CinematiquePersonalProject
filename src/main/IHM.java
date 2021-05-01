/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Login.LoginDialog;
import comment.Comment;
import comment.CommentInfo;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import movie.Movie;
import movie.MovieInfo;
import movie.MovieModel;
import org.json.JSONException;
import org.json.JSONObject;
import txt.TxtFile;
import web.ClientHttp;

public class IHM extends javax.swing.JFrame {

    ArrayList<Movie> movieList;
    ArrayList<Comment> commentList;
    MovieModel localMovieModel, searchMovieModel;
    LoginDialog ld;
    File movieList_txt;
    File userMap_txt;
    File userComment_txt;
    ClientHttp cl;

    String currentUser;

    /**
     * Creates new form IHM.It's Start()!
     *
     * @throws java.lang.Exception
     */
    public IHM() throws Exception {

        initComponents();

        cl = new ClientHttp();

        reinitComponent();

        this.setLocation(500, 200);

        Dimension size = new Dimension(204, 326);
        jLabel_poster.setMinimumSize(size);
        jLabel_poster.setMaximumSize(size);
        jLabel_poster.setPreferredSize(size);

    }

    //Cut the time for me to locate the component, so just let ide init one then I rewrite it
    private void reinitComponent() throws Exception {
        //Movielist file
        initMovielist();

        //UserMap
        initUsermap();

        //Table
        initTable();

        //JTextArea
        initTextArea();

        //Comment part
        initComment();

        //For test
        try {

        } catch (Exception exxx) {
            System.out.println("As i expected");
            exxx.printStackTrace();
        }

    }

    private void initMovielist() throws Exception {
        movieList_txt = new File("movieList.txt");
        TxtFile.createFile(movieList_txt);

        this.setResizable(false);

        localMovieModel = new MovieModel();
        searchMovieModel = new MovieModel();

        movieList = new ArrayList();

        localMovieModel.setMovieList(TxtFile.getMovieList(movieList_txt));
        movieList = localMovieModel.getMovieList();
    }

    private void initUsermap() throws Exception {
        userMap_txt = new File("userMap.txt");
        TxtFile.createFile(userMap_txt);
        currentUser = "";
        ld = new LoginDialog(this, userMap_txt, this);
        ld.pack();
    }

    private void initTable() {
        table = new JTable(localMovieModel);
        jScrollPane1.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                if (row == -1) {
                    return;
                }
                MovieModel model = (MovieModel) table.getModel();//Make sure it's the current table, that's important
                int movieId = model.getMovie(row).getID();
                String result = new String();
                try {
                    JSONObject js = MovieInfo.getDetail(movieId, "movie");
                    result = MovieInfo.getStringDetail_Movie(js);
                    //Comment part
                    result += "-------COMMENT-------\n";
                    System.out.println("Sup " + CommentInfo.getComments(movieId, commentList));
                    result += CommentInfo.getComments(movieId, commentList);
                    //Comment part over
                    String posterSrc = js.getString("poster_path");
                    jLabel_poster.setIcon(MovieInfo.getPosterIcon(posterSrc, jLabel_poster));
                } catch (JSONException ex) {
                    System.out.println("Bad detail");
                }
                jTextArea_MovieDetail.setText(result);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void initTextArea() {
        jTextArea_MovieDetail.setLineWrap(true);// Auto new line
        jTextArea_MovieDetail.setWrapStyleWord(true);//Won't break the line
    }

    private void initComment() throws Exception {
        jPanel_Comment.setVisible(false);

        jLabel_wych.setVisible(false);

        userComment_txt = new File("userComment.txt");
        TxtFile.createFile(userComment_txt);
        commentList = TxtFile.getCommentList(userComment_txt);

        jTextArea_MovieDetail.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField_input = new javax.swing.JTextField();
        jButton_MovieNameConfirm = new javax.swing.JButton();
        jComboBox_MovieType = new javax.swing.JComboBox<>();
        jButton_LocalPage = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton_Search = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_MovieDetail = new javax.swing.JTextArea();
        jLabel_poster = new javax.swing.JLabel();
        jComboBox_searchType = new javax.swing.JComboBox<>();
        jLabel_SearchTips1 = new javax.swing.JLabel();
        jLabel_ConfirmTips = new javax.swing.JLabel();
        jButton_UserLogin = new javax.swing.JButton();
        jLabel_User = new javax.swing.JLabel();
        jButton_Comment = new javax.swing.JButton();
        jPanel_Comment = new javax.swing.JPanel();
        jComboBox_star = new javax.swing.JComboBox<>();
        jButton_CancelComment = new javax.swing.JButton();
        jButton_ConfirmComment = new javax.swing.JButton();
        jLabel_star = new javax.swing.JLabel();
        jLabel_wych = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cinematique");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField_input.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N

        jButton_MovieNameConfirm.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jButton_MovieNameConfirm.setText("Confirm");
        jButton_MovieNameConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MovieNameConfirmActionPerformed(evt);
            }
        });

        jComboBox_MovieType.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jComboBox_MovieType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DVD", "Blu-Ray", "Numérique", "Autre" }));

        jButton_LocalPage.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jButton_LocalPage.setText("Local movie");
        jButton_LocalPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LocalPageActionPerformed(evt);
            }
        });

        jButton_Delete.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jButton_Delete.setText("Delete");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        jButton_Search.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jButton_Search.setText("Search");
        jButton_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });

        jTextArea_MovieDetail.setEditable(false);
        jTextArea_MovieDetail.setColumns(20);
        jTextArea_MovieDetail.setRows(5);
        jScrollPane3.setViewportView(jTextArea_MovieDetail);

        jComboBox_searchType.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jComboBox_searchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "person", "year", "genre" }));

        jLabel_SearchTips1.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel_SearchTips1.setText(" Search something:");

        jLabel_ConfirmTips.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel_ConfirmTips.setText(" Register a new movie:");

        jButton_UserLogin.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jButton_UserLogin.setText("Login");
        jButton_UserLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UserLoginActionPerformed(evt);
            }
        });

        jLabel_User.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel_User.setText("You have not login:");

        jButton_Comment.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jButton_Comment.setText("Comment");
        jButton_Comment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CommentActionPerformed(evt);
            }
        });

        jPanel_Comment.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N

        jComboBox_star.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jComboBox_star.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        jComboBox_star.setSelectedIndex(2);

        jButton_CancelComment.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jButton_CancelComment.setText("Annuler");
        jButton_CancelComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelCommentActionPerformed(evt);
            }
        });

        jButton_ConfirmComment.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jButton_ConfirmComment.setText("Confirm");
        jButton_ConfirmComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConfirmCommentActionPerformed(evt);
            }
        });

        jLabel_star.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jLabel_star.setText("star(s)");

        javax.swing.GroupLayout jPanel_CommentLayout = new javax.swing.GroupLayout(jPanel_Comment);
        jPanel_Comment.setLayout(jPanel_CommentLayout);
        jPanel_CommentLayout.setHorizontalGroup(
            jPanel_CommentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CommentLayout.createSequentialGroup()
                .addGroup(jPanel_CommentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_CommentLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jComboBox_star, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_star))
                    .addGroup(jPanel_CommentLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel_CommentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_CancelComment, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_ConfirmComment, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel_CommentLayout.setVerticalGroup(
            jPanel_CommentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CommentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_CommentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_star, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_star))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_ConfirmComment, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_CancelComment, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel_wych.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jLabel_wych.setText("Write your comment here:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField_input, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_ConfirmTips)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel_SearchTips1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_User)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_UserLogin))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox_MovieType, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_MovieNameConfirm)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_searchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Search)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_wych, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_poster, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_LocalPage, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Delete)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Comment))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_Comment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_Comment, jButton_Delete, jButton_LocalPage});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_ConfirmTips, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_UserLogin)
                        .addComponent(jLabel_User))
                    .addComponent(jLabel_SearchTips1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_MovieType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_MovieNameConfirm))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_poster, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel_Comment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton_LocalPage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton_Delete)
                                        .addComponent(jButton_Comment))))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_searchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Search)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_wych, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_Comment, jButton_Delete, jButton_LocalPage});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Confirm button: add movie to list: Still need to register more details
    private void jButton_MovieNameConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MovieNameConfirmActionPerformed
        //This button will read the text of the movie name and register it into the table
        table.setModel(localMovieModel);
        String movieName = jTextField_input.getText();
        if (movieName.isEmpty()) {
            return;
        }
        String movieType = jComboBox_MovieType.getSelectedItem().toString();

        //Update file
        try {
            int movieID = MovieInfo.searchResult(movieName, "movie").getInt("id");
            //WARNING!!!! not include series!
            JSONObject js = MovieInfo.getDetail(movieID, "movie");
            movieName = js.getString("title");//Make the name more proper
            String strYear = js.getString("release_date").split("-")[0];
            int year = Integer.parseInt(strYear);
            Movie registerMovie = new Movie(movieName, movieType, movieID, year);//Use to initialize one for registion           
            localMovieModel.addMovie(registerMovie);
            TxtFile.writeFile(this.movieList, movieList_txt);
        } catch (Exception e) {
            jTextArea_MovieDetail.setText("Can't find " + movieName + " !");
            jLabel_poster.setIcon(null);
            System.out.println("File to be written not found! Or whatever");
        }

    }//GEN-LAST:event_jButton_MovieNameConfirmActionPerformed

    //No more detail button! :)
    private void jButton_LocalPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LocalPageActionPerformed
        table.setModel(localMovieModel);

    }//GEN-LAST:event_jButton_LocalPageActionPerformed

    //Delete one from table, that's it
    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        if (!table.getModel().equals(localMovieModel)) {
            return;
        }
        int rowSelected = table.getSelectedRow();

        localMovieModel.removeMovie(rowSelected);

        //Update to txt, same thing as add sth, it overwrites the txt anyway, how convinient it is huh?
        try {
            TxtFile.writeFile(this.movieList, movieList_txt);
        } catch (Exception e) {
            System.out.println("File to be written not found!");
        }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SearchActionPerformed
        searchMovieModel = new MovieModel();
        table.setModel(searchMovieModel);

        String searchContent = jTextField_input.getText();
        String searchType = jComboBox_searchType.getSelectedItem().toString();
        if (searchContent.isEmpty()) {
            return;
        }

        //Well I decide just to use if
        if (searchType.equals("person")) {
            try {
                //Person timeeeeeeeeeeeeee
                JSONObject js = MovieInfo.searchResult(searchContent, searchType);
                int personID = js.getInt("id");
                JSONObject jd = MovieInfo.getDetail(personID, searchType);
                String shownRes = MovieInfo.getStringDetail_Person(jd);
                jTextArea_MovieDetail.setText(shownRes);

                String posterSrc = js.getString("profile_path");
                jLabel_poster.setIcon(MovieInfo.getPosterIcon(posterSrc, jLabel_poster));

                String personName = jd.getString("name");

                //Movie: Search local to see if the movie contains the personssosnsnson
                ArrayList<Movie> list = localMovieModel.getMovieList();
                int localListLength = list.size();
                for (int i = 0; i < localListLength; i++) {
                    Movie mo = list.get(i);
                    if (MovieInfo.containPerson(personName, mo.getID())) {
                        searchMovieModel.addMovie(mo);
                    }
                }

            } catch (Exception e) {
                jTextArea_MovieDetail.setText("No person called " + searchContent + " found.");
                jLabel_poster.setIcon(null);
                System.out.println("Search person failed");
            }
            /*
                //Movie: Search person's known for movie
                JSONArray jss = js.getJSONArray("known_for");                
                for (int i = 0; i < jss.length(); i++) {
                    JSONObject current = jss.getJSONObject(i);
                    Movie movie = new Movie();
                    try {
                        String movieName = current.getString("title");
                        int movieID = current.getInt("id");
                        movie = new Movie(movieName, "Autre", movieID);
                        searchMovieModel.addMovie(movie);
                    } catch (Exception e) {
                        System.out.println("Array failed");
                    }
                }
             */
        }

        //Use year to search
        if (searchType.equals("year")) {
            try {
                int sy = Integer.parseInt(searchContent);

                movieList = localMovieModel.getMovieList();
                for (int i = 0; i < movieList.size(); i++) {
                    Movie m = movieList.get(i);
                    int my = m.getYear();
                    if (sy == my) {
                        searchMovieModel.addMovie(m);
                    }
                }
                if (searchMovieModel.getRowCount() == 0) {
                    jTextArea_MovieDetail.setText("No movie released in " + sy + " found.");
                    jLabel_poster.setIcon(null);
                }
            } catch (Exception e) {
                jTextArea_MovieDetail.setText("Wrong input!");
                jLabel_poster.setIcon(null);
            }
        }

        if (searchType.equals("genre")) {
            movieList = localMovieModel.getMovieList();
            for (int i = 0; i < movieList.size(); i++) {
                Movie m = movieList.get(i);
                int mid = m.getID();
                if (MovieInfo.containGenre(searchContent, mid)) {
                    searchMovieModel.addMovie(m);
                }
            }
            if (searchMovieModel.getRowCount() == 0) {
                jTextArea_MovieDetail.setText("No movie in genre: " + searchContent + " found.");
                jLabel_poster.setIcon(null);
            }

        }

    }//GEN-LAST:event_jButton_SearchActionPerformed

    private void jButton_UserLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UserLoginActionPerformed
        if (!userStatus()) {
            ld.setVisible(true);
        } else {
            currentUser = "";
            userStatus();
        }
    }//GEN-LAST:event_jButton_UserLoginActionPerformed

    private void jButton_CancelCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelCommentActionPerformed

        commentActive(false);

    }//GEN-LAST:event_jButton_CancelCommentActionPerformed

    private void jButton_CommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CommentActionPerformed
        if (!userStatus()) {
            JOptionPane.showMessageDialog(this, "You should login first!", "Tip", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "You should choose a film first", "Tip", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        MovieModel model = (MovieModel) table.getModel();//Make sure it's the current table, that's important
        int movieId = model.getMovie(row).getID();

        commentActive(true);

        int hc = getHasComment(movieId);
        if (hc != -1) {
            Comment c = commentList.get(hc);
            jTextArea_MovieDetail.setText(c.getContent());
            jComboBox_star.setSelectedIndex(c.getNote() - 1);
        }

    }//GEN-LAST:event_jButton_CommentActionPerformed

    private void jButton_ConfirmCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConfirmCommentActionPerformed
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "You should choose a film first", "Tip", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        MovieModel model = (MovieModel) table.getModel();//Make sure it's the current table, that's important
        int movieId = model.getMovie(row).getID();
        int note = jComboBox_star.getSelectedIndex() + 1;
        String content = jTextArea_MovieDetail.getText();
        Comment nc = new Comment(movieId, currentUser, note, content);
        int hc = getHasComment(movieId);
        if (hc != -1) {
            commentList.remove(hc);
        }
        commentList.add(nc);

        //Write in txt
        try {
            TxtFile.writeCFile(commentList, userComment_txt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "Comment success!", "Tip", JOptionPane.INFORMATION_MESSAGE);

        commentActive(false);

    }//GEN-LAST:event_jButton_ConfirmCommentActionPerformed

    private void commentActive(boolean t) {
        jPanel_Comment.setVisible(t);
        jLabel_wych.setVisible(t);
        jTextArea_MovieDetail.setText("");
        jTextArea_MovieDetail.setEditable(t);
        jComboBox_star.setSelectedIndex(2);
        table.setEnabled(!t);
    }

    private boolean alreadyHasComment(int movieId) {
        for (int i = 0; i < commentList.size(); i++) {
            Comment c = commentList.get(i);
            if (c.getMovieId() == movieId && c.getUser().equals(currentUser)) {

                return true;
            }
        }
        return false;
    }

    private int getHasComment(int movieId) {
        for (int i = 0; i < commentList.size(); i++) {
            Comment c = commentList.get(i);
            if (c.getMovieId() == movieId && c.getUser().equals(currentUser)) {

                return i;
            }
        }
        return -1;
    }

    private boolean userStatus() {
        if (currentUser.isEmpty()) {
            jLabel_User.setText("You have not login:");
            jButton_UserLogin.setText("Login");
            return false;
        }
        jLabel_User.setText("Welcome: " + currentUser);
        jButton_UserLogin.setText("Log out");
        return true;
    }

    public void setCurrentUser(String name) {
        this.currentUser = name;
        userStatus();
    }

    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new IHM().setVisible(true);

                } catch (Exception ex) {
                    //Logger.getLogger(IHM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_CancelComment;
    private javax.swing.JButton jButton_Comment;
    private javax.swing.JButton jButton_ConfirmComment;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_LocalPage;
    private javax.swing.JButton jButton_MovieNameConfirm;
    private javax.swing.JButton jButton_Search;
    private javax.swing.JButton jButton_UserLogin;
    private javax.swing.JComboBox<String> jComboBox_MovieType;
    private javax.swing.JComboBox<String> jComboBox_searchType;
    private javax.swing.JComboBox<String> jComboBox_star;
    private javax.swing.JLabel jLabel_ConfirmTips;
    private javax.swing.JLabel jLabel_SearchTips1;
    private javax.swing.JLabel jLabel_User;
    private javax.swing.JLabel jLabel_poster;
    private javax.swing.JLabel jLabel_star;
    private javax.swing.JLabel jLabel_wych;
    private javax.swing.JPanel jPanel_Comment;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea_MovieDetail;
    private javax.swing.JTextField jTextField_input;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
