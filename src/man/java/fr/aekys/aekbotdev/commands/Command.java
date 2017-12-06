package fr.aekys.aekbotdev.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Creating by Aven at 06.12.2017
 *
**/

public interface Command
{
    public boolean called(String args[], MessageReceivedEvent event);
    public void action(String args[], MessageReceivedEvent event);
    public void executed(boolean flag, MessageReceivedEvent event);
}
