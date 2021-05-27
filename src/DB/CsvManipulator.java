package DB;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import controllers.*;

import static java.lang.Integer.parseInt;

public class CsvManipulator {

    public static void write(String actionName, String threadName) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append(actionName);
        sb.append(',');

        String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new java.util.Date());
        sb.append(timeStamp);

        sb.append(',');
        sb.append(threadName);

        try(FileWriter csvWriter = new FileWriter(new File("src/data/audit.csv"), Boolean.TRUE)){
            csvWriter.write(sb.toString());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
/*
    public static void read(String file) {
        String fileName = "src/data/" + file + ".csv";
        try {
            File driversCsv = new File(fileName);
            Scanner myReader = new Scanner(driversCsv);
            String data;
            int i = 0;
            while (myReader.hasNextLine()) {
                //get line from  file
                data = myReader.nextLine();
                i += 1;
                if(i > 1) {
                    //split data
                    List<String> line = Arrays.asList(data.split(","));
                    switch (file){
                        case "drivers":
                            if(line.size() == 5){
                                DriversController.createDriver(line.get(0), line.get(1), line.get(2), line.get(3), line.get(4));
                            }
                            else {
                                DriversController.createDriver(line.get(0), line.get(1), line.get(2), line.get(3), parseInt(line.get(4)), line.get(5), line.get(6), Boolean.parseBoolean(line.get(7)));
                            }
                            break;
                        case "orders":
                            List<String> orderedItems = Arrays.asList(line.get(3).split(","));
                            OrdersController.newOrder(line.get(0),line.get(1), line.get(2), orderedItems);
                            break;
                        case "restaurants":
                            RestaurantsController.createRestaurant(line.get(0), line.get(1));
                            break;
                        case "users":
                            if(line.size() == 3){
                                UsersController.createUser(line.get(0), line.get(1),line.get(2));
                            }
                            else {
                                UsersController.createUser(line.get(0), line.get(1), line.get(2), parseInt(line.get(3)), line.get(4));
                            }
                            break;
                        default:
                            System.out.println("Not valid");
                            break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }*/
}
