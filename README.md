# MineCord

Connect minecraft and discord channel together.  

## Usage
1. Run server with plugin to generate default `config.yml`.  
2. Set `discord-token` to your discord bot token.
3. Set `channel-id` to the channel id you want to connect in discord.

## Library
- spigot-api: v1.16.5-R0.1-SNAPSHOT--
- javacord: v3.1.2

## Building
- Using maven
- Run `zip -d your.jar 'META-INF/.SF' 'META-INF/.RSA' 'META-INF/*SF'` if there're errors with "Invalid signature file digest for Manifest main attributes" when running the server.
