package application.observers;

import application.beans.Chat;

public interface ChatSubject {
	
	// methods to implemented by the Notifier
	void addObserver(ChatObserver chatObs);
	void removeObserver(ChatObserver chatObs);
	void notifyObservers(Chat chat, String groupName);
	

}
