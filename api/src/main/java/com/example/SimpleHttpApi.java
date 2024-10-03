package com.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpApi {
    public static void main(String[] args) throws IOException {
        int port = 8080;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api", new Handler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server online! Listening on PORT: " + port);
    }

    static class Handler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                handleGetRequest(exchange);
            } else if ("POST".equals(exchange.getRequestMethod())) {
                handlePostRequest(exchange);
            } else if ("PATCH".equals(exchange.getRequestMethod())) {
                handlePatchRequest(exchange);
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                handleDeleteRequest(exchange);
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }

        private void handleGetRequest(HttpExchange exchange) throws IOException {
            String response = "[{\success\": false}]";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            OutputStream oStream = exchange.getResponseBody();
            oStream.write(response.getBytes());
            oStream.close();
        }

        private void handlePostRequest(HttpExchange exchange) throws IOException {
            byte[] requestBody = exchange.getRequestBody().readAllBytes();
            String body = new String(requestBody);

            System.out.println("Solicitud POST recibida con cuerpo: " + body);

            String response = "Tarea creada exitosamente!";
            exchange.sendResponseHeaders(201, response.getBytes().length);
            OutputStream oStream = exchange.getResponseBody();
            oStream.write(response.getBytes());
            oStream.close();
        }

        private void handlePatchRequest(HttpExchange exchange) throws IOException {
            // PENDIENTE
        }

        private void handleDeleteRequest(HttpExchange exchange) throws IOException {
            // PENDIENTE
        }
    }
}
