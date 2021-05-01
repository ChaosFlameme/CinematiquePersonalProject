/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Map;
import javax.swing.JPasswordField;
import main.IHM;
import txt.TxtFile;

public class LoginDialog extends JDialog
        implements ActionListener,
        PropertyChangeListener {

    private Map<String, String> userList;

    private JOptionPane optionPane;

    private String typedUsername = null;
    private String typedPassword = null;
    private JTextField textField_username;
    private JTextField textField_password;

    private final String btnString_login = "Login";
    private final String btnString_cancel = "Cancel";
    private final String btnString_register = "Register";

    private Map<String, String> map;
    //private String currentUser = null;
    File file;
    IHM ihm;

    public LoginDialog(Frame frame, File fl, IHM im) {
        super(frame, true);
        //ls = parent;

        file = fl;
        ihm = im;

        map = TxtFile.getUserMap(fl);

        setTitle("Login");
        this.setLocation(500, 200);//Can be improved to associate with main window

        textField_username = new JTextField(10);
        textField_password = new JPasswordField(10);

        String strUsername = "Username: ";
        String strPassword = "Password: ";
        Object[] array = {strUsername, textField_username, strPassword, textField_password};

        Object[] options = {btnString_register, btnString_login, btnString_cancel};

        optionPane = new JOptionPane(
                array,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.YES_NO_CANCEL_OPTION,
                null,
                options);

        setContentPane(optionPane);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                optionPane.setValue(JOptionPane.CLOSED_OPTION);
            }
        });

        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                textField_username.requestFocusInWindow();

            }
        });

        optionPane.addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString_login);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();

        if (isVisible()
                && (evt.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop)
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }

            //Reset JOptionPane's value.
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

            //Login Part
            //Check username correspond to password as you can see
            if (btnString_login.equals(value)) {
                String tname = textField_username.getText();
                String tpass = textField_password.getText();

                if (tname.isEmpty() || tpass.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Finish it will'ya?", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (tpass.equals(map.get(tname))) {
                    //Add Login process
                    ihm.setCurrentUser(tname);
                    System.out.println("Login sucess");
                } else {
                    JOptionPane.showMessageDialog(this, "Username not found or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                clearAndHide();

            } //Register one:Over
            else if (btnString_register.equals(value)) {
               
                typedUsername = textField_username.getText();
                typedPassword = textField_password.getText();
                boolean j = false;
                if (typedUsername.isEmpty() || typedPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Finish it will'ya?", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (map.containsKey(typedUsername)) {
                    JOptionPane.showMessageDialog(this, "Username already existed!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                map.put(typedUsername, typedPassword);
                try {
                    TxtFile.writeFile(map, file);
                } catch (Exception ee) {
                    System.out.println("File write failed");
                    ee.printStackTrace();
                }
                JOptionPane.showMessageDialog(this, "Registion sucess!", "Sucess", JOptionPane.INFORMATION_MESSAGE);

            } else {
                clearAndHide();
            }

        }
    }

    public void clearAndHide() {
        typedUsername = null;
        typedPassword = null;
        textField_password.setText(null);
        textField_username.setText(null);
        setVisible(false);
    }

    public String getTypedUsername() {
        return typedUsername;
    }

    public String getTypedPassword() {
        return typedPassword;
    }

    public Map<String, String> getMap() {
        return map;
    }

}
