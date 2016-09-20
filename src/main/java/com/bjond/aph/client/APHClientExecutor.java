package com.bjond.aph.client;

import com.beust.jcommander.JCommander;

public class APHClientExecutor {
	
	private APHClientCommands commands;
	private JCommander commander;
	
	public APHClientExecutor(JCommander pCommander, APHClientCommands pCommands) {
		commands = pCommands;
		commander = pCommander;
	}
	
	public void execute() {
		if(commands.isHelp()) {
			commander.usage();
		}
	}
}
