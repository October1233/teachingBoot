package com.demoPack.command;

public class ByeCommand implements Command {

    private Receiver receiver = null;

    public ByeCommand(Receiver receiver) {
        super();
        this.receiver = receiver;
    }

    public void execute() {
        receiver.byeAction();
    }
}

