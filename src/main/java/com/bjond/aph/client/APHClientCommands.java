package com.bjond.aph.client;

import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;

import lombok.Data;

@Data
public class APHClientCommands {
	
	@Parameter(names={"--url", "-u"}, description="The URL (including port) of the adapter.", required=false)
	private String url = "https://axis-develop.bjondhealth.com/bjond-axis-adapter/";
	
	@Parameter(names={"--key", "-k"}, description="The JWT encryption key.", required=true)
	private String key;
	
	@Parameter(names={"--audience", "-a"}, description="The JWT audience string.", required=false)
	private String audience = "development";
	
	@Parameter(names={"--subject", "-s"}, description="The JWT subject string.", required=false)
	private String subject = "Bjond, Inc";
	
	@Parameter(names={"--endpoint", "-e"}, description="The endpoint we will be calling.", required=true)
	private String endpoint;
	
	@DynamicParameter(names = "-D", description = "The key/value parameters for the json body of the request.")
	private Map<String, String> json = new HashMap<String, String>();
	
	@Parameter(names = "--help", help = true, description="Print this help message.")
	private boolean help;
	
}
