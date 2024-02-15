## Modules

### 1. Core

The Core module includes essential components for setting up a new project. This
includes:

- Retrofit: for handling API requests
- Internet connection: for checking network connectivity
- Datastore: for local data storage
- Interceptor: for intercepting and modifying network requests
- HILT setup: for dependency injection

### 2. CoreUI

CoreUI contains theming data, reusable widgets, and drawable resources required
for creating a consistent user interface across the app.

### 3. BuildSrc

BuildSrc module centralizes dependency setup, version names, and Gradle plugin
configurations, making it easier to manage dependencies across modules.

### 4. Utility

The Utility module houses various utility functions that can be shared across
different parts of the app.

### 5. OnBoarding

OnBoarding module handles the initial setup and includes a SplashScreen with all
necessary configurations.

### 6. Auth

Auth module encompasses features related to user authentication, including:

- Login
- Signup
- Forgot Password
- OTP Verification
- Set Password

All screens come with API integration for seamless authentication processes.

### 7. Setting

Setting module provides basic setting screens with common options such as:

- Edit Profile
- Change Password
- Privacy
- Logout
- Help
- About Us

### 8. Sample

The Sample module serves as a guide on how to use all the provided modules in
your app with examples.