import controllers.UsersController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private Container c;
    private JLabel title;
    //Username
    private JLabel username;
    private JTextField tusername;
    //Email
    private JLabel email;
    private JTextField temail;
    //Password
    private JLabel password;
    private JTextField tpassword;
    //Address
    private JLabel address;
    private JTextArea taddress;
    //Age
    private JLabel age;
    private JTextField tage;

    private JCheckBox terms;
    private JButton submit;
    private JLabel res;

    public LoginFrame(){
        setTitle("Registration form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        c = getContentPane();
        c.setLayout(null);

        //TITLU
        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(330, 30);
        c.add(title);

        //USERNAME
        username = new JLabel("Username");
        username.setFont(new Font("Arial", Font.PLAIN, 15));
        username.setSize(100, 20);
        username.setLocation(270, 120);
        c.add(username);

        tusername = new JTextField();
        tusername.setFont(new Font("Arial", Font.PLAIN, 10));
        tusername.setSize(190, 20);
        tusername.setLocation(370, 120);
        c.add(tusername);

        //EMAIL
        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 15));
        email.setSize(100, 20);
        email.setLocation(270, 150);
        c.add(email);

        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 10));
        temail.setSize(190, 20);
        temail.setLocation(370, 150);
        c.add(temail);

        //PASSWORD
        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 15));
        password.setSize(100, 20);
        password.setLocation(270, 180);
        c.add(password);

        tpassword = new JTextField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 10));
        tpassword.setSize(190, 20);
        tpassword.setLocation(370, 180);
        c.add(tpassword);

        //ADDRESS
        address = new JLabel("Address");
        address.setFont(new Font("Arial", Font.PLAIN, 15));
        address.setSize(100, 20);
        address.setLocation(270, 240);
        c.add(address);

        taddress = new JTextArea();
        taddress.setFont(new Font("Arial", Font.PLAIN, 10));
        taddress.setSize(190, 75);
        taddress.setLocation(370, 210);
        taddress.setLineWrap(true);
        c.add(taddress);

        //AGE
        age = new JLabel("Age");
        age.setFont(new Font("Arial", Font.PLAIN, 15));
        age.setSize(100, 20);
        age.setLocation(270, 300);
        c.add(age);

        tage = new JTextField();
        tage.setFont(new Font("Arial", Font.PLAIN, 10));
        tage.setSize(190, 20);
        tage.setLocation(370, 300);
        c.add(tage);

        //Terms and conditions
        terms = new JCheckBox("Accept Terms And Conditions.");
        terms.setFont(new Font("Arial", Font.PLAIN, 15));
        terms.setSize(250, 20);
        terms.setLocation(350, 400);
        c.add(terms);

        //Submit
        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(100, 20);
        submit.setLocation(420, 450);
        submit.addActionListener(this);
        c.add(submit);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(300, 500);
        c.add(res);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(terms.isSelected()){
            try {
                UsersController.Create(tusername.getText(), temail.getText(), tpassword.getText(), taddress.getText(), Integer.parseInt(tage.getText()));
                res.setText("Thank you for joining us!");
            } catch (Exception exception) {
                res.setText("There has been an error, please try again");
            }
        }
        else {
            res.setText("Please accept the"
                    + " terms & conditions...");
        }
    }

}

class RegistrationClass {

    public static void main(String[] args) throws Exception
    {
        LoginFrame f = new LoginFrame();

    }
}