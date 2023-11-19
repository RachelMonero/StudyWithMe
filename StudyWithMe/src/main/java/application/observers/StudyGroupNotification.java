package application.observers;

import application.beans.StudyGroup;

public class StudyGroupNotification implements StudyGroupObserver {

	@Override
	public void update(StudyGroup studyGrp) {
		System.out.println("\nNotification for creation of study group");
		System.out.println("New group created: "+studyGrp.getGroupName()+" for course "+studyGrp.getSubject());
		System.out.println("End of Notification\n");
	}

}
