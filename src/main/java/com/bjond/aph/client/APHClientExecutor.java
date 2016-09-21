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

import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jose4j.lang.JoseException;

import com.beust.jcommander.JCommander;
import com.bjond.aph.client.utils.JSONUtils;
import com.bjond.aph.client.utils.JWTUtil;

public class APHClientExecutor {
	
	private APHClientCommands commands;
	private JCommander commander;
	
	public APHClientExecutor(JCommander pCommander, APHClientCommands pCommands) {
		commands = pCommands;
		commander = pCommander;
	}
	
	/**
	 * execute the command.
	 */
	public void execute() {
		if(commands.isHelp()) {
			commander.usage();
		}
		else {
			makeCall();
		}
	}
	
	/**
	 * Make the call to the given endpoint.
	 */
	private void makeCall() {
		try {
			String json = JSONUtils.toJSON(commands.getJson());
			System.out.println("\n ---------------------------------------------------------\n");
			System.out.println("JSON Payload: " + json);
			
			final String token = generateJWTToken(json);
			
			System.out.println("JWT Token: " + token);
			System.out.println("\n ---------------------------------------------------------\n");
			
			final ResteasyClient client    = new ResteasyClientBuilder().build();
			final String endpoint = commands.getUrl() + commands.getEndpoint();
			
			System.out.println("Endpoint: " + commands.getUrl() + commands.getEndpoint());
			
            final ResteasyWebTarget target = client.target(endpoint);
            final Response response = target.request().post(Entity.entity(token, MediaType.TEXT_PLAIN));
            
            System.out.println("Status: " + Integer.toString(response.getStatus()));
            System.out.println("Status Info: " + response.getStatusInfo());
            if(response.hasEntity()) {
            	System.out.println("Response: " + response.readEntity(String.class));
            }
            System.out.println("\n");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
     * Generates a JWT Token with the json embedded within a claim.
     * 
	 * @param json
	 * @return
	 * @throws JoseException
	 */
	private String generateJWTToken(final String json) throws JoseException {
        final Key key = JWTUtil.generateAESKey(JWTUtil.base64Decode(commands.getKey()));
		final Map<String, List<String>> claimsMap = new HashMap<>();
		claimsMap.put("json", Arrays.asList(json));
        return JWTUtil.generateJWT_AES128(key, commands.getIssuer(), commands.getAudience(), commands.getSubject(), claimsMap, commands.getClockSkew());
	}
}
