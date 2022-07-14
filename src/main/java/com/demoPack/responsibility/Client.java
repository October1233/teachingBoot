package com.demoPack.responsibility;


public class Client {

    public static AbstractHandler initChain() {
        ConcreteHandlerA concreteHandlerA = new ConcreteHandlerA();
        ConcreteHandlerB concreteHandlerB = new ConcreteHandlerB();
        concreteHandlerA.setNext(concreteHandlerB);
        return concreteHandlerA;
    }

    public static void main(String[] args) {
        AbstractHandler handlerChain = initChain();
        handlerChain.handle();
    }
}

