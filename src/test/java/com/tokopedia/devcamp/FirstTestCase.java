package devcamp;

import org.testng.annotations.Test;

public class FirstTestCase {

    @Test(priority = 3)
    void setup(){
        System.out.println("Misal tahap setup itu Opening Browser 2123123");
    }

    @Test(priority = 1)
    void login(){
        System.out.println("berikutnya adalah loginnnn 12312312");
    }
    
}
