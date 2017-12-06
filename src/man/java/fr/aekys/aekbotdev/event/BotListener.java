package fr.neutronstars.botdiscord.event;

import fr.aekys.aekbotdev.Main;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Creating by Aven at 06.12.2017
 *
**/

public class Listener extends ListenerAdapter
{
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Bot connected !");
        super.onReady(event);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!event.getAuthor().isBot() && event.getMessage().getContent().startsWith("="))
        {
            Main.handleCommand(Main.parser.parse(event.getMessage().getContent(), event));
        }
        super.onMessageReceived(event);
    }
}
