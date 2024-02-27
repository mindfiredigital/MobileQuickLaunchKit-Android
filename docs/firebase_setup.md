### Firebase Setup:

To integrate Firebase into your Android project, follow these steps:

1. Navigate to **Tools** -> **Firebase**.
   ![Firebase Option in Tools](screenShots/firebase_option_tools.png)

2. In the Firebase Assistant, expand **Authentication** and select
   **Authenticate using Google**.
   ![Firebase Assistant](screenShots/firebase_assistant.png)

3. Click on **Connect to Firebase**.
   ![Firebase Android App Creation](screenShots/connect_to_firebase.png)

4. You will be redirected to the Firebase console. Select your existing project
   or create a new one.

5. Once your project is selected, Firebase will create a new Android app within
   it.

6. After completion, you'll receive a confirmation that your Firebase Android
   app has been successfully created.

7. Click on **Connect** to proceed.
   ![Connect to Project](screenShots/connect_to_project.png)

8. You'll be notified that your Android Studio project is now connected to your
   Firebase Android app.
   ![Project Connected](screenShots/project_connected.png)

9. Return to your project in Android Studio. In the Firebase Assistant, click on
   **Add the Firebase Authentication SDK to your app**.
   ![Add Firebase SDK](screenShots/add_firebase_sdk.png)

10. Click on **Accept Changes**.

11. ![Apply Changes](screenShots/apply_changes.png)

12. Now, go to the Firebase console, click on **Authentication** from the
    left-side panel.

13. Click on **Add new provider**.
    ![Enable Google](screenShots/enable_google.png)

14. From **Additional providers**, select **Google** and enable it.
    ![Enabling Google Firebase](screenShots/enabling_google_firebase.png)

15. After enabling Google, go to **Web SDK configuration** and copy the Web
    client ID. ![Copy Web Client ID](screenShots/copy_web_client_id.png)

16. Paste the copied Web client ID in your config file
