package fr.neutronstars.botdiscord;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import fr.neutronstars.botdiscord.command.CommandMap;
import fr.neutronstars.botdiscord.event.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class BotDiscord implements Runnable{

	private final JDA jda;
	private final CommandMap commandMap = new CommandMap(this);
	private final Scanner scanner = new Scanner(System.in);
	
	private boolean running;
	
	public BotDiscord() throws LoginException, IllegalArgumentException, RateLimitedException {
		jda = new JDABuilder(AccountType.BOT).setToken("Token").buildAsync();
		jda.addEventListener(new BotListener(commandMap));
		jda.getPresence().setGame(Game.of("=help | Bot test | 1 serveur"));
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		System.out.println("Bot connected.");		
	}
	
	public JDA getJda() {
		return jda;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	@Override
	public void run() {
		running = true;
		
		while (running) {
			if(scanner.hasNextLine()) commandMap.commandConsole(scanner.nextLine());
		}
		
		scanner.close();
		System.out.println("Bot stopped.");
		jda.shutdown();
		commandMap.save();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		try {
			BotDiscord botDiscord = new BotDiscord();
			new Thread(botDiscord, "bot").start();
		} catch (LoginException | IllegalArgumentException | RateLimitedException e) {
			e.printStackTrace();
		}
	}
}
