package org.example.javaspi;

public class MyServiceSpiImpl  implements MyServiceSpi{
    @Override
    public void execute() {
        System.out.println("MyServiceSpiImpl   ----execute");
    }
}
