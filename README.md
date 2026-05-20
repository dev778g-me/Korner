# Korner

Smooth rounded corners and squircles for Compose Multiplatform.

Korner is a Compose Multiplatform library for creating beautiful smooth corner shapes (superellipses/squircles) with customizable radius and smoothness values for each individual corner.

Supports:
- Android
- iOS
- Desktop
- WASM/Web

Based on the original smooth corner implementation from:
https://github.com/racra/smooth-corner-rect-android-compose

---

# Features

- Smooth iOS-style corners
- Superellipse / squircle rendering
- Individual corner radius support
- Custom smoothness per corner
- Type-safe corner smoothing API
- Compose Multiplatform compatible
- Works with any Compose UI component

---

# Installation

## Gradle

```kotlin
repositories {
    mavenCentral()
}
```

```kotlin
dependencies {
    implementation("io.github.dev778g-me:korner:2.0.0")
}
```

---

# Usage

## Basic Smooth Corner Shape

```kotlin
Box(
    modifier = Modifier
        .size(200.dp)
        .background(
            color = Color(0xFFF6EABE),
            shape = AbsoluteSmoothCornerShape(
                cornerRadius = 32.dp,
                cornerSmoothing = CornerSmoothing.Continuous
            )
        )
)
```

---

## Individual Corner Configuration

```kotlin
Box(
    modifier = Modifier
        .size(200.dp)
        .background(
            color = Color(0xFFF6EABE),
            shape = AbsoluteSmoothCornerShape(
                topLeftRadius = 40.dp,
                topLeftCornerSmoothing = CornerSmoothing.Continuous,

                topRightRadius = 12.dp,
                topRightCornerSmoothing = CornerSmoothing.Balanced,

                bottomRightRadius = 40.dp,
                bottomRightCornerSmoothing = CornerSmoothing.Smooth,

                bottomLeftRadius = 12.dp,
                bottomLeftCornerSmoothing = CornerSmoothing.Subtle
            )
        )
)
```

---

# Corner Smoothing

Korner uses a type-safe `CornerSmoothing` API.

```kotlin
@JvmInline
value class CornerSmoothing(val percent: Int) {
    init {
        require(percent in 0..100) {
            "Corner smoothing must be between 0 and 100"
        }
    }

    companion object {
        val Subtle = CornerSmoothing(25)
        val Balanced = CornerSmoothing(50)
        val Smooth = CornerSmoothing(75)
        val Continuous = CornerSmoothing(100)
    }
}
```

## Presets

| Preset | Value |
|--------|--------|
| `CornerSmoothing.Subtle` | `25` |
| `CornerSmoothing.Balanced` | `50` |
| `CornerSmoothing.Smooth` | `75` |
| `CornerSmoothing.Continuous` | `100` |

You can also create custom smoothing values:

```kotlin
CornerSmoothing(65)
```

Valid values range from `0..100`.

---

# Why Korner?

Traditional rounded rectangles use simple circular arcs.

Korner generates smooth superellipse-style curves that look more natural and modern, similar to the corners used in iOS and modern design systems.

This gives:
- smoother transitions
- more organic shapes
- modern UI aesthetics
- better visual balance

---

# Platforms

Korner supports Compose Multiplatform targets:

| Platform | Supported |
|----------|------------|
| Android | ✅ |
| iOS | ✅ |
| Desktop | ✅ |
| WASM/Web | ✅ |

---

# Attribution

Korner is based on and adapted from:
https://github.com/racra/smooth-corner-rect-android-compose

Original work copyright (c) racra.

Licensed under the MIT License.
