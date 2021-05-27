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

public class FoodDeliveryGUI extends JFrame {
    private JPanel restaurantsPanel;

    public FoodDeliveryGUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(restaurantsPanel);
        this.pack();
    }


    public static JPanel popUpPanelRestaurant(JPanel parentPanel){
        JPanel panouRestaurant = new JPanel();

        JLabel eticheta = new JLabel("Nume restaurant");
        JButton buton = new JButton("Afiseaza restaurant");
        JTextField text = new JTextField(20);

        panouRestaurant.add(eticheta);
        panouRestaurant.add(text);
        panouRestaurant.add(buton);

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

                    panouPrincipal.add(panouProduse, BorderLayout.PAGE_END);

                    JOptionPane showOneRestaurant = new JOptionPane(restaurant);
                    showOneRestaurant.add(panouPrincipal);
                    parentPanel.add(showOneRestaurant);

                    JOptionPane.showMessageDialog(parentPanel, panouPrincipal);

                } catch (SQLException throwables) {
                    restaurant = "Nu exista acest restaurant. Verificati daca ati introdus numele corect!";
                    JOptionPane showOneRestaurant = new JOptionPane(restaurant);
                    JOptionPane.showMessageDialog(parentPanel, showOneRestaurant);
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
        int i = 1;
        for(Restaurant r: restaurantList){

            String elem = "<html>" +  String.valueOf(i) + ") Name: " + r.getName() + "<br/>Email: " + r.getEmail() + "<br/>Phone: " + r.getPhoneNumber() + "<br/>Address: " + r.getAddress() + "<br/><br/>" ;
            elemente.add(elem);
            i += 1;
        }

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

    static void showRestaurantsUsersDrivers() throws Exception {

        JFrame frame = new JFrame("Glovo dupa o luna in Vaslui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Restaurante", null, allRestaurantsPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Useri", null, allUsersPanel());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Driveri", null, allDriversPanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);



        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }



    public static void main(String[] args) throws Exception {
        showRestaurantsUsersDrivers();
    }
}
