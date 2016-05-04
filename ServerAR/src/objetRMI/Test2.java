package objetRMI;

import java.io.Serializable;

public class Test2 implements Serializable, TestInterface{

    public void salut(){
        System.out.println("Test2");
    }
}
