package com.demoPack.command;

public class HelloCommand implements Command {

    private Receiver receiver = null;

    public HelloCommand(Receiver receiver) {
        super();
        this.receiver = receiver;
    }

    public void execute() {
        receiver.helloAction();
    }
}

