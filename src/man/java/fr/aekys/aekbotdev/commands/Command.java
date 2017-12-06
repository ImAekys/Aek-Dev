package fr.aekys.aekbotdev.commands;

public interface Command
{
    public boolean called(String args[], MessageReceivedEvent event);
    public void action(String args[], MessageReceivedEvent event);
    public void executed(boolean flag, MessageReceivedEvent event);
}
