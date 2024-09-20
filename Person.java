/**
 * Represents a generic person entity.
 * Contains methods that the person subclasses may use.
 */
public interface Person {

    /**
     * Sets the unique identifier of the person.
     * @param id The new ID value to be set.
     */
    void setID(String id);

    /**
     * Sets the first name of the person.
     * @param firstName The new first name value to be set.
     */
    void setFirstName(String firstName);

    /**
     * Sets the last name of the person.
     * @param lastName The new last name value to be set.
     */
    void setLastName(String lastName);

    /**
     * Retrieves the unique identifier of the person.
     * @return The ID of the person.
     */
    String getID();

    /**
     * Retrieves the first name of the person.
     * @return The first name of the person.
     */
    String getFirstName();

    /**
     * Retrieves the last name of the person.
     * @return The last name of the person.
     */
    String getLastName();
}
