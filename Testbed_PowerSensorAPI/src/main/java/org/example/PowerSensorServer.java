package org.example;

import org.apache.commons.lang3.math.NumberUtils;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;

import org.apache.commons.cli.*;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class PowerSensorServer {
    private HttpServer createServer(String hostAddress, int port) {
        final HttpServer server = HttpServer.createSimpleServer("/", hostAddress, port);
        final ServerConfiguration config = server.getServerConfiguration();

        config.addHttpHandler(new PowerApiHandler(), "/power");

        return server;
    }

    Options constructCommandLineOptions() {
        Options options = new Options();

        options.addOption("i", "ipaddress", true, "IP address to bind");
        options.addOption("p", "port", true, "Port number to bind");

        return options;
    }

    PowerServerConfiguration parseCommandLine(String[] args, final Options options) throws ParseException {
        final CommandLineParser parser = new DefaultParser();
        final PowerServerConfiguration config = new PowerServerConfiguration();

        CommandLine commandLine = parser.parse(options, args);
        if (commandLine.hasOption("i")) {
            String address = commandLine.getOptionValue("i");
            config.setIpAddress(address);
        }
        else {
            throw new IllegalArgumentException("i parameter is required.");
        }

        if (commandLine.hasOption("p")) {
            String port = commandLine.getOptionValue("p");
            config.setPort(NumberUtils.toInt(port));
        }
        else {
            throw new IllegalArgumentException("p parameter is required.");
        }

        if (!PowerServerConfiguration.validate(config)) {
            throw new IllegalArgumentException("Configuration not valid.");
        }

        return config;
    }

    public void startServerOnConsole(String[] args) throws ParseException {
        Options options = constructCommandLineOptions();
        if (args.length < 2) {
            throw new IllegalArgumentException("You must specify the port.");
        }
        PowerServerConfiguration serverConfiguration = parseCommandLine(args, options);
        final HttpServer server = createServer(serverConfiguration.getIpAddress(), serverConfiguration.getPort());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // "Shutting down the server due to process termination.");
            server.shutdownNow();
            // "Server stopped.");
        }));

        try {
            server.start();
            // ("Press CTRL^C to exit..");
            Thread.currentThread().join();
        } catch (IOException | InterruptedException ioe) {
            // logger.info(ioe.toString(), ioe);
        }
    }
}
