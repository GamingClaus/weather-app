# 🌤️ Weather App

A simple Java console app that fetches and displays the current weather of a given city using the [WeatherAPI](https://www.weatherapi.com/).

---

## 🚀 Features

- Takes city name as input
- Fetches real-time weather data using HTTP requests
- Parses and displays:
  - City and country
  - Local date
  - Temperature (°C / °F)
  - Weather condition
- Loops until you type `exit`

---

## 🛠️ Built With

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [WeatherAPI](https://www.weatherapi.com/)
- [Jackson](https://github.com/FasterXML/jackson) (for JSON parsing)

---

## 📦 Getting Started

### Prerequisites

- Java installed
- Maven installed
- WeatherAPI key (free to get from [weatherapi.com](https://www.weatherapi.com/))

### Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/weather-app.git
   cd weather-app
2. Add your API Key to the `API_Key` variable in the code.
3. Run the app:
   ```
   mvn compile
   mvn exec:java
---
## ❗ Handling Errors

If the user types an invalid city, the app currently may crash.  
To fix this, you can check the status code or catch the JSON exception and show a friendly message.

---

## 📄 License

This project is open source and free to use.
