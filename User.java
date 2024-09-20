import java.util.LinkedList;

/**
 * Represents a user object.
 * Implements the person interface.
 */
public class User implements Person {

    /** the id */
    private String id;
    /** user's firstname */
    private String firstName;
    /** user's lastname*/
    private String lastName;

    /** budget of user */
    private float budget;
    /** how many cars owned by user */
    private int carsOwned;
    /** if user is a member of car dealership */
    private boolean isMember;
    /** username of person*/
    private String userName;
    /** password of person */
    private String password;

    /**
     * LinkedList to store all the car objects owned by the user
     * Each element in the list represents a car object owned by the user.
     */
    private LinkedList<Car> ticket = new LinkedList<>();

    /**
     * Constructs a new User object with default values
     */
    public User(){
        super();
    }

    /**
     * Constructs a new User object with specified budget, number of cars owned, and membership status.
     * @param id The unique identifier of the person.
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     * @param budget The budget of the user.
     * @param carsOwned The number of cars owned by the user.
     * @param isMember Indicates whether the user has a membership with the shop.
     * @param userName The username of the person.
     * @param password The password associated with the person's account.
     */
    public User(String id, String firstName, String lastName, float budget, int carsOwned, boolean isMember, String userName, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.budget = budget;
        this.carsOwned = carsOwned;
        this.isMember = isMember;
        this.userName = userName;
        this.password = password;
    }

    //Setters

    /**
     * Sets the budget of the user.
     * @param budget The new budget to be set.
     */
    public void setBudget(float budget){this.budget = budget;}

    /**
     * Sets the number of cars owned by the user.
     * @param carsOwned The new number of cars owned to be set.
     */
    public void setCarsOwned(int carsOwned){this.carsOwned = carsOwned;}

    /**
     * Sets the membership status of the user.
     * @param isMember The new membership status to be set.
     */
    public void setIsMember(boolean isMember){this.isMember = isMember;}

    /**
     * Sets the username of the person.
     * @param userName The new username value to be set.
     */
    public void setUserName(String userName){this.userName = userName;}

    /**
     * Sets the password associated with the person's account.
     * @param password The new password value to be set.
     */
    public void setPassword(String password){this.password = password;}

    //Getters

    /**
     * Retrieves the budget of the user.
     * @return The budget of the user.
     */
    public float getBudget(){return this.budget;}

    /**
     * Retrieves the number of cars owned by the user.
     * @return The number of cars owned by the user.
     */
    public int getCarsOwned(){return this.carsOwned;}

    /**
     * Retrieves the membership status of the user.
     * @return The membership status of the user.
     */
    public boolean getIsMember(){return this.isMember;}

    /**
     * Retrieves the username of the person.
     * @return The username of the person.
     */
    public String getUserName(){return this.userName;}

    /**
     * Retrieves the password associated with the person's account.
     * @return The password of the person's account.
     */
    public String getPassword(){return this.password;}


    //Others

    /**
     * Adds a car to the user's ticket.
     * @param car The car object to be added to the user's ticket.
     */
    public void addToTicket(Car car){
        ticket.add(car);
    }

    /**
     * Retrieves the list of cars stored in the user's ticket.
     * @return The LinkedList containing the cars in the user's ticket.
     */
    public LinkedList<Car> getTicketList(){
        return this.ticket;
    }

    // ***** INTERFACE METHODS ***** //

    /**
     * set id
     * @param id The new ID value to be set.
     */
    @Override
    public void setID(String id) {
        this.id = id;
    }

    /**
     * set firstname
     * @param firstName The new first name value to be set.
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * set last name
     * @param lastName The new last name value to be set.
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * get id
     * @return id
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * get first name
     * @return firstname
     */
    @Override
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * get last name
     * @return last name
     */
    @Override
    public String getLastName() {
        return this.lastName;
    }
}
