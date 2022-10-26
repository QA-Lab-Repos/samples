package org.example;

import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.io.IOException;

public class ResponseUtility {
    private static final String TEXTRESPONSETYPE = "text/plain";

    private ResponseUtility() {}

    static void setErrorResponse(Response response, final String message) throws IOException {
        response.setContentType(TEXTRESPONSETYPE);
        response.getWriter().write(message);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
    }

    static void setAcceptedResponse(Response response, final String message) throws IOException {
        response.setContentType(TEXTRESPONSETYPE);
        response.getWriter().write(message);
        response.setStatus(HttpStatus.OK_200);
    }
}
