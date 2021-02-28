package io.github.samuel21119.minecord.botHandler;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.event.message.MessageCreateEvent;

public class botHandler {
    private static boolean init = false;
    private static Channel channel = null;
    private static long channelId = 0;
    private static long consoleChannelId = -1;
    private static DiscordApi api;
    public botHandler(String token, long channel_id, boolean console, long consoleChannel_Id) {
        if (init)
            return;
        api = new DiscordApiBuilder().setToken(token).login().join();
        init = true;
        channelId = channel_id;
        channel = (Channel) api.getChannelById(channel_id).orElse(null);
        consoleChannelId = console ? consoleChannel_Id : -1;
        api.addMessageCreateListener(botHandler::onMessageCreate);
    }
    public static void Disable() {
        if (!init)
            return;
        api.getMessageCreateListeners().forEach((i) -> api.removeListener(i));
        api.disconnect();
        init = false;
        channel = null;
        consoleChannelId = -1;
    }
    public static void Send(String player, String msg) {
        if (!init || channel == null)
            return;
        new MessageBuilder()
                .append("<")
                .append(player, MessageDecoration.BOLD)
                .append("> ")
                .append(msg)
                .send((TextChannel) channel);
    }

    private static void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessage().getAuthor().isRegularUser()) {
            if (event.getChannel().getId() == channelId) {
                String player = event.getMessageAuthor().getDisplayName();
                String msg = event.getReadableMessageContent();
                new broadcastMinecraftChannel(player, msg);
            } else if (event.getChannel().getId() == consoleChannelId) {
                dispatchMinecraftCommand.Run(event.getMessageContent());
            }
        }
    }
}
