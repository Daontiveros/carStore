/**
 * This class is a blueprint for car objects.
 * This class is also a superclass of the
 * Sedan, SUV, Hatchback, and Pickup classes.
 * @author Daniel Avitia
 */
public class Car{
    //Using protected so that they can be seen from subclasses
    /** car id */
    protected String id;
    /** car type */
    protected String carType;
    /** car model */
    protected String model;
    /** car condition */
    protected String condition;
    /** car color */
    protected String color;
    /** car capacity */
    protected String capacity;
    /**  car mileage */
    protected String mileage;
    /** car fuel */
    protected String fuel;
    /** car transmission */
    protected String transmission;
    /** car VIN */
    protected String VIN;
    /** car price */
    protected float price;
    /** car quantity */
    protected int numAvailable;
    /** car year */
    protected String year;

    /** car revenue */
    protected float revenue;

    /**
     * Empty constructor to set default values.
     */
    public Car(){

    }

    /**
     * This constructor is here for convenience when filtering between new and old cars in RunShop.java.
     */
    public Car(String condition){
        this.condition = condition;
    }

    /**
     * Constructs a new Car object with complete attributes.
     *
     * @param id The ID of the car.
     * @param carType The type of the car.
     * @param model The model of the car.
     * @param condition The condition of the car.
     * @param color The color of the car.
     * @param capacity The capacity of the car.
     * @param mileage The mileage of the car.
     * @param fuel The fuel type of the car.
     * @param transmission The transmission type of the car.
     * @param VIN The Vehicle Identification Number (VIN) of the car.
     * @param price The price of the car.
     * @param numAvailable The number of available cars of this type.
     */
    public Car(String id, String carType, String model, String condition, String color, String capacity,
    String mileage, String fuel, String transmission, String VIN, float price, int numAvailable){
        this.id = id;
        this.carType = carType;
        this. model = model;
        this.condition = condition;
        this.color = color;
        this.capacity = capacity;
        this.mileage = mileage;
        this.fuel = fuel;
        this.transmission = transmission;
        this.VIN = VIN;
        this.price = price;
        this.numAvailable = numAvailable;
    }

    //Setters

    /**
     * This method sets the id for the car object.
     * @param id id of car
     */
    public void setID(String id){
        this.id = id;
    }

    /**
     * This method sets the car type.
     * @param carType type of car
     */
    public void setCarType(String carType){
        this.carType = carType;
    }

    /**
     * This method sets the model of the car.
     * @param model model of car
     */
    public void setModel(String model){
        this.model = model;
    }

    /**
     * This method sets the color of the car.
     * @param color color of car
     */
    public void setColor(String color){
        this.color = color;
    }

    /**
     * This method sets the year of the car.
     * @param year year of car
     */
    public void setYear(String year){
        this.year = year;
    }

    /**
     * This method sets the number of cars available for this type.
      * @param num quantity of car
     */
    public void setNumAvailable(int num){
        this.numAvailable = num;
    }

    /**
     * set price
     * @param price price of car
     */
    public void setPrice(float price){
        this.price = price;
    }

    /**
     * set revenue
     * @param revenue revenue of car
     */
    public void setRevenue(float revenue){
        this.revenue = revenue;
    }

    //Getters

    /**
     * This method returns the id of the car.
     * @return id
     */
    public String getId(){
        return this.id;
    }

    /**
     * This method returns the car type.
     * @return type
     */
    public String getType(){
        return this.carType;
    }

    /**
     * This method returns the model of the car.
     * @return model
     */
    public String getModel(){
        return this.model;
    }

    /**
     * This method returns the car condition.
     * @return condition
     */
    public String getCondition(){
        return this.condition;
    }

    /**
     * This method returns the car color
     * @return color
     */
    public String getColor(){
        return this.color;
    }

    public String getCapacity(){
        return this.capacity;
    }
    public String getMileage(){
        return this.mileage;
    }
    public String getFuel(){
        return this.fuel;
    }
    public String getTransmission(){
        return this.transmission;
    }
    public String getVIN(){
        return this.VIN;
    }

    /**
     * get price of car
     * @return price
     */
    public float getPrice(){
        return this.price;
    }

    /**
     * This method returns the quantity of the car type.
     * @return quantity of specific car
     */
    public int getNumAvailable(){
        return this.numAvailable;
    }

    /**
     * This method returns the year of the car
     * @return year
     */
    public String getYear(){
        return this.year;
    }

    /**
     * get revenue of car
     * @return revenue
     */
    public float getRevenue(){
        return this.revenue;
    }
}
