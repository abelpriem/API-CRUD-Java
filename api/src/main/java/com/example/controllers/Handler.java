package com.example.controllers;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Handler implements HttpHandler {
    String response = null;
    int statusCode;

    public void handle(HttpExchange exchange) throws IOException {

        // RECIBIMOS EL MÉTODO DE LA PETICIÓN Y LA RUTA
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        switch (method) {
            case "POST":
                // Method POST
                handlePostRequest(exchange, path);
                break;

            case "GET":
                // Method GET
                handleGetRequest(exchange, path);
                break;

            case "PUT":
                // Method PUT
                handlePutRequest(exchange, path);
                break;

            case "DELETE":
                // Method DELETE
                handleDeleteRequest(exchange, path);
                break;

            default:
                statusCode = 405;
                exchange.sendResponseHeaders(statusCode, -1);
                break;
        }

        // RECIBIMOS LA SOLICITUD/RESPUESTA DEL CONTROLADOR
        OutputStream osStream = exchange.getResponseBody();
        osStream.write(response.getBytes());
        osStream.close();
    }

    // POST METHOD
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

    // GET METHOD
    private void handleGetRequest(HttpExchange exchange, String path) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        statusCode = 200;
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);

        OutputStream oStream = exchange.getResponseBody();
        oStream.write(response.getBytes());
        oStream.close();
    }

    // PUT METHOD
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

    // DELETE METHOD
    private void handleDeleteRequest(HttpExchange exchange, String path) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        statusCode = 200;
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);

        OutputStream oStream = exchange.getResponseBody();
        oStream.write(response.getBytes());
        oStream.close();
    }
}