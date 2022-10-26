package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.Buffer;

import java.io.IOException;

public class PowerApiHandler extends HttpHandler {
    @Override
    public void service(Request request, Response response) throws IOException {
        int contentLength = request.getContentLength();
        Buffer requestBodyBuffer;
        try {
            requestBodyBuffer = request.getPostBody(contentLength);
        } catch (IOException e) {
            String message = "Request contained no body.";
            ResponseUtility.setErrorResponse(response, message);
            return;
        }
        String requestBody = requestBodyBuffer.toStringContent();

        ObjectMapper mapper = new ObjectMapper();

        // Do something with the request
        PowerApiRequestBody powerApiRequestBody = mapper.readValue(requestBody, PowerApiRequestBody.class);
    }
}
