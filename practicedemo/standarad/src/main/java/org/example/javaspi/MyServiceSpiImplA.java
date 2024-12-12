package org.example.javaspi;

public class MyServiceSpiImplA implements MyServiceSpi{
    @Override
    public void execute() {
        System.out.println("MyServiceSpiImplA   ----execute");
    }
}