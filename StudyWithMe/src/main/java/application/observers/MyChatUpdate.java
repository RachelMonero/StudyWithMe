package application.observers;

import application.beans.Chat;

public class MyChatUpdate implements ChatObserver {

	@Override
	public void update(Chat chat,String groupName) {

		System.out.println("\nNew Message Alert: ");
		System.out.println("New Message added to the group: " +groupName+ " by member " +chat.getCreator());
		System.out.println("End of notification.\n");
	
	}

}
