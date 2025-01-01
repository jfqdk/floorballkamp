package dk.jfq.floorballkamp.server;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.util.Headers;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server implements AutoCloseable {

    private final Undertow server;

    private final Logger logger;

    public Server() {
        server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(Handlers.path().addPrefixPath("/", Handlers.resource(new ClassPathResourceManager(Server.class.getClassLoader())))).build();
        logger = Logger.getLogger(Server.class.getName());
    }

    public void start() {
        logger.info("Starting Floornallkamp version X");
        server.start();
        logger.info("Ready!");
    }

    @Override
    public void close() throws Exception {
        server.stop();
    }
}
