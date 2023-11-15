package application.beans;

import java.util.Date;
import java.util.UUID;

/* this is a super class for chats
 * 
 */

public class Chat {
	
	private UUID chatId;
	private String chat;
	private Date timeDateStamp;
	private String creator; // this is chat creator not admin of group.
	private String groupId;
	
	
	
	
	
	public Chat( String chat,  String creator, String groupId) {
		super();
		
		this.chat = chat;
		this.timeDateStamp =  new Date();
		this.creator = creator;
		this.groupId = groupId;
	}
	
	
	
	public Chat() {
		// TODO Auto-generated constructor stub
	}


	public UUID getChatId() {
		return chatId;
	}
	public void setChatId(UUID id) {
		this.chatId = id;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}
	public Date getTimeDateStamp() {
		return timeDateStamp;
	}
	public void setTimeDateStamp(Date timeStamp) {
		this.timeDateStamp = timeStamp;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String username) {
		if(username!=null) {
			this.creator = username; // new made user name a string
		}else {
			this.creator = "No Name";
		}
		
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


}


