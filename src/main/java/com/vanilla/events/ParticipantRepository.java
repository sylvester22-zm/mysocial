package com.vanilla.events;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author sylvester kalumbi
 */
public class ParticipantRepository {

	private Map<String, ActiveEvent> activeSessions = new ConcurrentHashMap<>();
	
	

	public void add(String sessionId, ActiveEvent event) {
		
		  for(String check:activeSessions.keySet()) {
		  if(check==sessionId) { continue; }else {
		  activeSessions.put(sessionId,event); } }
		 
		
	}

	public ActiveEvent getParticipant(String sessionId) {
		return activeSessions.get(sessionId);
	}

	public void removeParticipant(String sessionId) {
		activeSessions.remove(sessionId);
	}

	public Map<String, ActiveEvent> getActiveSessions() {
		return activeSessions;
	}

	public void setActiveSessions(Map<String, ActiveEvent> activeSessions) {
		this.activeSessions = activeSessions;
	}
}
