<div align="center">

# Phoenix

**A Kotlin Multiplatform + Compose Multiplatform sample app for Firebase Storage image uploads.**

Pick an image with the native photo picker on Android and iOS, upload it to Firebase Storage, and browse or delete stored objects, all from shared Compose code.

<br/>

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.21-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.11.0--rc01-4285F4?logo=jetpackcompose&logoColor=white)](https://www.jetbrains.com/compose-multiplatform/)
[![Android](https://img.shields.io/badge/Android-minSdk%2024-3DDC84?logo=android&logoColor=white)](https://developer.android.com)
[![iOS](https://img.shields.io/badge/iOS-supported-000000?logo=apple&logoColor=white)](https://developer.apple.com/ios/)
[![AGP](https://img.shields.io/badge/AGP-9.1.1-02303A?logo=gradle&logoColor=white)](https://developer.android.com/build)

[![Firebase](https://img.shields.io/badge/Firebase-BoM%2034.4.0-FFCA28?logo=firebase&logoColor=black)](https://firebase.google.com)
[![GitLive Firebase SDK](https://img.shields.io/badge/GitLive%20Firebase%20KMP-2.4.0-FF6F00?logo=firebase&logoColor=white)](https://github.com/GitLiveApp/firebase-kotlin-sdk)
[![Peekaboo](https://img.shields.io/badge/Peekaboo%20Image%20Picker-0.5.2-FF4088)](https://github.com/onseok/peekaboo)
[![Koin](https://img.shields.io/badge/Koin-4.1.1-F9A825)](https://insert-koin.io)
[![Coil 3](https://img.shields.io/badge/Coil-3.4.0-2196F3)](https://coil-kt.github.io/coil/)

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)
[![Code of Conduct](https://img.shields.io/badge/Code%20of%20Conduct-v2.1-ff69b4.svg)](CODE_OF_CONDUCT.md)

</div>

---

## About

**Phoenix** is a small, focused **Kotlin Multiplatform (KMP)** application built with **Compose Multiplatform**. It demonstrates a real, end-to-end **Firebase Storage** workflow from 100% shared code:

- **Pick an image** using the platform-native picker, the Android Photo Picker and the iOS `PHPicker`, via [Peekaboo](https://github.com/onseok/peekaboo).
- **Upload** the selected image to Firebase Storage and get back a public download URL.
- **List and delete** the objects stored in a folder.
- A clean **MVVM** structure with a repository layer, `ViewModel`, and Koin dependency injection.

It is designed as a reference or starter for anyone wiring Firebase Storage into a Compose Multiplatform app.

> Bootstrapped with the [Catylst KMP Starter Kit](https://github.com/Rohit-554/Catylst).

---

## Demo 
<img width="800" height="720" alt="Screenshot 2026-07-03 at 9 29 10 PM" src="https://github.com/user-attachments/assets/8ab14aae-9c5f-4136-932d-21935c561d03" />


---

## Tech Stack

| Concern | Library | Version |
|---|---|---|
| Language | [Kotlin](https://kotlinlang.org) | `2.3.21` |
| UI | [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/) | `1.11.0-rc01` |
| Design | [Material 3](https://m3.material.io) | `1.11.0-alpha06` |
| Build | Android Gradle Plugin | `9.1.1` |
| Backend | [Firebase BoM](https://firebase.google.com) | `34.4.0` |
| Firebase KMP SDK | [GitLive Firebase SDK](https://github.com/GitLiveApp/firebase-kotlin-sdk) (`common`, `auth`, `storage`) | `2.4.0` |
| Google Services plugin | `com.google.gms.google-services` | `4.5.0` |
| Image picker | [Peekaboo](https://github.com/onseok/peekaboo) | `0.5.2` |
| DI | [Koin](https://insert-koin.io) | `4.1.1` |
| Navigation | [Navigation 3](https://developer.android.com/guide/navigation) | `1.1.1` |
| Image loading | [Coil 3](https://coil-kt.github.io/coil/) | `3.4.0` |
| Serialization | kotlinx.serialization | `1.9.0` |
| Date/Time | kotlinx-datetime | `0.7.1` |
| Testing | kotlin-test, [Turbine](https://github.com/cashapp/turbine), [Kotest](https://kotest.io), [Mockative](https://mockative.io) | - |

**Platforms:** Android (supported), iOS (supported), Desktop (not yet), Web (not yet)

---

## Project Structure

```
Phoenix/
├── composeApp/                  # Shared KMP module (commonMain + androidMain + iosMain)
│   └── src/commonMain/kotlin/io/jadu/phoenix/
│       ├── di/                  # Koin modules
│       ├── navigation/          # Navigation3 setup
│       ├── storage/             # StorageRepository, StorageData (expect/actual)
│       └── ui/
│           ├── screens/         # StorageScreen (Compose UI)
│           └── viewmodel/       # StorageViewModel (MVVM state)
├── androidApp/                  # Android application entry point
├── iosApp/                      # iOS application (Xcode project)
├── gradle/libs.versions.toml    # Version catalog (single source of truth)
└── settings.gradle.kts
```

---

## Getting Started

### Prerequisites

- **JDK 17+**
- **Android Studio** (Ladybug or newer) or **IntelliJ IDEA**
- **Xcode 15+** (for iOS builds, macOS only)
- A **Firebase project**. [Create one here](https://console.firebase.google.com)

### 1. Clone the repo

```bash
git clone https://github.com/<your-org>/Phoenix.git
cd Phoenix
```

### 2. Set up Firebase

1. In the [Firebase Console](https://console.firebase.google.com), create a project.
2. Enable **Storage** (Build, then Storage, then Get started).
3. Register your apps and download the config files:
   - **Android**: `google-services.json`, place in `androidApp/`
   - **iOS**: `GoogleService-Info.plist`, add to the `iosApp/` Xcode target.

> Note: These config files are **git-ignored** on purpose (see `.gitignore`). Never commit them.

For quick local testing, you can relax the Storage rules (do not use in production):

```
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /{allPaths=**} {
      allow read, write: if true;   // TEST ONLY, lock this down before shipping
    }
  }
}
```

### 3. Configure `local.properties`

Copy the example and fill in your SDK path:

```bash
cp local.properties.example local.properties
```

### 4. Run

**Android**

```bash
./gradlew :androidApp:assembleDebug
# or press Run in Android Studio
```

**iOS**

Open `iosApp/iosApp.xcodeproj` in Xcode and run, or use the KMP run configuration from Android Studio.

---

## How the upload flow works

1. `StorageScreen` launches the native picker via `rememberImagePickerLauncher` (Peekaboo).
2. The picker returns the image as a `ByteArray`, passed to `StorageViewModel.uploadImage()`.
3. `StorageRepository.upload()` calls the GitLive Firebase Storage SDK:
   ```kotlin
   val ref = storage.reference(path)
   ref.putData(storageDataOf(byte))
   return ref.getDownloadUrl()
   ```
4. On success the UI shows the download URL and refreshes the object list.

---

## Contributing

Contributions are welcome. Please read the **[Contributing Guide](CONTRIBUTING.md)** and our **[Code of Conduct](CODE_OF_CONDUCT.md)** before opening a PR.

See the list of people who have helped in **[CONTRIBUTORS.md](CONTRIBUTORS.md)**.

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---
<div align="center">

Project Generated via CATYLST KMP STARTER - Android Studio Plugin

Made with ❤️ by Jadu

</div>
