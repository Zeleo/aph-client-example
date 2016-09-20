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

import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;

import lombok.Data;

@Data
public class APHClientCommands {
	
	@Parameter(names={"--url", "-u"}, description="The URL (including port) of the adapter.", required=false)
	private String url = "https://axis-develop.bjondhealth.com/bjond-axis-adapter/services/adapter";
	
	@Parameter(names={"--key", "-k"}, description="The JWT encryption key.", required=true)
	private String key;
	
	@Parameter(names={"--audience", "-a"}, description="The JWT audience string.", required=false)
	private String audience = "Bjond, Inc";
	
	@Parameter(names={"--subject", "-s"}, description="The JWT subject string.", required=false)
	private String subject = "development";
	
	@Parameter(names={"--endpoint", "-e"}, description="The endpoint we will be calling.", required=true)
	private String endpoint;
	
	@DynamicParameter(names = "-D", description = "The key/value parameters for the json body of the request.")
	private Map<String, String> json = new HashMap<String, String>();
	
	@Parameter(names = "--help", help = true, description="Print this help message.")
	private boolean help;
	
	@Parameter(names={"--issuer", "-i"}, description="The JWT issuer string. This is usually hardcoded to the default value.", required=false)
	private String issuer = "Bjönd, Inc."; 
	
	@Parameter(names={"--clock-skew", "-c"}, description="The clock skew for an acceptable JWT token. Usually the default is fine.", required=false)
	private int clockSkew = 60;
}
