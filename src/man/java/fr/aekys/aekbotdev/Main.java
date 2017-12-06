package fr.neutronstars.botdiscord;

import fr.aekys.commands.*;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

/**
 * Creating by Aven at 06.12.2017
 *
**/

public class Main
{
	private static final HashMap commands = new HashMap();
	private static final CommandParser parser = new ComamndParser();
	private final JDA jda;	
	public JDA getJda() {
		return jda;
	}
	
	public static void main(String[] args) {
		try {
		jda = new JDABuilder(AccountType.BOT).setToken("Token").buildBlocking();
		jda.addEventListener(new BotListener());
		jda.getPresence().setGame(Game.of("=help | Bot test | 1 serveur"));
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		} catch (LoginException | IllegalArgumentException | RateLimitedException e) {
			e.printStackTrace();
		}
	}
	
    public static void handleCommand(CommandParser.CommandContainer cmd)
    {
        if(commands.containsKey(cmd.invoke))
        {
            boolean safe = ((Command)commands.get(cmd.invoke)).called(cmd.args, cmd.event);

            if(safe)
            {
                ((Command)commands.get(cmd.invoke)).action(cmd.args, cmd.event);
                System.out.println(cmd.event.getAuthor().getName()+cmd.event.getAuthor().getDiscriminator()+" a exécuté la commande "+cmd.event.getMessage().getContent()+".");
                ((Command)commands.get(cmd.invoke)).executed(safe, cmd.event);
            } else {
                ((Command)commands.get(cmd.invoke)).executed(safe, cmd.event);
            }
        }
    }
}
