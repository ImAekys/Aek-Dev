package fr.aekys.aekbotdev.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * Creating by Aven at 06.12.2017
 *
**/

public class CommandParser
{
    public class CommandContainer
    {

        public final String raw;
        public final String beheaded;
        public final String splitBeheaded[];
        public final String invoke;
        public final String args[];
        public final MessageReceivedEvent event;
        final CommandParser this$0;

        public CommandContainer(String rw, String beheaded, String splitBeheaded[], String invoke, String args[], MessageReceivedEvent e)
        {
            super();
            this$0 = CommandParser.this;
            raw = rw;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            event = e;
        }
    }


    public CommandParser()
    {
    }

    public CommandContainer parse(String rw, MessageReceivedEvent e)
    {
        ArrayList split = new ArrayList();
        String raw = rw;
        String beheaded = raw.replaceFirst("=", "");
        String splitBeheaded[] = beheaded.split(" ");
        String as[];
        int j = (as = splitBeheaded).length;
        for(int i = 0; i < j; i++)
        {
            String s = as[i];
            split.add(s);
        }

        String invoke = (String)split.get(0);
        String args[] = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);
        return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args, e);
    }
}
