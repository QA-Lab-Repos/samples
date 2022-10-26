package org.example;

import org.apache.commons.lang3.StringUtils;

public class PowerServerConfiguration {
    private String ipAddress;
    private int port;

    PowerServerConfiguration() {
        ipAddress = "";
        port = 0;
    }

    static boolean validate(PowerServerConfiguration serverConfiguration) {
        if (StringUtils.isEmpty(serverConfiguration.ipAddress) || StringUtils.isBlank(serverConfiguration.ipAddress)) {
            return false;
        }

        if (serverConfiguration.port <= 0 || serverConfiguration.port > 65535) {
            return false;
        }

        return true;
    }

    String getIpAddress() {
        return ipAddress;
    }

    void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    int getPort() {
        return port;
    }

    void setPort(int port) {
        this.port = port;
    }
}
