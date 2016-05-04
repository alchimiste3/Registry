package objetRMI;

import java.io.Serializable;

public class Test implements Serializable, TestInterface{

    public void salut(){
        System.out.println("Test");
    }
}
