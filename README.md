# Discord-Android-Integration
Synchronize your Android phone's texts and calls with Discord.

# Compatibility
This project is currently compatible only with Android phones capable of sending valid SMS messages. It operates by dispatching data to a webhook, detailing both the phone number and the content of the message. The capability to detect incoming calls is a planned addition.

# Future Developments
- **iOS Compatibility**: The consideration for extending support to iOS devices is being planned, with the major hindrance being the existance of cost being involved in iOS app deployment.

- **Integration with Discord Bots**: To enhance functionality and flexibility, we are planning to transition from webhook to Discord Bot integrations in future updates.

- **Call Detection and Forwarding**: We are actively working on the functionality to detect incoming calls and transmit this information to the server.

# Server Setup
 - Install Node.js.
 - Rename the `config.example.json` file into `config.json` and set the webhook and device name fields.
 - Run `npm install -g pm2`.
 - Execute `pm2 start . --name "Discord-Android-Integration"`.

# Mobile Setup
 - TODO: Add instructions for mobile setup.