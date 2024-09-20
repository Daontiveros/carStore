import java.util.LinkedList;
import java.io.IOException;
import java.util.Calendar;
import java.io.*;

/**
* This class places every
* action performed by the user
* inside a text file call log.
* @author Dayanna Ontiveros
*/
public class Log{

    /** The linked-list containing the strings that will be placed on the log text file */
    LinkedList<String> logByUser = new LinkedList<>();

    /** A calendar to obtain the current date */
    Calendar calendar = Calendar.getInstance();

    /** The string that contains the date, and the user's action */
    String logActions;

    /** The file where the log will be written */
    String file = "log.txt";

    /** Will store the only instance of log / part of Singleton Design Pattern */
    private static Log firstInstance = null;

    /** Sole Constructor */
    private Log(){}

    /**
     * This method creates the one and only log object.
     * This log object is created using the Singleton Design pattern.
     * @return log object
     */
    public static Log getInstanceOf() {
        if(firstInstance == null){
            firstInstance = new Log();
        }
        return firstInstance;
    }

    /** Creates a string with the current date and the
     *  user's action which is that they logged in.
     *  It adds the string to a linked list.
     */
    public void userLoggedInLog(){
        // Creates a string with the date and user's action
        logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " +  java.time.LocalDate.now() + " - User Name logged in";
        // Adds the string to the linked list
        logByUser.add(logActions);
    }

    /** clears the linked list */
    public void clearLinkedList(){
        logByUser.clear();
    }
    
    /** Creates a string with the current date and
     * the user's action. The user's action can
     * be that they filtered the cars by new, used,
     * or decided to go back. 
     * It adds the string to a linked list.
     * @param userFilterCarActions the user's actions
     * when filtering a car. It can be 1,2, or 3.
    */
    public void filterCarActions(String userFilterCarActions){
        // Turns user's action into an int
        int filterCarNumber = Integer.parseInt(userFilterCarActions);
        // Switches between the user's actions.
        switch(filterCarNumber){
            case 1:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " +  java.time.LocalDate.now() + " - User Name filtered Cars by New";
                break;
            case 2:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + java.time.LocalDate.now() + " - User Name filtered Cars by Used";
                break;
            case 3:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + java.time.LocalDate.now() + " - User Name went Back";
                break;
        }
        // Adds the string to the linked list
        logByUser.add(logActions);
    }
    

    /** Creates a string with the current date and
     * the user's action. The user's action can
     * be that they displayed cars, filtered the cars, 
     * purchased a car, viewed tickets or signed out.
     * @param userAction the user's actions
     * on the menu. It can be 1,2,3,4 or 5.
    */
    public void storeUserActions(String userAction){
        // Turns user's action into an int
        int actionNumber = Integer.parseInt(userAction);
        // Switches between the user's actions.
        switch(actionNumber){
            case 1:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + java.time.LocalDate.now() + " - User Name displayed all cars";
                break;
            case 2:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + java.time.LocalDate.now() + " - User Name filtered all cars";
                break;
            case 3:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + java.time.LocalDate.now() + " - User Name purchased car";
                break;
            case 4:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + java.time.LocalDate.now() + " - User Name viewed tickets";
                break;
            case 5:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + java.time.LocalDate.now() + " - User Name Returned Car";
                break;
            case 6:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + java.time.LocalDate.now() + " - User Name signed out";
                break;
            case 7:
                // Creates a string with the date and user's action
                logActions = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " " + java.time.LocalDate.now() + " - User Name went back to Log in as Admin or User";

                break;
        }
        // Adds the string to the linked list
        logByUser.add(logActions);
    }
    
    
    /** Adds every string from the linked
     * list to a file.
    */
    public void enterInLogLinkedList() {
        // An int set to 0 to traverse through the linked list
        int iterating = 0;
        try{
            //true for appending to the file
            FileWriter pen = new FileWriter(file, true); 
            // Create a printwriter
            PrintWriter write = new PrintWriter(pen);

            // Traverse the link list
            while(iterating < logByUser.size()) {
                //write the content to the file log.txt
                write.println(logByUser.get(iterating));
                iterating++;
            }

            //close the writer
            write.close();
        }catch(IOException e){
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

}