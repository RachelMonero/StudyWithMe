package application.observers;

import java.util.ArrayList;
import java.util.List;

import application.beans.Chat;

public class ChatNotifier implements ChatSubject {

	private List<ChatObserver> chatObservers = new ArrayList<>();
	

	@Override
	public void addObserver(ChatObserver chatObs) {
		
		chatObservers.add(chatObs);
	}

	@Override
	public void removeObserver(ChatObserver chatObs) {
		
		chatObservers.remove(chatObs);
	}

	@Override
	public void notifyObservers(Chat chat, String groupName) {
		// TODO Auto-generated method stub
		for (ChatObserver obs : chatObservers) {
			obs.update(chat,groupName);
		}
	}


	
	
		
		

	

}
