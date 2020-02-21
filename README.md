## Notifier

This app can receive a webhook notification from Rundeck and display it to a user.

### To Run the app

./gradlew run

Navigate to: http://localhost:8085/demo.html

### Configuration

The app is configured by default to run on port 8085.
To change the listening port open the file src/main/resources/application.yml and change the port.

### Rundeck Webhook Notification

Point your webhook notification to the url: `http://localhost:8085/notify`

The app will mark success triggers as green, failures as red, and anything else as grey.