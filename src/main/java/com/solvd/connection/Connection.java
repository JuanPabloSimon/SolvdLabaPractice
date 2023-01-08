package com.solvd.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {
    private static final Logger LOGGER = LogManager.getLogger(Connection.class);
    protected final String name;
    protected boolean isAvailable;

    public Connection(String name) {
        this.name = name;
        LOGGER.info("The connection " + name + " was created");
    }

    void connect() {
        LOGGER.info("Connecting " + name);
        isAvailable = false;
    }

    void disconnect() {
        LOGGER.info("Disconnecting " + name);
        isAvailable = true;
    }
}
