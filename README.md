# MobileQuickLaunchKit Documentation

## Overview

MobileQuickLaunchKit is a mobile app development kit built using Jetpack Compose
and follows the Clean architecture pattern with a modular approach. It aims to
provide a quick and efficient setup for common functionalities required in
Android app development. The app is divided into multiple modules, each serving
a specific purpose.

## Table of contents

1. [Modules](./docs/modules.md)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Mockoon Setup Instructions](./docs/mockoon_setup.md)
5. [API Setup](./docs/apis_setup.md)
6. [Troubleshoot](#troubleshoot)

## Installation

### Note

- You are welcome to utilize this repository for your professional endeavors and make adjustments to
  modules or the main app as necessary.
- If you prefer to include only specific modules, kindly follow the steps below before integrating
  them into your project.
- HILT is an integral part of this repository. Therefore, it is essential to configure HILT in your
  own project. Refer to the provided instructions [here](./docs/hilt_setup.md) after
  completing the initial setup.
- Firebase is utilized for enabling Google sign-in functionality. To integrate Firebase into your
  project, please follow the instructions provided [here](./docs/firebase_setup.md) after
  completing the initial setup..

### Prerequisites

- Install the latest Android Studio (should be 3.0.1 or later).
- Clone the repo
- Add the config.json file on your app->src->main->assets->config.json otherwise, you'll see a message saying "Please provide config file."
```json
{
  "BASE_URL": "http://10.0.2.2:3001/api/v1/",
  "Google_WEB_CLIENT_ID": "your_google_web_client_id"
}
```

### Steps for Setup

1. **Create a New Android Empty Activity Project:**

- Start by creating a new Android project with an Empty Activity template.

2. **Run the Project and Check for Issues:**

- Run the project and ensure there are no issues.

3. **Create a New Directory named: buildSrc:**

- It is crucial to create a new directory on the root level with exactly the
  same name.
- Create a new file named `.gitignore` inside buildSrc.
- Include: `.gradle` and `build`
- Remove everything from the project level `build.gradle.kts`. (You will get
  this error: Error resolving plugin [id: 'com.android.application', version:
  '8.2.1', apply: false] The request for this plugin could not be satisfied
  because the plugin is already on the classpath with an unknown version, so
  compatibility cannot be checked.)
- Create a new file named `build.gradle.kts` inside buildSrc and include the
  below code:

```kotlin
plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    implementation("com.android.tools.build:gradle:8.1.1")
    implementation("com.squareup:javapoet:1.13.0")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "18"
}
```

- Sync the project.
- Create a new directory `src/main/kotlin`.
- Include all files from `buildSrc->src->main->java` from the
  QuickLaunchMobileKit repository.

5. **Modify Project Level `build.gradle.kts`:**

- Replace with:

```kotlin
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.hiltAgp)
    }
}
```

6. **Modify App Level `build.gradle.kts`:**

- Include kotlin("kapt") and id("com.google.dagger.hilt.android") inside plugin
```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")

}
```

- Inside `android -> defaultConfig` replace:

```kotlin
defaultConfig {
    applicationId = "com.example.demo_test"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
        useSupportLibrary = true
    }
}
```

With:

```kotlin
defaultConfig {
    applicationId = ProjectConfig.appId
    minSdk = ProjectConfig.minSdk
    targetSdk = ProjectConfig.targetSdk
    versionCode = ProjectConfig.versionCode
    versionName = ProjectConfig.versionName
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
        useSupportLibrary = true
    }
}
```
- Also, set `compileSdk = 34` to `compileSdk = ProjectConfig.compileSdk`.

7. **Replace `composeOptions`:**

- Replace:

```kotlin
composeOptions {
    kotlinCompilerExtensionVersion = “1.4.6”
}
```

With:

```kotlin
composeOptions {
    kotlinCompilerExtensionVersion = Versions.composeCompiler
}
```

8. **Replace Dependencies:**

- Replace dependencies with:

```kotlin
dependencies {
    Dependencies.coreKtx
    Dependencies.androidLifecycleRuntime
    compose()
    unitTesting()
}
```

9. **Replace `compileOptions and kotlinOptions `:**

- Replace:

```kotlin
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
kotlinOptions {
    jvmTarget = "1.8"
}
```

With:

```kotlin
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}
kotlinOptions {
    jvmTarget = "18"
}
```

9. **Sync Your Project:**

- Sync your project to apply the changes.

10. **Including Modules:**

- To work with other modules, include `core` and `core_ui` modules first.
- Copy the required module's folder from `MobileQuickLaunchKit` and paste it
  into your project.
- Include the module inside the project level `settings.gradle.kts`:

```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "test4"
include(":app")
include(":core")
include(":core_ui")
/// Other modules
include(":onboarding:onboarding_presentation")
include(":auth:auth_data")
include(":auth:auth_domain")
include(":auth:auth_presentation")
include(":home")
include(":settings")
include(":sample")
include(":utility")
```

- Sync the project.
- Inside your main app level `build.gradle`, add the required module.

```kotlin
dependencies {
    Dependencies.coreKtx
    Dependencies.androidLifecycleRuntime
    compose()
    unitTesting()
    hilt()
    hiltTesting()
    retrofit()
    constraintLayout()
    coilImage()
    timber()
    composeNavigation()

    coreUI()
    core()
    onboardingPresentation()
    authData()
    authDomain()
    authPresentation()
    home()
    settings()
    utilities()
}
```

- Sync the project, and you are good to go.

---

## Usage

### 1. Initialize Theme:

```kotlin
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val window = rememberWindowSizeClass()
            MQLKTheme(
                windowSizeClass = window,
            ) {
                MyApp()
            }
        }
    }
}
```

Here `MQLKTheme` has `lightTheme` and `darkTheme` parameters where you can pass
your custom theme data as well.

```kotlin
val lightTheme: MQLKThemeModel = MQLKThemeModel(
    primary = Red,
    onPrimary = DefaultTextColor, // Text Color
    secondary = DarkGrey,
    tertiary = Pink40,
    background = Color.White,
    surface = Color.White,
)

val darkTheme: MQLKThemeModel = MQLKThemeModel(
    primary = LightPink,
    onPrimary = LightGrey,
    secondary = MedGrey,
    tertiary = Pink40,
    background = DarkBlue,
    surface = Ebony
)
```

### 2. Importing Screens:

You can easily import screens from different modules and create your own
nav-graph for navigation.

```kotlin
@Composable
fun SampleMQLKNavigationGraph(
    navController: NavHostController,
    startLocation: String,
    drawerState: DrawerState
) {
    NavHost(navController = navController, startDestination = startLocation) {
        // Define composable destinations and their corresponding routes
        composable(MQLKScreens.SplashScreen.route) {
            val viewModel: SplashScreenViewModel = hiltViewModel()
            MQLKSplashScreenUI(navController, viewModel)
        }

        composable(MQLKScreens.LoginScreen.route) {
            MQLKLoginScreen(navController, navigateTo = {
                navController.navigate(it)
            })
        }

        composable(MQLKScreens.SignupScreen.route) {
            MQLKSignUpScreen(navController, {
                navController.navigate(it)
            })
        }

        composable(MQLKScreens.ForgotPasswordScreen.route) {
            MQLKForgotPasswordScreen(navController, {
                navController.navigate(it)
            })
        }

        composable(MQLKScreens.OTPVerification.route) {
            MQLKOTPVerificationScreen(navController, {
                navController.navigate(it)
            })
        }

        composable(MQLKScreens.SetNewPassword.route) {
            MQLKSetNewPasswordScreen(navController, {
                navController.navigate(it)
            })
        }

        composable(MQLKScreens.HomeScreen.route) {
            MQLKHomeScreen(drawerState)
        }

        composable(MQLKScreens.SettingScreen.route) {
            MQLKSettingsScreen(navController)
        }

        composable(MQLKScreens.EditProfileScreen.route) {
            MQLKEditProfileScreen(navController)
        }

        composable(MQLKScreens.ChangePasswordScreen.route) {
            MQLKChangePasswordScreen(navController)
        }

        composable(
            MQLKScreens.WebView.route,
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
            })
        ) {
            MQLKWebView(navController, it.arguments?.getString("url"))
        }
    }
}
```

Adjust these composable functions based on your specific navigation
requirements.

### 3. MQLKModelNavigationDrawerWrapper Composable

We have the `MQLKModelNavigationDrawerWrapper` composable available for
integrating a drawer into your app. Follow these steps:

1. **Wrap at Top Composable:**
    - Wrap this at the very top of your composable hierarchy.

2. **Pass Drawer State:**
    - Pass your drawer state as a parameter.

3. **Include Composable Items:**
    - Include all composable items that you want to show in your drawer.

4. **Use to Open or Close Drawer:**
    - Use this to open or close the drawer.

Example:

```kotlin
val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

MQLKModelNavigationDrawerWrapper(
    drawerState,
    items = listOf(
        {
            NavigationDrawerItem(
                label = { Text(text = "Drawer Item") },
                selected = false,
                onClick = { MQLKUtilities.sharePlainText(context = context) },
                badge = {
                    Icon(Icons.Default.Email, null)
                },
                icon = {
                    Icon(Icons.Default.Phone, null)
                },
            )
        },
        {
            NavigationDrawerItem(
                label = { Text(text = "Drawer Item") },
                selected = false,
                onClick = { /*TODO*/ }
            )
        },
        {
            NavigationDrawerItem(
                label = { Text(text = "Drawer Item") },
                selected = false,
                onClick = { /*TODO*/ }
            )
        },
    )
) {
    // YourTopComposable()
    YourTopComposable()
}
```

### 4. MQLKBottomNavBar Composable

We have the `MQLKBottomNavBar` composable available for including a bottom
navigation bar in your app. Follow these steps:

1. **Pass Navigation Items and NavController:**
    - Pass your navigation items and NavController as parameters.

Example:

```kotlin
val navController = rememberNavController()
val navItems = listOf(
    MQLKNavItem(
        label = "Home", route = MQLKScreens.HomeScreen.route, icon = Icons.Default.Home
    ),
    MQLKNavItem(
        label = "Settings",
        route = MQLKScreens.SettingScreen.route,
        icon = Icons.Default.Settings
    ),
)

Scaffold(
    modifier = Modifier.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        focusManager.clearFocus()
    },
    containerColor = MaterialTheme.colorScheme.background,
    bottomBar = {
        val items = navItems.map {
            it.route
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.hierarchy?.first()?.route

        // Responsible for showing the bottom navigation bar
        if (currentRoute !in items) return@Scaffold

        MQLKBottomNavBar(
            items = navItems,
            navController = navController
        ) {
            navController.navigate(it.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
) { it ->
    SampleMQLKNavigationGraph(navController, MQLKScreens.SplashScreen.route, drawerState)
}
```

In this example, `navController` is created using `rememberNavController()` and
`navItems`represents the list of navigation items with labels, routes, and
icons. Adjust the items, routes, and actions based on your app's navigation
requirements.

Please go through Sample module to see all the implementation of all composables
and including modules

### 5. Utility

This section outlines various utility functions designed to streamline your workflow.

1. **MQLKDates**

    1. **getFormattedDateString**: This function accepts a `LocalDateTime` object and a `dateFormatter` and returns the date in the specified format as a string.

       **Example:**
       ```kotlin
       val resp = MQLKDates.getFormattedDateString(LocalDateTime.now(), "MM-DD-Y")
       print(resp)
       // Output: "02-40-2024"
       ```

    2. **changeDateFormat**: Use this function to convert the input date from one format to another. It requires the `inputDate` as a string, `inputFormat`, and `outputFormat`, returning the date in the desired format.

       **Example:**
       ```kotlin
       val resp = MQLKDates.changeDateFormat("2024-02-09T13:58:12.727283","yyyy-MM-dd'T'HH:mm:ss.SSSSSS","YY-MM-dd")
       print(resp)
       // Output: "24-02-09"
       ```

    3. **getFormattedTime**: This function provides flexibility in formatting time. It accepts the format of type `TimeFormat` (an enum) and `currentTime` as a `LocalDateTime` object, returning the formatted time in either 12-hour or 24-hour notation.

       **Example:**
       ```kotlin
       val resp = MQLKDates.getFormattedTime(MQLKDates.TimeFormat.TWENTY_FOUR_HOUR, LocalDateTime.now())
       print(resp)
       // Output: "02:01:18 pm" (12 hr) or "14:01:31" (24 hr)
       ```

    4. **parseDateStringToDate**: This function parses a date string based on the specified format (`inputFormat`) and returns a `LocalDateTime` object.

       **Example:**
       ```kotlin
       val resp = MQLKDates.parseDateStringToDate("2024-02-09T13:58:12.727283","yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
       print(resp)
       // Output: "2024-02-09T13:58:12.727283" (LocalDateTime object)
       ```

2. **MQLKValidations**

   This document provides a detailed overview of the functions available in the `MQLKValidations` class. These functions are written in Kotlin and can be used for various validation purposes in your application.

    - **isValidNumericInput**

      **Description:** This function determines if the provided string is numeric or not.

      **Usage:**
      ```kotlin
      val resp = MQLKValidations.isValidNumericInput("435345.43")
      print(resp)
      // Output: true
      ```

    - **isValidEmailOrPhoneNumber**

      **Description:** This function determines if the provided string is a valid email address or phone number.

      **Usage:**
      ```kotlin
      val resp = MQLKValidations.isValidEmailOrPhoneNumber("demo@mail.com")
      val resp2 = MQLKValidations.isValidEmailOrPhoneNumber("9988776655")
      println(resp)
      println(resp2)
      // Output:
      // true
      // true
      ```

    - **isPasswordMatching**

      **Description:** This function determines if the provided strings match and return `true` or `false`.

      **Usage:**
      ```kotlin
      val resp = MQLKValidations.isPasswordMatching("Test@123", "Test@123")
      val resp2 = MQLKValidations.isPasswordMatching("Test@1243", "Test@123")
      println(resp)
      println(resp2)
      // Output:
      // true
      // false
      ```

    - **isValidEmail**

      **Description:** This function determines if the provided string is a valid email address.

      **Usage:**
      ```kotlin
      val resp = MQLKValidations.isValidEmail("demo@mail.com")
      val resp2 = MQLKValidations.isValidEmail("demouser@demo")
      println(resp)
      println(resp2)
      // Output:
      // true
      // false
      ```

    - **isValidPhoneNumber**

      **Description:** This function determines if the provided string is a valid phone number.

      **Usage:**
      ```kotlin
      val resp = MQLKValidations.isValidPhoneNumber("9988776666")
      val resp2 = MQLKValidations.isValidPhoneNumber("33224433")
      println(resp)
      println(resp2)
      // Output:
      // true
      // false
      ```

    - **isValidURL**

      **Description:** This function determines if the provided string is a valid URL.

      **Usage:**
      ```kotlin
      val resp = MQLKValidations.isValidURL("http://api.demo.com")
      val resp2 = MQLKValidations.isValidURL("htp:/api.com")
      println(resp)
      println(resp2)
      // Output:
      // true
      // false
      ```

    - **isStrongPassword**

      **Description:** This function determines if the provided string is a strong password or not.

      **Usage:**
      ```kotlin
      val resp = MQLKValidations.isStrongPassword("Test@123#")
      val resp2 = MQLKValidations.isStrongPassword("TestDemo")
      println(resp)
      println(resp2)
      // Output:
      // true
      // false
      ```

    - **validateCreditCardNumber**

      **Description:** This function determines if the provided string is a valid credit card number using the Luhn algorithm.

      **Usage:**
      ```kotlin
      val resp = MQLKValidations.validateCreditCardNumber("378282246310005")
      val resp2 = MQLKValidations.validateCreditCardNumber("078282146310005")
      println(resp)
      println(resp2)
      // Output:
      // true
      // false
      ```



## Troubleshoot

- If you encounter any errors related to the Java version, similar to the
  [this](./docs/screenShots/java_version_error.png), please follow the steps
  below:

1. Go to **Settings**.
2. Navigate to **Gradle**.
3. Change the Gradle JDK to version 18 (18.0.2).
4. Click **Apply**.
5. Click **OK**.

<img src="./docs/screenShots/gradle_update.png" >
