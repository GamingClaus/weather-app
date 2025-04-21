package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class WeatherApp {

    private static final String POSTS_API_URL = "https://api.weatherapi.com/v1/current.json";
    private static final String API_KEY = "71c3b10469304e8492b102926252104";

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("Enter the City Name (Type 'exit' to quit):");
            String name = scanner.nextLine();

            if(name.equalsIgnoreCase("exit")){
                break;
            }

            String encodedCity = URLEncoder.encode(name, StandardCharsets.UTF_8);
            String fullUrl = POSTS_API_URL + "?key=" + API_KEY + "&q=" + encodedCity;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(fullUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Converting the JSON into Objects
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.body());

            if(root.has("error")){
                String errorMsg = root.path("error").path("message").asText();
                System.out.println(errorMsg + "Please try again!!");
                continue;
            }

            // Getting the required objects from the JSON file.
            String cityName = root.path("location").path("name").asText();
            String countryName = root.path("location").path("country").asText();
            String[] localDate = root.path("location").path("localtime").asText().split(" ");

            double tempInCelsius = root.path("current").path("temp_c").asDouble();
            double tempInFahrenheit = root.path("current").path("temp_f").asDouble();

            String weatherCondition = root.path("current").path("condition").path("text").asText();

            System.out.println("=====================================================");
            System.out.println(
                    "City Name: " + cityName + "\n" +
                    "Country: " + countryName + "\n" +
                    "LocalDate: " + localDate[0] + "\n" +
                    "Temperature In Celsius: " + tempInCelsius + "°C\n" +
                    "Temperature In Fahrenheit: " + tempInFahrenheit + "°F\n" +
                    "Weather Condition: " + weatherCondition);
            System.out.println("=====================================================\n");
        }

    }



}
