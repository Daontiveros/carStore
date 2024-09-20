import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RunShopTest {

    /*Testing doeasCarExist method */

    @Test
    public void doesCarExistTest1(){
        // Testing with id 15
        int id = 15;
        // Assuming RunShop.doesCarExist(id) returns a boolean
        assertTrue(RunShop.doesCarExist(id));
    }

    @Test
    public void doesCarExistTest2(){
        // Testing with id 1
        int id = 1;
        // Assuming RunShop.doesCarExist(id) returns a boolean
        assertTrue(RunShop.doesCarExist(id));
    }

    @Test
    public void doesCarExistTest3(){
        // Testing with id 200 which does not exist
        int id = 200;
        // Assuming RunShop.doesCarExist(id) returns a boolean
        assertFalse(RunShop.doesCarExist(id));
    }
}
