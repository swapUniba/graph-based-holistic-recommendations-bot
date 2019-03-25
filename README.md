# Demo Telegram Bot for Graph-based Context-aware Holistic Recommendations

This is the code for the Telegram Bot used to demo our API built for the Graph-based Context-aware Holistic Recommendations.

This Bot is currently hosted on Heroku at:

```
https://gr-hor-bot.herokuapp.com/
```

### Prerequisites

Add the TelegramBots library dependencies in your pom.xml file.
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

Add the Bot on Telegram, the Bot username is @GrHOR_Bot .

The Bot will asks users to input their context or the context in which they would like to receive suggestions of places to go to, then it asks the users in which kind of person stereotype they feel like they better fit.

Then the Bot will use the data collected and make a POST request to our API service at :

```
https://graph-recommender.herokuapp.com/recommendation/post
```

The API endpoint will return the suggestions that the Bot then shows to the user.

## Built With

* Intellij IDEA
* Maven
* [TelegramBots](https://github.com/rubenlagus/TelegramBots)

## Authors

* **Marco Mirizio**
* **Federico Impellizzeri**


## Acknowledgments

* Prof. **Cataldo Musto**
