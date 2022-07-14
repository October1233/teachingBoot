package com.demoPack.command;



public class Client {

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        HelloCommand helloCommand = new HelloCommand(receiver);
        ByeCommand byeCommand = new ByeCommand(receiver);
        Invoker invoker = new Invoker(helloCommand);
        invoker.action();
        invoker.setCommand(byeCommand);
        invoker.action();
    }
}

