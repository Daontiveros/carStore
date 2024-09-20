import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;


/**
 * RunShop contains the main method that runs the program.
 * This is where the main operations of the program happen.
 * @author Team 21
 */
public class RunShop{

    /** This variable stores the log object / Singleton Design Pattern is used here */
    private static final Log logObj = Log.getInstanceOf();
    /** stores the user object */
    private static User user;
    /** stores a car object */
    private static Car userCar;
    /** a single admin object*/
    private static Admin admin = Admin.getInstanceOf();
    /** discount rate*/
    private static final float DISCOUNT_RATE = 0.10F;
    /** state tax rate*/
    private static final float STATE_TAX = 0.0625F;

    /**
     * This method updates hasTurbo with false
     * on the last column
     * @param fileName name of the file being updated
     */
    public static void updateHasTurbo(String fileName){
        // temp file to store the updated version of the old file
        String tempFile = "temp.csv";
        File oldFile = new File(fileName);
        File newFile = new File(tempFile);

        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw); //used to write
            
            //String cars = "car_data.csv"; 
            int index = setCarTableMap("hasTurbo");
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
                // String that reads the lines
                String line;
                //The loop will access every line in the csv file
                while((line = br.readLine()) != null){
                    //split the line into an array
                    String[] carLine = line.split(",");
                    // loop through carLine so the current line
                    for(int i = 0; i < carLine.length; i++){
                        // at the end just write the last data without the comma
                        if(i == carLine.length - 1){
                            pw.append(carLine[i]);
                        }else{
                            // write the data with a comma after
                            pw.append(carLine[i]);
                            pw.append(",");
                        }
                    }
                    // at the end in hasTurbo check if hasTurbo has a missing yes or no
                    // append a false
                    if(carLine.length - 1 != index){
                        pw.append(",false");
                    }
                    pw.append("\n");
                }
            }catch(IOException e){
                e.printStackTrace();
            }

            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(fileName);
            newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    /**
     * This method deletes a car by rewritting the file
     * and omitting that car line
     * @param fileName name of the file being updated
     * @param carID car to be deleted
     */
    public static void deleteCarTurbo(String fileName, String carID){
        // temp file to store the updated version of the old file
        String tempFile = "temp.csv";
        File oldFile = new File(fileName);
        File newFile = new File(tempFile);

        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw); //used to write
            
            //String cars = "car_data.csv"; 
            int index = setCarTableMap("ID");
            try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
                // String that reads the lines
                String line;
                //The loop will access every line in the csv file
                while((line = br.readLine()) != null){
                    //split the line into an array
                    String[] carLine = line.split(",");
                    // loop through carLine so the current line
                    if(!(carLine[index].equals(carID))){
                        for(int i = 0; i < carLine.length; i++){
                            // at the end just write the last data without the comma
                            if(i == carLine.length - 1){
                                pw.append(carLine[i]);
                            }else{
                                // write the data with a comma after
                                pw.append(carLine[i]);
                                pw.append(",");
                            }
                        }
                        pw.append("\n");
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }

            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(fileName);
            newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    /**
     * This method calls the function to delete a car
     * by passing it the car id of the car that
     * will be deleted
     * @param carId id of the car to be deleted
     */
    public static void updateDeleteCar(String carId){
        //update user data in CSV file
        String fileName = "car_data.csv";
        String carToRemove = carId;
        deleteCarTurbo(fileName, carToRemove);
    }

    /**
     * This method updates hasTurbo column to false
     * if there is no yes or no
     */
    public static void updateHasTurbo(){
        //update user data in CSV file
        String fileName = "car_data.csv";
        updateHasTurbo(fileName);
    }

    /**
     * This method returns the index of the column that you need
     * from the csv file.
     * @param tableTop string that contains the top of the csv file column
     * @return the index of the column of the csv files
     */
    public static int setCarTableMap(String tableTop){
        String cars = "car_data.csv"; 
        int indexFile = -1;
        try(BufferedReader br = new BufferedReader(new FileReader(cars))){
            String firstLine = br.readLine();
            String[] carFirstLine = firstLine.split(",");
            for(int i = 0; i < carFirstLine.length; i++){
                if(carFirstLine[i].equals(tableTop)){
                    indexFile = i;
                    return indexFile;
                }
            }    
        }catch(IOException e){
            e.printStackTrace();
        }
        return indexFile;
    }

    public static int setUserTableMap(String tableTop){
        String users = "user_data.csv"; 
        int indexFile = -1;
        try(BufferedReader br = new BufferedReader(new FileReader(users))){
            String firstLine = br.readLine();
            String[] userFirstLine = firstLine.split(",");
            for(int i = 0; i < userFirstLine.length; i++){
                if(userFirstLine[i].equals(tableTop)){
                    indexFile = i;
                    return indexFile;
                }
            }    
        }catch(IOException e){
            e.printStackTrace();
        }
        return indexFile;
    }


    /** This method filters the cars and stores the user's actions into a log class.
     * It scans the user's input and stores it into userFilteringOption.
     * Depending on the user's input, it filters the cars or breaks the loop. 
     * @param logObj the log object where user's actions are stored
     */

    public static void filterCars(Log logObj){
        //Declaring for use as csv in switch case
        String cars = "car_data.csv"; 
        // A scanner
        Scanner filterCarsScanner = new Scanner(System.in);
        // String to store user's input
        String userFilteringOption;

		System.out.print("\n1)New\n2)Used\n3)Go back\n\n");
        // While user's input is not 3
        while(true){
            // Scan a string
            userFilteringOption = filterCarsScanner.nextLine();
            // If the string is 1,2,3
            if(userFilteringOption.equalsIgnoreCase("1")){
                int index = setCarTableMap("Condition");
                try(BufferedReader br = new BufferedReader(new FileReader(cars))){
                    // String that reads the lines
                    String line;
                    //The loop will access every line in the csv file
                    while((line = br.readLine()) != null){
                        //split the line into a list (or array??♪)
                        String[] carLine = line.split(",");
                        //This if statement filters for new cars only
                        if(carLine[index].equals("New")){
                            //for every string in the line (data), it will print the string(data)
                            for(String data : carLine){
                                System.out.print(data + " ");
                            }
                            System.out.print("\n");
                        }
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
                // Store user's action on the log
                logObj.filterCarActions(userFilteringOption);
            }else if(userFilteringOption.equalsIgnoreCase("2")){
                int index = setCarTableMap("Condition");
                try(BufferedReader br = new BufferedReader(new FileReader(cars))){
                    String line;
                    //The loop will access every line in the csv file
                    while((line = br.readLine()) != null){
                        //split the line into a list (or array??♪)
                        String[] carLine = line.split(",");
                        //This if statement filters for new cars only
                        if(carLine[index].equals("Used")){
                            //for every string in the line (data), it will print the string(data)
                            for(String data : carLine){
                                System.out.print(data + " ");
                            }
                            System.out.print("\n");
                        }
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
                // Store user's action on the log
                logObj.filterCarActions(userFilteringOption);
            }else if(userFilteringOption.equalsIgnoreCase("3")){
                // Store user's action on the log
                logObj.filterCarActions(userFilteringOption);
                System.out.println("Go Back");
                // Break the loop
                break;
            }else{
                System.out.print("\nWrong input try again 2: \n");
            }
            System.out.print("\n1)New\n2)Used\n3)Go back\n\n");
		}
    }
    
    /**
     * This method calls different methods depending on the user's input.
     * It switches between 1,2,3, or 4. The user's input.
     * @param userInput the user's input (1-4)
     * @param logObj the log class where user's actions are stored
     */
    public static void actionUser(String userInput, Log logObj){
        // Turns the user's input into a string
        //String userLogAction = Integer.toString(userInput);
        // Switches the user's input
		switch(userInput){
		    case "1":
		        System.out.println("Display all Cars.");
                // Calls a method that displays all cars
                displayCars();
                // Store user's action on the log
		        logObj.storeUserActions(userInput);
		        break;
		    case "2":
                // Calls a method that filters cars
		        filterCars(logObj);
		        break;
		    case "3":
		        System.out.println("Purchase a car.");
                //user can buy a car
                buyCar(user);
                // Store user's action on the log
		        logObj.storeUserActions(userInput);
		        break;
		    case "4":
		        System.out.println("View Tickets.");
                //user can view a ticket with all their purchases
                viewAllTickets(user);
                // Store user's action on the log
		        logObj.storeUserActions(userInput);
		        break;
            case "5":
                System.out.println("Return a Car");
                returnCar();
                logObj.storeUserActions(userInput);
		        break;
		} //switch
    } //actionUser
    
    /**
     * Starts the interface and handles user authentication.
     * @return A line from the user_data.csv file containing the user's information if authentication is successful; otherwise, null.
     */
    public static User start(){
        //start the interface with this function
        //D: my work starts here ♪

        //Set csv files for future use
        String userData = "user_data.csv";
        boolean firstLine = true;

        //Start by asking username and password
        Scanner s = new Scanner(System.in);//Scanner for user

        //See if the username matches any in user_data.csv
        try (BufferedReader br = new BufferedReader(new FileReader(userData))) {
            String line;
            boolean exists = false;
            User person = null; //declare User
            while (!exists) {
                //Mark the current position in the reader
                br.mark(4096); //default buffer size of 4096
                
                System.out.print("Username: ");
                String usernameIn = s.nextLine();
                System.out.print("\nPassword: ");
                String passwordIn = s.nextLine();
                System.out.println();
                
                while ((line = br.readLine()) != null) {
                    //skip the header
                    if(firstLine){
                        firstLine = false;
                        continue;
                    }
                    
                    String[] data = line.split(",");
                    //give values to the user
                    person = new User(data[setUserTableMap("ID")], data[setUserTableMap("First Name")], data[setUserTableMap("Last Name")],
                        Float.parseFloat(data[setUserTableMap("Money Available")]), Integer.parseInt(data[setUserTableMap("Cars Purchased")]),
                        Boolean.parseBoolean(data[setUserTableMap("MinerCars Membership")]), data[setUserTableMap("Username")], data[setUserTableMap("Password")]);
                    if (person.getUserName().equals(usernameIn)) {
                        if (person.getPassword().equals(passwordIn)) {
                            System.out.print("Welcome! ");
                            System.out.println(usernameIn);
                            System.out.println();
                            exists = true;
                        }
                        break;
                    }
                }
                if (!exists) {
                    System.out.println("Incorrect username or password");
                    firstLine = true;
                    br.reset(); //reset the mark
                }
            }
            //set the remaining information for the person
            return person; //return the user object
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        
        //D: work ends here
    }

    /**
     * Displays all cars and their data stored in the car_data.csv file.
     */
    public static void displayCars(){
        //set csv file to use
        String cars = "car_data.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(cars))){
            String line;

            //The loop will access every line in the csv file
            while((line = br.readLine()) != null){
                String[] carLine = line.split(",");//split the line into a list (or array??♪)
                
                //for every string in the line (data), it will print the string(data)
                for(String data : carLine){
                    System.out.print(data + " ");
                }
                System.out.print("\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * This method is used to initialize the fields in the car object.
     * It does not return anything and only helps create the car object.
     * @param data this array holds the values that will be stored in the car's fields.
     * @param car this is the car object that will be initialized.
     */
    public static void setCarData(String[] data, Car car){
        int index = setCarTableMap("ID");
        car.setID(data[index]);

        index = setCarTableMap("Car Type");
        car.setCarType(data[index]);

        index = setCarTableMap("Model");
        car.setModel(data[index]);

        index = setCarTableMap("Year");
        car.setYear(data[index]);

        index = setCarTableMap("Color");
        car.setColor(data[index]);

        index = setCarTableMap("Cars Available");
        car.setNumAvailable(Integer.parseInt(data[index]));

        index = setCarTableMap("Price");
        car.setPrice(Float.parseFloat(data[index]));
    }

    public static boolean doesCarExist(int carID){
        String cars = "car_data.csv"; 
        int index = setCarTableMap("ID");
        boolean carExist = false;
        try(BufferedReader br = new BufferedReader(new FileReader(cars))){
            // String that reads the lines
            String line;
            //The loop will access every line in the csv file
            while((line = br.readLine()) != null){
                //split the line into a list (or array??♪)
                String[] carLine = line.split(",");
                //This if statement filters for new cars only
                if(carLine[index].equals(Integer.toString(carID))){
                    carExist = true;
                    return carExist;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return carExist;
    }

    /**
     * This method completes the 3rd option of the menu for buying a car.
     * It asks the user to enter the id of the car they want to buy and checks
     * if they can buy it.
     * @param user this is the user object used in this method
     * @return an integer that is the year of the model
     */
    public static void buyCar(User user){
        Scanner s1 = new Scanner(System.in);
        String userInput;
        boolean done = false;
        int carID;
        boolean carIdExists;

        while(!done){
            System.out.print("Enter car ID: ");
            userInput = s1.nextLine();

            if(!(userInput.length() >= 1 && userInput.length() <= 3)){
                System.out.println("Enter a valid ID!");
                continue;
            }

            try {
                carID = Integer.parseInt(userInput);
                carIdExists = doesCarExist(carID);
                if(!carIdExists){
                    System.out.println("Car ID doesn't exist!");
                    continue;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Enter a valid ID!");
                continue;
            }

            if(carIdExists){
                done = true;
                String carData;
                carData = getCarData(carID);
                String[] carInfoArray = carData.split(",");
                int index = setCarTableMap("Car Type");
                switch(carInfoArray[index]){ //find the type of car based on 2nd element of array
                    case "Sedan":
                        userCar = new Sedan();
                        break;
                    case "SUV":
                        userCar = new SUV();
                        break;
                    case "Hatchback":
                        userCar = new Hatchback();
                        break;
                    case "Pickup":
                        userCar = new Pickup();
                        break;
                }
                setCarData(carInfoArray, userCar); // initialize the car object fields

                if(!(userCar.getNumAvailable() > 0)){
                    System.out.println("Sorry the car you want to purchase is not available.");
                    break;
                }
                else if(!(user.getBudget() > userCar.getPrice())){
                    System.out.println("Sorry you can't afford the car.");
                }
                else{ //car is available and user has the budget
                    checkout();
                }
            }
            else{
                System.out.println("Enter a valid ID!");
            }
        }
    }

    /**
     * This method helps the user checkout
     */
    public static void checkout(){

        int numCar = userCar.getNumAvailable(); //current quantity of car
        float carPrice = userCar.getPrice(); //current price of car

        carPrice = calculateFinalPrice(carPrice); //final price user will pay for

        System.out.println();
        System.out.println("Congrats you purchased a car.");

        float newUserBudget = user.getBudget() - carPrice; // calculate new budget of user
        user.setBudget(newUserBudget); // update user's budget in user object

        if(numCar > 0){ //cars available
            userCar.setNumAvailable(numCar - 1); //set new quantity of car
        }
        else{ //zero cars available
            userCar.setNumAvailable(0); //quantity will be zero
        }
        user.addToTicket(userCar); //add car to user's ticket
        admin.addToList(userCar); //add car to admin car list

        printUserTicket(); //method will print the user's new ticket
    }

    /**
     * Calculates final price of car
     * @param carPrice car price
     * @return final price
     */
    public static float calculateFinalPrice(float carPrice){

        //if user is a car shop member, then discount 10% from price
        if(user.getIsMember()){
            carPrice = (carPrice - (carPrice * DISCOUNT_RATE));
        }

        //calculate final price with state taxes
        carPrice = (carPrice + (carPrice * STATE_TAX));

        userCar.setRevenue(carPrice);

        return carPrice;
    }

    /**
     * Prints the user's ticket after buying a car
     */
    public static void printUserTicket(){
        System.out.println("Here is your ticket:"); //display ticket for user's car
        System.out.println();
        System.out.println("Type: " + userCar.getType());
        System.out.println("Model: " + userCar.getModel());
        System.out.println("Year: " + userCar.getYear());
        System.out.println("Color: " + userCar.getColor());
        System.out.println();
        System.out.println("Check all of your tickets with option 4 in the menu.");
    }

    /**
     * This method allows the user to return a car they bought
     */
    public static void returnCar(){
        String userOption;
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        int carIndex;

        if(user.getTicketList().isEmpty()){ //user has no cars / send them back to menu
            System.out.println("You have no cars to return yet!");
            return;
        }
        else{
            Car carTemp;
            System.out.println("Here are the cars you own.");
            System.out.println();
            for(int i = 0; i < user.getTicketList().size(); i++){
                carTemp = user.getTicketList().get(i);
                System.out.println(i + ") " + carTemp.getType() + " " + carTemp.getModel());
            }
            System.out.println();
            while(!done){
                System.out.println("Enter the number of the car you want to return.");
                System.out.print(">> ");
                userOption = scan.nextLine();
                try{
                    carIndex = Integer.parseInt(userOption);
                }
                catch(NumberFormatException e){
                    System.out.println("Enter a valid number!");
                    continue;
                }
                if(!(0 <= carIndex && carIndex <= user.getTicketList().size() - 1)){ //check for valid index
                    System.out.println("Enter a valid number!");
                }
                else{
                    carTemp = user.getTicketList().get(carIndex); //car user wants to return
                    //return money to user
                    float userBudget = user.getBudget();
                    float carRevenue = carTemp.getRevenue();
                    user.setBudget(userBudget + carRevenue);

                    //add car back to dealership
                    int quantity = carTemp.getNumAvailable();
                    carTemp.setNumAvailable(quantity + 1);

                    updateCSVFiles();
                    user.getTicketList().remove(carIndex); //remove car from user's ticket list
                    System.out.println("You returned the selected car!");
                    break;
                }
            }
        }
    }


    /**
     * This method reads the car csv file and finds the car that the user wants to buy.
     * It then returns the line of the csv file that contains all the car info.
     * @param carID this is the id of the car the user wants to buy
     * @return a string that has all the info from the user's chosen car
     */
    public static String getCarData(int carID){
        String carFile = "car_data.csv"; //read this file
        BufferedReader reader = null;
        String line = ""; //will store each line of csv

        try{
            reader = new BufferedReader(new FileReader(carFile));
            while((line = reader.readLine()) != null){ //read next line
                String[] row = line.split(","); //change line into array of strings
                int index = setCarTableMap("ID");
                if(row[index].equals("ID")){ //skip first line / the header line
                    continue;
                }
                else if(carID == Integer.parseInt(row[index])){ //a match is found / return the line with car info
                    return line;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                reader.close(); //close the reader
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * This method prints all the cars the user bought.
     * It uses iteration to access all car objects in the ticket list.
     * @param user the user object / user objects have a ticket linked list field
     */
    public static void viewAllTickets(User user){
        System.out.println("Cars purchased by " + user.getFirstName());
        for(Car car : user.getTicketList()){
            System.out.println();
            System.out.println("Type: " + car.getType());
            System.out.println("Model: " + car.getModel());
            System.out.println("Year: " + car.getYear());
            System.out.println("Color: " + car.getColor());
        }
    }

    /**
     * This method updates the budget of the user in the user CSV file.
     * It transfers all the info from the original file to the new one
     * and updates only the budget of the user.
     * @param fileName name of the file to be updated
     * @param userID used to find the user in the file
     * @param newBudget stores the value of the user's new budget and will replace the old value in the new file
     */
    public static void updateUserData(String fileName, String userID, String newBudget){
        String tempFile = "temp.csv";
        File oldFile = new File(fileName);
        File newFile = new File(tempFile);
        String id = "";
        String firstName = "";
        String lastName = "";
        String budget = "";
        String carsOwned = "";
        String membership = "";
        String username = "";
        String password = "";
        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw); //used to write

            Scanner x;
            x = new Scanner(new File(fileName)); //used to read file
            x.useDelimiter("[,\n]");

            while(x.hasNext()){ //read next record
                budget = x.next();
                password = x.next();
                lastName = x.next();
                id = x.next();
                carsOwned = x.next();
                firstName = x.next();
                username = x.next();
                membership = x.next();
                if(id.equals(userID)){ //found user rewrite line with updated budget
                    pw.print(newBudget+","+password+","+lastName+","+id+","+
                            carsOwned+","+firstName+","+username+","+membership+"\n");
                }
                else{ //rewrite line to csv file
                    pw.print(budget+","+password+","+lastName+","+id+","+
                            carsOwned+","+firstName+","+username+","+membership+"\n");

                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(fileName);
            newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    /**
     * This method updates the amount of cars available in the car CSV file.
     * It transfers all the info from the original file to the new one
     * and updates only the quantity of each car purchased by the user.
     * @param fileName name of the file to be updated
     * @param carID used to find the car in the file
     * @param newNumCar stores the new quantity of the car type the user purchased
     */
    public static void updateCarData(String fileName, String carID, String newNumCar){
        String tempFile = "temp.csv";
        File oldFile = new File(fileName);
        File newFile = new File(tempFile);
        String id = "";
        String type = "";
        String model = "";
        String condition = "";
        String color = "";
        String capacity = "";
        String year = "";
        String fuelType = "";
        String transmission = "";
        String vin = "";
        String price = "";
        String num = "";
        String hasTurbo = "";
        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw); //used to write

            Scanner x;
            x = new Scanner(new File(fileName)); //used to read file
            x.useDelimiter("[,\n]");

            while(x.hasNext()){ //read next record
                capacity = x.next();
                type = x.next();
                num = x.next();
                condition = x.next();
                color = x.next();
                id = x.next();
                year = x.next();
                price = x.next();
                transmission = x.next();
                vin = x.next();
                fuelType = x.next();
                model = x.next();
                hasTurbo = x.next();

                if(id.equals(carID)){ //found car rewrite line with updated number of car
                    pw.print(capacity+","+type+","+newNumCar+","+condition+","+color+","
                            +id+","+year+","+price+","+transmission+","+vin+","+fuelType+","+model+","+hasTurbo+"\n");
                }
                else{ //rewrite line to csv file
                    pw.print(capacity+","+type+","+num+","+condition+","+color+","
                            +id+","+year+","+price+","+transmission+","+vin+","+fuelType+","+model+","+hasTurbo+"\n");
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(fileName);
            newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }

    /**
     * This method prompts the user to login as a user or an admin.
     * If they enter 1, they will log in as a user
     * If they enter 2, they will log in as an admin
     * @return the option entered by the person
     */
    public static String loginUserOrAdmin(){
        String userOption;
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("Login: Enter 1 or 2\n");
            System.out.print("  1) User login\n");
            System.out.print("  2) Admin login\n");
            System.out.print(">> ");
            userOption = scan.nextLine();
            if(userOption.equals("1") || userOption.equals("2")){
                return userOption;
            }
            else{
                System.out.println("Invalid option: enter 1 or 2");
            }
        }
    }


    /**
     * This method has all the code that updates the CSV files
     * when a user either signs out or exits the program.
     */
    public static void updateCSVFiles(){
        //update user data in CSV file
        String fileName = "user_data.csv";
        String userToEdit = user.getID();
        String newBudget = String.valueOf(user.getBudget());
        updateUserData(fileName, userToEdit, newBudget);

        //update car data in CSV file
        fileName = "car_data.csv";
        for(Car car : user.getTicketList()){
            String carToEdit = car.getId();
            String newNumCar = String.valueOf(car.getNumAvailable());
            updateCarData(fileName, carToEdit, newNumCar);
        }
    }

    /**
     * Checks if the year of the car is from 1900 to 2025
     * Otherwise creates an exception object and throws it
     * @param year the year of the car
     */
    public static void checkYear(String year) throws YearException{
        int yearNum = Integer.parseInt(year);  
        if(yearNum < 1900 || yearNum >= 2025){
            throw new YearException("\n" + "Enter a year from 1900 to 2024" + "\n");
        }else{
            System.out.println();
        }
    }

    /**
     * If the carType is not Sedan, SUV, Hatchback, and Pickup
     * it creates an exception object and throws it
     * @param carType the admin's input for the car type when admin creates a new car
     */
    public static void checkType(String carType) throws TypeException{
        if(carType.equals("Sedan") || carType.equals("SUV") || carType.equals("Hatchback") || carType.equals("Pickup")){
            System.out.println();
        }else{
            throw new TypeException("\n" + "Enter one of the following: Sedan, SUV, Hatchback, Pickup." + "\n");
        }
    }

    /**
     * The main method that starts the system
     * @param args statements
     */
	public static void main(String[] args) {

       
        /** A boolean set to false. Used to exit a while loop. */
        boolean exitUser = false;
        updateHasTurbo();

        while(true){
            String login = loginUserOrAdmin();
            // if they choose to log in as user
            if(login.equals("1")){
                // make exitUser false again so you can enter the loop
                exitUser = false;
                while(!exitUser){
                    //Start the program by having the user enter their information
                    user = start(); //start now returns a user object
                    if(user == null){
                        return; //case where the program ends if a line
                        //to get a persons info is never found
                    }
                    //userStatsLine is the string we can use to access user information

                    //create user object

                    //initializes the user fields with their csv data
                    //not needed anymore maybe
                    //setUserData(userStatsLine, user);

                    /** An object instance of the Log Class */
                    //Log logObj = new Log();

                    /** A string where the user's input (1-5) will be stored. */
                    String userText;

                    /** A scanner for the main */
                    Scanner myScanner = new Scanner(System.in);

                    // Calling a method from Log class that stores on log txt, whenever the user logging in
                    logObj.userLoggedInLog();
                    System.out.print("\n 1. Display all cars.\n 2. Filter Cars (used / new)\n 3. Purchase a car\n 4. View Tickets\n 5. Return Car\n 6. Sign Out\n 7. Go back to log in as user or admin.\n Write Exit to close program.\n\n");

            
                    /* Prompts user for their action. Depending on the action, different methods will be called.
                    * Deals with exceptions and updates the CSV files.
                    */        
                        while(true){
                            userText = myScanner.nextLine();
                            if(userText.equalsIgnoreCase("exit")){
                                updateCSVFiles();
                                logObj.enterInLogLinkedList();
                                logObj.clearLinkedList();
                                System.exit(0);
                                break;
                            }
                            if(userText.equalsIgnoreCase("1") || userText.equalsIgnoreCase("2")
                                    || userText.equalsIgnoreCase("3") || userText.equalsIgnoreCase("4") || userText.equalsIgnoreCase("5")){
                                actionUser(userText, logObj);
                            }else if(userText.equalsIgnoreCase("6")){
                                logObj.storeUserActions(userText);
                                updateCSVFiles();
                                break;
                            }else if(userText.equalsIgnoreCase("7")){
                                logObj.storeUserActions(userText);
                                updateCSVFiles();
                                // make exitUser true so you exit the user loop, and go back to admin or user menu
                                exitUser = true;
                                break;
                            }else{
                                System.out.print("\nWrong input try again: ");
                            }
                            System.out.print("\n 1. Display all cars.\n 2. Filter Cars (used / new)\n 3. Purchase a car\n 4. View Tickets\n 5. Return Car\n 6. Sign Out\n 7. Go back to log in as user or admin.\n Write Exit to close program.\n\n");
                            //enterInLogLinkedList is a method from the Log class that enters all the logs on a text file
                        }
                
                        

                    
                } // while !exitUser / logged in as user
            logObj.enterInLogLinkedList();
            logObj.clearLinkedList();
            }
            // if they choose to log in as admin
            else if(login.equals("2")){
                //create admin object
                //Admin admin = new Admin(); //this was the error / should only be one admin object!

                System.out.print("\n a. Add a Car to Dealership.\n b. Get Revenue.\n c. Remove a car from the dealership.\n d. Add more users.\n 5. Go back to log in as user or admin.\n");
                Scanner myScanner = new Scanner(System.in);
                /** admin's action*/
                String adminText;

                /* Prompts admin for their action. Depending on the action, different methods will be called.
                * Deals with exceptions.
                */ 

                while(true){
                    adminText = myScanner.nextLine();
                    if(adminText.equalsIgnoreCase("exit")){
                        //updateCSVFiles();
                        System.exit(0);
                        break;
                    }
                    if(adminText.equalsIgnoreCase("a")){
                        String carType;
                        String transmission;
                        String condition;

                        // checks if the car is Sedan, SUV, Hatchback, or Pickup
                        while(true){
                            System.out.println("What is the type of the car? (Sedan, SUV, Hatchback, Pickup)");
                            carType = myScanner.nextLine();
                            try{
                                checkType(carType);
                                break;
                            }catch (Exception e){
                                System.out.println("\n" + "A problem occured: " + e);
                            }
                        }

                        // check the model of the car
                        System.out.println("What is the model of the car?");
                        String model = myScanner.nextLine();

                        // checks the condition of the car, either used or new
                        while(true){
                            System.out.println("What is the car's condition? (Used, New)");
                            condition = myScanner.nextLine();
                            if(condition.equals("Used") || condition.equals("New")){
                                break;
                            }
                            System.out.println("\n" + "Wrong condition. Enter Used or New."  + "\n");
                        }


                        // input the car's color
                        System.out.println("What is the car's color?");
                        String color = myScanner.nextLine();
                        // input the car's capicity
                        System.out.println("What is the car's capacity?");
                        String capacity = myScanner.nextLine();
                        //input the car's fuel type
                        System.out.println("What is the car's fuel type?");
                        String fuel = myScanner.nextLine();

                        // checks if the transmission is manual, or automatic
                        while(true){
                            System.out.println("What is the car's transmission? (Manual, Automatic)" + "\n");
                            transmission = myScanner.nextLine();
                            if(transmission.equals("Manual") || transmission.equals("Automatic")){
                                break;
                            }
                            System.out.println("\n" + "Wrong transmission. Enter Manual or Automatic."  + "\n");
                        }
                        
                        // input the car's VIN
                        System.out.println("\n" + "What is the car's VIN?");
                        String VIN = myScanner.nextLine();
                        String price;
                        while(true){
                            System.out.println("What is the car's price?");
                            price = myScanner.nextLine();
                            try{
                                //try passing string as double
                                Double.parseDouble(price);
                                break;
                            }catch (NumberFormatException e){
                                System.out.println("Invalid price");
                            }
                        }
                        String numAvailable;
                        while(true){
                            System.out.println("What is the number of cars available?");
                            numAvailable = myScanner.nextLine();
                            try{
                                //try passing string as integer
                                Integer.parseInt(numAvailable);
                                break;
                            }catch (NumberFormatException e){
                                System.out.println("Invalid number");
                            }
                        }
                        String year;
                        while(true){
                            System.out.println("What is the year of the car?");
                            year = myScanner.nextLine();
                            try{
                                //try passing string as int
                                Integer.parseInt(year);
                            }catch (NumberFormatException e){
                                System.out.println("Invalid year" + "\n");
                            }

                            try{
                                checkYear(year);
                                break;
                            }catch (Exception e){
                                System.out.println("\n" + "A problem occured: " + e);
                            }

                        }
                        String hasTurbo;
                        while(true){
                            System.out.println("Does is have turbo?");
                            System.out.println("a. Yes      b. No");
                            hasTurbo = myScanner.nextLine();
                            if(hasTurbo.equals("a")){
                                hasTurbo = "Yes";
                                break;
                            }else if(hasTurbo.equals("b")){
                                hasTurbo = "No";
                                break;
                            }else{
                                System.out.println("Invalid choice");
                            }
                        }
                        admin.addCar(carType, model, condition, color, capacity, fuel, transmission, VIN, price, numAvailable, year, hasTurbo);
                        
                    }else if(adminText.equalsIgnoreCase("b")){
                        String revenueType;
                        float revenue;
                        while(true){//will loop until correct choice
                            System.out.println("a. Get revenue by ID    b. Get revenue by car type");
                            revenueType = myScanner.nextLine();
                            if(!revenueType.equals("a") && !revenueType.equals("b")){
                                System.out.println("Invalid choice");
                            }else{
                                break;
                            }
                        }
                        if(revenueType.equals("a")){//revenue by id
                            String id;
                            while(true){//will loop until a correct id is given
                                System.out.println("Type ID to get revenue");
                                id = myScanner.nextLine();
                                try{
                                    //try passing string as int
                                    Integer.parseInt(id);
                                    break;
                                }catch (NumberFormatException e1){
                                    System.out.println("Invalid id");
                                }
                            }
                            revenue = admin.revenueByID(id, admin.getCarList());
                        }else{//else revenue by car type
                            String carType;
                            System.out.println("Type the car type to get revenue from");
                            carType = myScanner.nextLine();

                            revenue = admin.revenueByCarType(carType, admin.getCarList());
                        }

                        System.out.println("Revenue for choice picked is: $" + revenue);

                    }else if(adminText.equalsIgnoreCase("c")){
                        boolean exitDeleteCar = true;
                        while(exitDeleteCar){
                            int carID;
                            System.out.print("Enter car ID (1-100): "); //change to any valid ID
                            String adminIdDelete = myScanner.nextLine();
                            try {
                                carID = Integer.parseInt(adminIdDelete);
                                updateDeleteCar(adminIdDelete); //add this function to admin class
                                exitDeleteCar = false;
                            }
                            catch(NumberFormatException e){
                                System.out.println("Enter a valid ID!");
                                continue;
                            }
                        }
                    }else if(adminText.equalsIgnoreCase("d")){
                        System.out.println("What is the first name?");
                        String firstName = myScanner.nextLine();
                        System.out.println("What is the last name");
                        String lastName = myScanner.nextLine();
                        String carsOwned;
                        while(true){
                            System.out.println("What is the number of cars owned / bought?");
                            carsOwned = myScanner.nextLine();
                            try{
                                //try passing string as int
                                Integer.parseInt(carsOwned);
                                break;
                            }catch (NumberFormatException e1){
                                System.out.println("Invalid number");
                            }
                        }
                        String isMember;
                        String choiceMember;
                        while(true){
                            System.out.println("Is the user a member?");
                            System.out.println("a. Yes      b. No");
                            choiceMember = myScanner.nextLine();
                            if(!choiceMember.equals("a") && !choiceMember.equals("b")){
                                System.out.print("Invalid choice");
                            }else{
                                break;
                            }
                        }
                        if(choiceMember.equals("a")){
                            isMember = "TRUE";
                        }else{
                            isMember = "FALSE";
                        }
                        String budget;
                        while(true){
                            System.out.println("What is the user's budget?");
                            budget = myScanner.nextLine();
                            try{
                                //try passing string as double
                                Double.parseDouble(budget);
                                break;
                            }catch (NumberFormatException e1){
                                System.out.println("Invalid budget");
                            }
                        }
                        System.out.println("What is the username?");
                        String userName = myScanner.nextLine();
                        System.out.println("What is the password?");
                        String password = myScanner.nextLine();

                        admin.addUser(firstName, lastName, carsOwned, isMember, budget, userName, password);
                    }else if(adminText.equalsIgnoreCase("5")){
                        // update the files
                        break;
                    }else{
                        System.out.print("\nWrong input try again: ");
                    }
                    System.out.print("\n a. Add a Car to Dealership.\n b. Get Revenue.\n c. Remove a car from the dealership.\n d. Add more users.\n 5. Go back to log in as user or admin.\n Write Exit to close program.\n\n");

                }
            }else{
                System.out.println("Wrong input!");
                
            }
        }
	} //main
}

