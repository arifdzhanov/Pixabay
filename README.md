# Image Search Application

## Overview

This Android application, developed as part of a coding challenge, enables users to search and view images from Pixabay. Utilizing Clean Architecture and modern Android development practices, the app is designed to be efficient, responsive, and user-friendly, adhering to best practices such as DRY and SOLID principles.


## Features

### Responsive Image Search

Users can search for images using keywords. Includes a debounce feature to optimize API calls.

### Adaptive Layout

Displays images in a grid that adjusts between two and three columns based on device orientation.

### Offline Support

Caches images for offline viewing and intelligently handles internet connectivity changes to refresh data.

### Error Handling

Implements nuanced error handling strategies for different scenarios, enhancing user engagement.

### Dynamic Image Aspect Ratios

Calculates and applies aspect ratios for images before loading, preserving visual integrity.

## Detailed Features

### Offline Support

- **Local Data Caching**: Uses Room to cache images and data locally for offline access.
- **Smart Data Refresh**: Monitors connectivity and refreshes data automatically when internet is restored.
- **Error Handling with Offline Data**: Provides cached data during connectivity issues, ensuring continuous access.

### Error Handling

- **Scenario-Specific UI Feedback**: Tailored messages and states for various error scenarios, including complete lack of data and partial availability.
- **Retry Mechanisms**: Actionable options for users to retry failed operations, improving user experience under error conditions.
- **Graceful Degradation**: Continues to serve cached data during errors, with options to retry, ensuring a seamless experience.

### Dynamic Image Aspect Ratios

- **Aspect Ratio Calculation**: Calculates aspect ratios based on image metadata to ensure images are displayed without distortion.
- **Responsive UI**: Maintains aesthetic integrity across screen sizes and orientations.
- **Performance Optimization**: Enhances performance by minimizing layout recalculations, especially in list views.

## Architecture

Structured into `data`, `domain`, and `presentation` modules, following Clean Architecture to ensure code is organized, decoupled, and easily maintainable.

## Technology Stack

- **Kotlin**: For development.
- **MVVM and Jetpack Compose**: For the UI layer.
- **Coroutines**: For asynchronous tasks.
- **Room**: For local data persistence.
- **Retrofit**: For network requests.
- **Coil**: For image loading.

## Screenshots

[//]: # (<p align="center">)

[//]: # (  <img src="screenshots/screenshot1.png" width="200"/>)

[//]: # (  <img src="screenshots/screenshot2.png" width="200"/>)

[//]: # (  <img src="screenshots/screenshot3.png" width="200"/>)

[//]: # (  <img src="screenshots/screenshot4.png" width="200"/>)

[//]: # (</p>)