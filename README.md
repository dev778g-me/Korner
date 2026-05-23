# Korner

Smooth rounded corners and squircles for Compose Multiplatform.

<p align="center">
  <img
    src="https://raw.githubusercontent.com/racra/smooth-corner-rect-android-compose/master/art/header.png"
    width="100%"
    alt="Korner Preview"
  />
</p>

Korner provides beautiful iOS-style smooth corner shapes and superellipses for Compose Multiplatform with fully customizable radius and smoothing values per corner.

Supports:
- Android
- iOS
- Desktop
- WASM/Web

Based on the original smooth corner implementation by racra:
https://github.com/racra/smooth-corner-rect-android-compose

---

# Features

- Smooth iOS-style corners
- Superellipse / squircle rendering
- Per-corner radius customization
- Per-corner smoothing customization
- Type-safe smoothing API
- Compose Multiplatform support
- Works with any Compose UI component
- Lightweight and allocation-friendly

---

# Why Korner?

Traditional rounded rectangles use simple circular arcs.

Korner generates smooth superellipse-style curves with more natural transitions and better visual balance — similar to modern design systems used in iOS and contemporary UI frameworks.

Compared to standard rounded corners, smooth corners:
- feel more organic
- reduce visual sharpness
- improve large-radius aesthetics
- create cleaner modern UI surfaces

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

# Basic Usage

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

# Per-Corner Configuration

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

Korner uses a type-safe `CornerSmoothing` API:

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
|---|---|
| `Subtle` | `25` |
| `Balanced` | `50` |
| `Smooth` | `75` |
| `Continuous` | `100` |

Custom values are also supported:

```kotlin
CornerSmoothing(65)
```

Valid range: `0..100`




---

# Attribution

Korner is adapted from:
https://github.com/racra/smooth-corner-rect-android-compose

Original work copyright © racra.

Licensed under the MIT License.
