package com.example.module6weatherapp

class Model {
    val name: String = "" // City name
    val main: Main = Main()
    val weather: List<Weather> = emptyList()
    val wind: Wind = Wind()
}

// Data class for the "main" section of the weather response (temperature and humidity).
data class Main(
    val temp: Double = 0.0,        // Current temperature
    val humidity: Int = 0          // Humidity percentage
)

// Data class for the weather section of the response (description and icon).
data class Weather(
    val description: String = "",  // Weather description (e.g., "clear sky")
    val icon: String = ""          // Weather icon code
)

// Data class for the wind section of the weather response (wind speed).
data class Wind(
    val speed: Double = 0.0        // Wind speed in meters per second
)