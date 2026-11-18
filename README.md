# Assignment 02 - Car Race

A JavaFX application that shows a slideshow of five cars and then runs an animated car race.
The project follows the Model-View-Controller pattern.

## Features

- Slideshow of five cars with a greeting and background music
- Cars drawn with JavaFX shapes (polygons, rectangles, circles and text)
- TranslateTransition moves the cars and RotateTransition spins the wheels
- Start, Pause and Reset buttons and a speed slider
- Winner announcement and final results in the message area

## Project structure

| Part | Class or file |
| --- | --- |
| Model | `Car`, `CarRaceModel` |
| View | `slideshow.fxml`, `race.fxml`, `style.css`, `CarView` |
| Controller | `SlideshowController`, `CarRaceController` |

The FXML files can be opened and edited with Scene Builder.

## How to run

Open the project in NetBeans and run it, or use Maven:

```
mvn clean javafx:run
```

Requires JDK 11 or newer.
