# Telegram BOT for Graph-based Context-aware Holistic Recommendations

This is the code of Telegram BOT in order to make usable our Graph-based, through by API.

This BOT is currently hosted on Heroku at:
```
https://gr-hor-bot.herokuapp.com
```

### Prerequisites

This is the official API provided by Telegram to built a BOT.
Add this dependency in your pom.xml file.
```
<dependencies>
        <dependency>
            <groupId>org.telegram</groupId>
            <artifactId>telegrambots</artifactId>
            <version>4.1.2</version>
        </dependency>
 </dependencies>
```

## How it Works

The system will ask to the user to choose a set of context situations through by a menu.
When it's finished data-entry, user can tap on "End" button.

After that, the system show the choices to the user with a message and offer a command (called "/recommendations") to obtain suggestions.
We define 3 stereotypes and the user can choose among them:
* /healthy
* /unhealthy
* /family

The suggestions are accompanied by URL of places.

## API Service

This is the Http Request, through by API, with which BOT ask informations to the server
```
HttpPost request = new HttpPost("https://graph-recommender.herokuapp.com/recommendation/post");
```

## Built With

* Intellij IDEA
* Telegram API

## Authors

* **Marco Mirizio**
* **Federico Impellizzeri**


## Acknowledgments

* Prof. **Cataldo Musto**
