package com.demoPack.responsibility;

public abstract class AbstractHandler {

    AbstractHandler next = null;

    public void setNext(AbstractHandler next) {
        this.next = next;
    }

    public void handle() {
        action();
        if(next != null) {
            next.handle();
        }
    }

    public abstract void action();
}
