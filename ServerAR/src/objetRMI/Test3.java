package objetRMI;

import java.io.Serializable;

public class Test3 implements Serializable, TestInterface{

    public void salut(){
        System.out.println("Test3");
    }
}
