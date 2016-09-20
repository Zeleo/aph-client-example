package com.bjond.aph.client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import lombok.Getter;

public class APHClient {
	
	@Getter private static APHClientCommands commands;
	private static JCommander commander;

	public static void main(String[] args) {
		try {
			commands = new APHClientCommands();
			commander = new JCommander(commands, args);
			new APHClientExecutor(commander, commands).execute();
		}
		catch(ParameterException pex) {
			System.out.println(pex.getMessage());
			if(commander != null) {
				commander.usage();
			}
			else {
				commands.setHelp(true);
				commander = new JCommander(commands);
				commander.usage();
			}
		}
	}

}
