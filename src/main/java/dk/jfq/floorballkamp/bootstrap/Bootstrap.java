package dk.jfq.floorballkamp.bootstrap;

import dk.jfq.floorballkamp.server.Server;

import java.util.logging.LogManager;


public class Bootstrap {

    public static void main(String[] args) throws Exception {
        LogManager.getLogManager().readConfiguration(Bootstrap.class.getClassLoader().getResourceAsStream("logging.properties"));
        try (Server server = new Server()) {
            server.start();
            System.in.read();
        }
    }
}
