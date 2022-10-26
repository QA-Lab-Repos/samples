package org.example;

import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("Creating PowerServeApi");
        PowerSensorServer server = new PowerSensorServer();
        server.startServerOnConsole(args);
    }
}