# Korner

Smooth rounded corners and squircles for Compose Multiplatform.

Korner is a lightweight Kotlin Multiplatform library for creating beautiful smooth corner shapes (superellipses/squircles) with customizable radius and smoothness values for each individual corner.

Supports:
- Android
- iOS
- Desktop
- WASM/Web

Bashed on the original smooth corner implementation from:
https://github.com/racra/smooth-corner-rect-android-compose

---

## Features

- Smooth iOS-style corners
- Individual corner radius support
- Custom smoothness per corner
- Compose Multiplatform compatible
- Lightweight and easy to use
- Works with any Compose UI component

---

## Installation

### Gradle

```kotlin
repositories {
    mavenCentral()
}
```

```kotlin
dependencies {
    implementation("io.github.dev778g-me:korner:1.0.0")
}
```

---

## Usage

### Basic Smooth Corner Shape

```kotlin
Box(
    modifier = Modifier
        .size(200.dp)
        .background(
            color = Color(0xFFF6EABE),
            shape = SmoothCornerShape(
                radius = 32.dp,
                smoothness = 100
            )
        )
)
```

---

### Individual Corner Configuration

```kotlin
Box(
    modifier = Modifier
        .size(200.dp)
        .background(
            color = Color(0xFFF6EABE),
            shape = SmoothCornerShape(
                topStartRadius = 40.dp,
                topStartSmoothness = 100,

                topEndRadius = 12.dp,
                topEndSmoothness = 60,

                bottomEndRadius = 40.dp,
                bottomEndSmoothness = 100,

                bottomStartRadius = 12.dp,
                bottomStartSmoothness = 60
            )
        )
)
```

---

## Why Korner?

Traditional rounded rectangles use simple circular arcs.

Korner generates smooth superellipse-style curves that look more natural and modern, similar to the corners used in iOS and modern design systems.

---


---
## Attribution

Korner is based on and adapted from:
https://github.com/racra/smooth-corner-rect-android-compose

Original work copyright (c) racra
Licensed under the MIT License.


