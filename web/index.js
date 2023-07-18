const express = require('express');
const bodyParser = require('body-parser');
const webhook = require('webhook-discord');
const config = require('./config.json');

const hook = new webhook.Webhook(config.webhook);
const app = express();
app.use(bodyParser.urlencoded({ extended: false }));

app.post('/', (req, res) => {
    hook.send(
        new webhook.MessageBuilder()
            .setName(config.deviceName)
            .setColor('#FFFA00')
            //.setImage('Image url') // TODO: Replace with contact photo
            .setTime()
            .setTitle(req.body.IsCall == 'false' ? 'New Text!' : 'New Call!')
            .addField(`From: ${req.body.Number}`, req.body.Text)
    );

    res.send(`${req.body.Number} ${req.body.Text} ${req.body.IsCall}`);
});

app.listen(10000, () => {
    console.log('Application started on port 10000');
});
