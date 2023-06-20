package com.kodilla.studentdatabase.views.list.form;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiForStudentsForm extends FormLayout {

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=poznan&appid=df88ba47b33d294848fc226bc448d4da&units=metric";
    private final Div dateTimeDiv;
    private final Div weatherDiv;

    public ApiForStudentsForm() {
        dateTimeDiv = new Div();
        weatherDiv = new Div();

        dateTimeDiv.getStyle().set("font-size", "30px").set("margin-bottom", "10px");
        weatherDiv.getStyle().set("font-size", "16px");

        add(dateTimeDiv, weatherDiv);

        refreshData();
    }
    private void refreshData() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String city = "Poznań";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, null, String.class);
        String responseBody = response.getBody();

        JSONObject json = new JSONObject(responseBody);

        if (!json.has("main") || !json.has("weather")) {
            weatherDiv.setText("Weather information not available");
            return;
        }

        JSONObject main = json.getJSONObject("main");
        JSONArray weatherArray = json.getJSONArray("weather");

        if (weatherArray.length() == 0) {
            weatherDiv.setText("Weather information not available");
            return;
        }

        double temperature = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        int pressure = main.getInt("pressure");
        String weatherDescription = weatherArray.getJSONObject(0).getString("description");

        Paragraph temperatureParagraph = new Paragraph("Temperature: " + temperature + "Celsius");
        Paragraph humidityParagraph = new Paragraph("Humidity: " + humidity + "%");
        Paragraph pressureParagraph = new Paragraph("Pressure: " + pressure + " hPa");
        Paragraph descriptionParagraph = new Paragraph("Description: " + weatherDescription);

        dateTimeDiv.setText(formattedDateTime);
        weatherDiv.removeAll();
        weatherDiv.add(new Text("Current Weather in " + city + ":"));
        weatherDiv.add(temperatureParagraph, humidityParagraph, pressureParagraph, descriptionParagraph);

    }

}