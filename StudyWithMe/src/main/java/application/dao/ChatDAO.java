package application.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import application.beans.Chat;
import application.beans.User;
import application.connection.DBConnection;

// class to obtain the chat logs from the database
public class ChatDAO {
	
	public List<Chat> getChatMessages(){
		
		List<Chat> chatMessages = new ArrayList<>();
		try {
			Connection conn = DBConnection.getConnectionToDatabase();
			String query = "SELECT * FROM chats";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet resultSet = stmt.executeQuery();
			
			
				
				while (resultSet.next()) {
					Chat chat = new Chat();
					
					String chatMsg = resultSet.getString("chat");
					String creator = resultSet.getString("creator");
					String gpId = resultSet.getString("groupId");
					Date timeStamp = resultSet.getDate("timeDateStamp");
					
					System.out.println("query :"+query);
					System.out.println("Group Id: "+gpId);
					
					chat.setChatId(UUID.fromString(resultSet.getString("chatId")));
					System.out.println("chat Id: "+UUID.fromString(resultSet.getString("chatId")));
					
					chat.setChat(chatMsg);
					chat.setTimeDateStamp(timeStamp);
					chat.setCreator(creator);
					chat.setGroupId(gpId);
			
					chatMessages.add(chat);
					System.out.println(chat);

				}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chatMessages;	
	}
	
public List<Chat> getChatMessagesForGroup(String groupId){
		
		List<Chat> chatMessages = new ArrayList<>();
		try {
			Connection conn = DBConnection.getConnectionToDatabase();
			String query = "SELECT * FROM chats WHERE groupId =?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, groupId);
			ResultSet resultSet = stmt.executeQuery();
				
			
				while (resultSet.next()) {
					Chat chat = new Chat();
					
					String uuidAsString = resultSet.getString("chatId");
					UUID chatId = UUID.fromString(uuidAsString);
					String chatMsg = resultSet.getString("chat");
					String creator = resultSet.getString("creator");
					String gpId = resultSet.getString("groupId");
					Date timeStamp = resultSet.getDate("timeDateStamp");
					
					chat.setChatId(chatId);
					chat.setChat(chatMsg);
					chat.setTimeDateStamp(timeStamp);
					chat.setCreator(creator);
					chat.setGroupId(gpId);
			
					chatMessages.add(chat);

				}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chatMessages;	
	}
	
		
		

	public void addChatMessage(Chat chat) {
		try {
			Connection conn = DBConnection.getConnectionToDatabase();
			String chatId = UUID.randomUUID().toString();
			System.out.println("uuid is "+chatId);
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			System.out.println("current timedate is "+currentTimestamp);
			String query = "INSERT INTO chats (chatId,chat,timeDateStamp,creator,groupId) VALUES(?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query);
				
					stmt.setString(1, chatId);
					stmt.setString(2,chat.getChat());
					stmt.setTimestamp(3, currentTimestamp);
					stmt.setString(4, chat.getCreator());
					stmt.setString(5, chat.getGroupId());
			
					stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

