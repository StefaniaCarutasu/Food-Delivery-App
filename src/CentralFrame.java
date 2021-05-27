import controllers.DriversController;
import controllers.RestaurantsController;
import controllers.UsersController;
import drivers.Driver;
import restaurants.MenuItem;
import restaurants.Restaurant;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class CentralFrame extends JFrame {
    private Container c;
    private static JButton nextFrame;

    public CentralFrame() throws Exception {
        setTitle("Registration form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        nextFrame = new JButton("New User");
        nextFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoginFrame f = new LoginFrame();
                } catch (FontFormatException fontFormatException) {
                    fontFormatException.printStackTrace();
                }
            }
        });
        nextFrame.setSize(100, 20);
        nextFrame.setBackground(new Color(226, 190, 235));
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(226, 190, 235));
        c.add(showRestaurantsUsersDrivers(), BorderLayout.CENTER);
        setVisible(true);
    }


    public static JPanel popUpPanelRestaurant(JPanel parentPanel){
        JPanel panouRestaurant = new JPanel();

        JLabel eticheta = new JLabel("Nume restaurant");
        JButton buton = new JButton("Afiseaza restaurant");
        JTextField text = new JTextField(20);


        panouRestaurant.add(eticheta);
        panouRestaurant.add(text);
        panouRestaurant.add(buton);
        panouRestaurant.add(nextFrame, BorderLayout.EAST);
        buton.setBackground(new Color(226, 190, 235));
        buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeRes = text.getText();
                String restaurant = "";
                JPanel panouProduse = new JPanel();
                Vector<String> vMancare = new Vector<>();
                Vector<String> vBauturi = new Vector<>();

                try {
                    Restaurant res = RestaurantsController.getRestaurantByName(numeRes);
                    List<MenuItem> produseMancare = RestaurantsController.ReadFood(res.getID());
                    List<MenuItem> produseBautura = RestaurantsController.ReadDrinks(res.getID());


                    //fac un panel pentru produsele de mancare pe care le are restaurantul afisat
                    vMancare.add("<html>Mancare<br><br>");
                    int i = 1;
                    for(MenuItem m: produseMancare){

                        String elem = "<html>" +  String.valueOf(i) + ") " + m.getName() + "......................... Price: " + String.valueOf(m.getPrice())  + "<br/><br/>" ;
                        vMancare.add(elem);
                        i += 1;
                    }

                    //fac un panel pentru produsele de mancare pe care le are restaurantul afisat
                    vBauturi.add("<html>Bauturi<br><br>");
                    i = 1;
                    for(MenuItem m: produseBautura){

                        String elem = "<html>" +  String.valueOf(i) + ") " + m.getName() + "......................... Price: " + String.valueOf(m.getPrice())  + "<br/><br/>" ;
                        vMancare.add(elem);
                        i += 1;
                    }

                    restaurant += "<html>Name: " + res.getName() + "<br/>Email: " + res.getEmail() + "<br/>Phone: " + res.getPhoneNumber() + "<br/>Address: " + res.getAddress() ;

                    JList listaMancare = new JList(vMancare);
                    JList listaBauturi = new JList(vBauturi);
                    JPanel panouPrincipal = new JPanel();
                    panouPrincipal.setLayout(new GridLayout(2, 1));

                    JLabel label = new JLabel();
                    label.setText(restaurant);
                    panouPrincipal.add(label, BorderLayout.NORTH);

                    JScrollPane spMancare = new JScrollPane(listaMancare);
                    JScrollPane spBauturi = new JScrollPane(listaBauturi);
                    panouProduse.setLayout(new GridLayout(1, 2));
                    panouProduse.add(spMancare);
                    panouProduse.add(spBauturi);
                    panouPrincipal.add(panouProduse, BorderLayout.CENTER);

                    JOptionPane showOneRestaurant = new JOptionPane(restaurant);
                    showOneRestaurant.add(panouPrincipal);
                    parentPanel.add(showOneRestaurant);
                    Popup po;
                    PopupFactory pf = new PopupFactory();
                    JOptionPane.showMessageDialog(parentPanel, panouPrincipal);
                    panouPrincipal.setBackground(new Color(226, 190, 235));

                } catch (SQLException throwables) {
                    restaurant = "Nu exista acest restaurant. Verificati daca ati introdus numele corect!";
                    JPanel nuAGasit = new JPanel();
                    JLabel res = new JLabel();
                    res.setText(restaurant);
                    nuAGasit.add(res);

                    JOptionPane.showMessageDialog(parentPanel, nuAGasit);
                }
            }
        });

        return panouRestaurant;
    }

    public static JPanel allRestaurantsPanel() throws SQLException {

        //Panoul principal
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());

        JPanel panouRestaurante = popUpPanelRestaurant(parentPanel);

        parentPanel.add(panouRestaurante, BorderLayout.NORTH);

        //Panou afisare restaurante
        JPanel panouAfisare = new JPanel();

        List<Restaurant> restaurantList = RestaurantsController.Read();
        Vector<String> elemente = new Vector<>();
        elemente.add("<html>RESTAURANTE<br><br>");

        elemente.add("<html><table>");
        int i = 1;

        for(Restaurant r: restaurantList){

            String elem = "<html><tr><td>" + String.valueOf(i) + ") Name: " + r.getName() + "<br/>Email: " + r.getEmail() + "<br/>Phone: " + r.getPhoneNumber() + "<br/>Address: " + r.getAddress() + "<br/><br/></td></tr>" ;
            elemente.add(elem);
            i += 1;
        }
        elemente.add("<html></table>");
       // elemente.add("</html>");

        JList lista = new JList(elemente);

        JScrollPane sp = new JScrollPane(lista);
        panouAfisare.add(sp);
        parentPanel.add(sp, BorderLayout.CENTER);
        return parentPanel;


    }

    public static JPanel allUsersPanel() throws Exception {

        //Panoul principal
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());

        //Panou afisare restaurante
        JPanel panouAfisare = new JPanel();

        List<User> restaurantList = UsersController.Read();
        Vector<String> elemente = new Vector<>();
        elemente.add("<html>USERI<br><br>");
        int i = 1;
        for(User u: restaurantList){

            String elem = "<html>" +  String.valueOf(i) + ") Username: " + u.getUsername() + "<br/>Email: " + u.getEmail() + "<br/>Address: " + u.getAddress() + "<br/><br/>" ;
            elemente.add(elem);
            i += 1;
        }

        JList lista = new JList(elemente);

        JScrollPane sp = new JScrollPane(lista);
        panouAfisare.add(sp);
        parentPanel.add(sp, BorderLayout.CENTER);
        return parentPanel;

    }

    public static JPanel allDriversPanel() throws Exception {

        //Panoul principal
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());

        //Panou afisare restaurante
        JPanel panouAfisare = new JPanel();

        List<Driver> restaurantList = DriversController.Read();
        Vector<String> elemente = new Vector<>();
        elemente.add("<html>DRIVERI<br><br>");
        int i = 1;
        for(Driver d: restaurantList){

            String elem = "<html>" +  String.valueOf(i) + ") Username: " + d.getUsername() + "<br/>Email: " + d.getEmail() + "<br/>Vehicle type: " + d.getVehicleType() + "<br/>Vechicle number: " + d.getVehicleNumber() + "<br/><br/>" ;
            elemente.add(elem);
            i += 1;
        }

        JList lista = new JList(elemente);

        JScrollPane sp = new JScrollPane(lista);
        panouAfisare.add(sp);
        parentPanel.add(sp, BorderLayout.CENTER);
        return parentPanel;


    }

    static JFrame showRestaurantsUsersDrivers() throws Exception {

        JFrame frame = new JFrame("Glovo 2.0");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Restaurante", null, allRestaurantsPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Useri", null, allUsersPanel());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Driveri", null, allDriversPanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        tabbedPane.setBackground(new Color(226, 190, 235));


        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setSize(900, 600);
        frame.setBackground(new Color(226, 190, 235));
        frame.setVisible(true);

        return frame;
    }


    public static void main(String[] args) throws Exception {
        CentralFrame cf = new CentralFrame();

    }
}

