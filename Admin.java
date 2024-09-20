import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;

/**
 * This class is a template for admin objects.
 * This will be used later in the project.
 * It implements the person interface.
 * @author Daniel Avitia
 */
public class Admin implements Person{
    /** This list stores all the cars bought during runtime*/
    private LinkedList<Car> carList = new LinkedList<>();

    /** keeps track of admin object*/
    private static Admin firstInstance = null;

    /**
     * This is a no arg constructor for the Admin class.
     */
    public Admin(){
        super();
    }

    /**
     * Retrieves the single instance of the Admin class, creating it if it doesn't exist.
     * This method ensures that only one instance of Admin is created throughout the program's execution.
     * 
     * @return The single instance of the Admin class.
     */
    public static Admin getInstanceOf() {
        if(firstInstance == null){
            firstInstance = new Admin();
        }
        return firstInstance;
    }

    /**
     * Adds a new car entry to the CSV file with the provided details.
     * 
     * @param carType      The type of the car.
     * @param model        The model of the car.
     * @param condition    The condition of the car.
     * @param color        The color of the car.
     * @param capacity     The capacity of the car.
     * @param fuel         The fuel type of the car.
     * @param transmission The transmission type of the car.
     * @param VIN          The VIN (Vehicle Identification Number) of the car.
     * @param price        The price of the car.
     * @param numAvailable The number of available cars.
     * @param year         The manufacturing year of the car.
     * @param hasTurbo     Whether the car has turbo or not.
     */
    public void addCar(String carType, String model, String condition, String color, String capacity,
                       String fuel, String transmission, String VIN, String price, String numAvailable, String year, String hasTurbo){
        String csvFile = "car_data.csv";
        String line = "";
        String[] data = new String[0];

        //automatically assigning id
        String lastLine = getLastLine(csvFile);
        String[] elements = lastLine.split(",");
        int index = RunShop.setCarTableMap("ID");
        String id = elements[index];
        int newID = Integer.parseInt(id) + 1; //add 1 to last ID


        //this here will read the header file to know where to index data
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            if ((line = br.readLine()) != null) {
                data = line.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(PrintWriter writer = new PrintWriter(new FileWriter(csvFile, true))){
            //Append the new car to the csv file
            for(int i = 0; i < data.length; i ++){
                String header = data[i];
                switch(header){
                    case "Capacity":
                        writer.print(capacity + ",");
                        break;
                    case "Car Type":
                        writer.print(carType + ",");
                        break;
                    case "Cars Available":
                        writer.print(numAvailable + ",");
                        break;
                    case "Condition":
                        writer.print(condition + ",");
                        break;
                    case "Color":
                        writer.print(color + ",");
                        break;
                    case "ID":
                        writer.print(newID + ",");
                        break;
                    case "Year":
                        writer.print(year + ",");
                        break;
                    case "Price":
                        writer.print(price + ",");
                        break;
                    case "Transmission":
                        writer.print(transmission + ",");
                        break;
                    case "VIN":
                        writer.print(VIN + ",");
                        break;
                    case "Fuel Type":
                        writer.print(fuel + ",");
                        break;
                    case "Model":
                        writer.print(model + ",");
                        break;
                    case "hasTurbo":
                        writer.print(hasTurbo);
                        break;
                    default:
                        System.out.println("Error adding car");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds a new user entry to the CSV file with the provided details.
     * 
     * @param firstName   The first name of the user.
     * @param lastName    The last name of the user.
     * @param carsOwned   The cars owned by the user.
     * @param isMember    Whether the user is a MinerCars member (true/false).
     * @param budget      The budget of the user.
     * @param userName    The username of the user.
     * @param password    The password of the user.
     */
    public void addUser(String firstName, String lastName, String carsOwned,
     String isMember, String budget, String userName, String password){//all strings for purpose of creating the string to add to the csv
        String csvFile = "user_data.csv";
        String line = "";
        String[] data = new String[0];

        //automatically assigning id
        String lastLine = getLastLine(csvFile);
        String[] elements = lastLine.split(",");
        int index = RunShop.setUserTableMap("ID");
        String id = elements[index];
        int newID = Integer.parseInt(id) + 1;

        //this here will read the header file to know where to index data
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            if ((line = br.readLine()) != null) {
                data = line.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(PrintWriter writer = new PrintWriter(new FileWriter("user_data.csv", true))){
            //Append the new car to the csv file
            for(int i = 0; i < data.length; i ++){
                String header = data[i];
                switch(header){
                    case "First Name":
                        writer.print(firstName + ",");
                        break;
                    case "Last Name":
                        writer.print(lastName + ",");
                        break;
                    case "MinerCars Membership":
                        writer.print(String.valueOf(isMember).toUpperCase()); //last column / no comma
                        break;
                    case "Cars Purchased":
                        writer.print(carsOwned + ",");
                        break;
                    case "ID":
                        writer.print(newID + ",");
                        break;
                    case "Money Available":
                        writer.print(budget + ",");
                        break;
                    case "Username":
                        writer.print(userName + ",");
                        break;
                    case "Password":
                        writer.print(password + ",");
                        break;
                    default:
                        System.out.println("Error adding user");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Removes a car entry from the CSV file based on the provided ID.
     * 
     * @param id The ID of the car to be removed.
     */
    public void removeCar(String id){
        int index = RunShop.setCarTableMap("ID");
        
        try {
            //open the CSV file
            BufferedReader reader = new BufferedReader(new FileReader("car_data.csv"));
            List<String> lines = new ArrayList<>(); //creating an array that will keep all other lines
            String line;
            String header = reader.readLine();
            String removedLine = "";

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                //if the ID matches, skip adding the line to the list
                if (!parts[index].trim().equals(id)) {
                    lines.add(line);
                }else{
                    removedLine = line;
                }
            }

            //the line that was removed will then be returned
            String[] parts = removedLine.split(",");
            reader.close();
                
            //write the modified data back to the CSV file
            BufferedWriter writer = new BufferedWriter(new FileWriter("car_data.csv"));
            writer.write(header + "\n");
            for (String newLine : lines) {
                writer.write(newLine + "\n");
            }
            writer.close();

            System.out.println("Line removed was:");
            System.out.println(parts);
            
            System.out.println("Car with ID " + id + " removed successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Retrieves the last line of a text file.
     * Used to know which id to set when addig a new car / user.
     * 
     * @param filepath The path of the text file.
     * @return The last line of the text file.
     */
    public String getLastLine(String filepath){
        String lastLine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            // Read lines until reaching the end of the file
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }

    /**
     * Adds a car to the list of cars that have been sold.
     * 
     * @param car The car to add to the list.
     */
    public void addToList(Car car){
        carList.add(car);
    }

    /**
     * Retrieves the list of cars that have been sold.
     * 
     * @return The list of cars.
     */
    public LinkedList<Car> getCarList(){
        return this.carList;
    }

    /**
     * Calculates the revenue generated by a car with the specified ID.
     * 
     * @param id The ID of the car.
     * @return The revenue generated by the car with the specified ID.
     */
    public float revenueByID(String id, LinkedList<Car> cars){
        float revenue = 0;
        int numSold = 0;
        //LinkedList<Car> cars = getCarList(); //this list contains all the cars that have been sold
        for (Car currentCar : cars){
            if(currentCar.getId().equals(id)){
                numSold++; //sold +1 of type car
                revenue += currentCar.getRevenue();
            }
        }
        System.out.println("Number Sold: " + numSold);
        return revenue;
    }

    /**
     * Calculates the total revenue generated by cars of the specified type.
     * 
     * @param carType The type of cars.
     * @return The total revenue generated by cars of the specified type.
     */
    public float revenueByCarType(String carType, LinkedList<Car> cars){
        int numSold = 0;
        float revenue = 0; //will add to revenue for each car found
        for (Car currentCar : cars){
            if(currentCar.getType().equals(carType)){
                //add the revenue for the car of this type
                numSold++;
                revenue += currentCar.getRevenue();
            }
        }
        System.out.println("Number Sold " + numSold);
        return revenue;
    }

    //***** INTERFACE METHODS *****//

    /**
     * Set id
     * @param id The new ID value to be set.
     */
    @Override
    public void setID(String id) {

    }

    /**
     * Set first name
     * @param firstName The new first name value to be set.
     */
    @Override
    public void setFirstName(String firstName) {

    }

    /**
     * Set last name
     * @param lastName The new last name value to be set.
     */
    @Override
    public void setLastName(String lastName) {

    }

    /**
     * Get id
     * @return id
     */
    @Override
    public String getID() {
        return null;
    }

    /**
     * Get first name
     * @return first name
     */
    @Override
    public String getFirstName() {
        return null;
    }

    /**
     * Get last name
     * @return last name
     */
    @Override
    public String getLastName() {
        return null;
    }
}