package application.observers;

import application.beans.Chat;

public interface ChatObserver {
	
	void update (Chat chat, String groupName);

}
