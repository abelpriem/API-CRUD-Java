package com.example.controllers;

import java.io.IOException;
import java.io.OutputStream;

public class Handler implements HttpHandler {
    String response = null;
    int statusCode;

    public void handle(HttpExchange exchange) throws IOException {

        String path = exchange.getRequestURI().getPath();
        String method = null;

        if (path == "/api/solicitudes/crear") {
            method = "POST";
        } else if (path == "/api/solicitudes") {
            method = "GET";
        } else if (path == "/api/solicitudes/{id}") {
            method = "PUT";
        } else if (path == "/api/solicitudes/orden") {
            method = "GET";
        } else if (path == "/api/solicitudes/comprobar/{id}") {
            method = "GET";
        } else if (path == "/api/solicitudes/pendiente/{id}") {
            method = "PUT";
        } else if (path == "/api/solicitudes/en-curso/{id}") {
            method = "PUT";
        } else if (path == "/api/solicitudes/finalizada/{id}") {
            method = "PUT";
        } else if (path == "/api/solicitudes/eliminar/{id}") {
            method = "DELETE";
        }

        switch (method) {
            case "POST":

                System.out.println(path);
                handlePostRequest(exchange, path);
                break;

            case "GET":

                System.out.println(path);
                handleGetRequest(exchange, path);
                break;

            case "PUT":

                System.out.println(path);
                handlePutRequest(exchange, path);
                break;

            case "DELETE":

                System.out.println(path);
                handleDeleteRequest(exchange, path);
                break;

            default:
                statusCode = 405;
                exchange.sendResponseHeaders(statusCode, -1);
                break;
        }

        OutputStream osStream = exchange.getResponseBody();
        osStream.write(response.getBytes());
        osStream.close();
    }

    private void handlePostRequest(HttpExchange exchange, String path) throws IOException {
        byte[] requestBody = exchange.getRequestBody().readAllBytes();
        String body = new String(requestBody);

        System.out.println("Solicitud POST recibida con cuerpo: " + body);

        statusCode = 201;
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);

        OutputStream oStream = exchange.getResponseBody();
        oStream.write(response.getBytes());
        oStream.close();
    }

    private void handleGetRequest(HttpExchange exchange, String path) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        statusCode = 200;
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);

        OutputStream oStream = exchange.getResponseBody();
        oStream.write(response.getBytes());
        oStream.close();
    }

    private void handlePutRequest(HttpExchange exchange, String path) throws IOException {
        byte[] requestBody = exchange.getRequestBody().readAllBytes();
        String body = new String(requestBody);

        System.out.println("Solicitud PUT recibida con cuerpo: " + body);

        statusCode = 200;
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);

        OutputStream osStream = exchange.getResponseBody();
        osStream.write(response.getBytes());
        osStream.close();
    }

    private void handleDeleteRequest(HttpExchange exchange, String path) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        statusCode = 200;
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);

        OutputStream oStream = exchange.getResponseBody();
        oStream.write(response.getBytes());
        oStream.close();
    }
}