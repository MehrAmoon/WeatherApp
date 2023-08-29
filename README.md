# Multi language Weather App

This document provides an overview of the Weather App, its architecture, features, and usage instructions.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Architecture](#architecture)
- [Libraries Used](#libraries-used)
- [Design Patterns](#design-patterns)
- [Architectural Patterns](#architectural-patterns)
- [Weather API - OpenWeatherMap](#weather-api---openweathermap)
- [Android Keystore system](#android-keystore-system)


## Introduction

The Weather App is a modern mobile application designed to provide users with accurate weather information for their chosen cities. The app adopts best practices in software architecture and employs Clean Architecture, MVVM, Hilt, Room, and Compose to ensure a robust and maintainable codebase.

## Features

The Weather App boasts the following key features:

- View current weather data for selected cities.
- Add cities to a list of favorites for quick access.
- Get detailed weather forecasts for the upcoming days.
- Seamlessly navigate between different screens with Compose UI.
- Store favorite cities and weather data using Room database.
- Utilize Hilt for dependency injection, enhancing code modularity.

## Architecture

The Weather App is built on the principles of Clean Architecture, promoting a clear separation of concerns and high testability. The architecture includes the following layers:

- **Presentation Layer (Compose UI + MVVM):** This layer focuses on displaying data to users and receiving user interactions. The Model-View-ViewModel (MVVM) pattern is adopted, utilizing Jetpack Compose for UI rendering.

- **Domain Layer:** The domain layer contains the business logic and use cases, defining the core operations of the app. Use cases interact with the repositories to provide necessary data to the presentation layer.

- **Data Layer (Repository + Data Sources):** The data layer manages data retrieval and storage. It communicates with remote data sources through API services and local data sources using Room.

- **Dependency Injection (Hilt):** Hilt is used to manage and inject dependencies across the app. It simplifies the setup of components like ViewModels, repositories, and services.


## Libraries Used

- Android Architecture Components (ViewModel, StateFlow)
- Jetpack Compose
- Coroutines
- Hilt for dependency injection
- Retrofit for network communication

## Design Patterns

- `The Singleton pattern`  Object classes, the Singleton pattern is a creational design pattern that ensures that a class has only one instance and provides a global access point to this instance.
- `Factory Method pattern` An interface or abstract class that defines the type of objects that will be created. This interface or class defines the methods that all objects created by the factory method must implement.

## Architectural Patterns

- `Model-View-ViewModel (MVVM) pattern`
- `Single-activity architecture`
- `State hoisting`

## Weather API - OpenWeatherMap

Simple, fast and free multi language weather API from OpenWeatherMap you have access to current weather data, hourly, 5- and 16-day forecasts. 

## Android Keystore system

The Android Keystore system lets you store cryptographic keys in a container to make them more difficult to extract from the device.