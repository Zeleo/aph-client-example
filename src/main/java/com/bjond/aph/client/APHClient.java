/*  Copyright (c) 2016
 *  by Bjönd, Inc., Boston, MA
 *
 *  This software is furnished under a license and may be used only in
 *  accordance with the terms of such license.  This software may not be
 *  provided or otherwise made available to any other party.  No title to
 *  nor ownership of the software is hereby transferred.
 *
 *  This software is the intellectual property of Bjönd, Inc.,
 *  and is protected by the copyright laws of the United States of America.
 *  All rights reserved internationally.
 *
 */
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
