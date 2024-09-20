import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

public class AdminTest {
    /*Testing revenueID */

    @Test
    public void revenueByIDTest1(){
        Admin temp = new Admin();
        Car testCar = new Car();
        //car with id 15 will have revenue of 12
        testCar.setID("15");
        testCar.setRevenue(12);
        float expected = 12; //expected revenue of 12
        LinkedList<Car> carList = new LinkedList<>();
        carList.add(testCar);
        float revenue = temp.revenueByID(testCar.getId(), carList);
        assertEquals(expected, revenue, 0.01);
    }

    @Test
    public void revenueByIDTest2(){
        Admin temp = new Admin();
        Car testCar = new Car();
        //car with id 15 will have revenue of 20
        testCar.setID("20");
        testCar.setRevenue(12);
        float expected = 9; //expected revenue of 9 (testing failure here ♪)
        LinkedList<Car> carList = new LinkedList<>();
        carList.add(testCar);
        float revenue = temp.revenueByID(testCar.getId(), carList);
        assertNotEquals(expected, revenue, 0.01);
    }

    /*testing revenue by car type */
    @Test
    public void revenueByCarTypeTest1(){
        Admin temp = new Admin();
        Car testCar = new Car();
        testCar.setCarType("Hatchback");
        testCar.setRevenue(5);

        Car testCar2 = new Car();
        testCar2.setCarType("Hatchback");
        testCar2.setRevenue(5);

        Car testCar3 = new Car();
        testCar3.setCarType("SUV");
        testCar3.setRevenue(7);

        LinkedList<Car> carList = new LinkedList<>();
        carList.add(testCar);
        carList.add(testCar2);
        carList.add(testCar3);

        float revenue = temp.revenueByCarType("Hatchback", carList);

        float expected = 10;

        assertEquals(expected, revenue, 0.01);
    }

    @Test
    public void revenueByCarTypeTest2(){
        Admin temp = new Admin();
        Car testCar = new Car();
        testCar.setCarType("Hatchback");
        testCar.setRevenue(5);

        Car testCar2 = new Car();
        testCar2.setCarType("Hatchback");
        testCar2.setRevenue(5);

        Car testCar3 = new Car();
        testCar3.setCarType("SUV");
        testCar3.setRevenue(7);

        LinkedList<Car> carList = new LinkedList<>();
        carList.add(testCar);
        carList.add(testCar2);
        carList.add(testCar3);

        float revenue = temp.revenueByCarType("SUV", carList);

        float expected = 7;

        assertEquals(expected, revenue, 0.01);
    }
}