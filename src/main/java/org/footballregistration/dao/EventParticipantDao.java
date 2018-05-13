package org.footballregistration.dao;

import java.util.List;

import org.footballregistration.dao.entity.EventParticipantEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipantDao {

	List<EventParticipantEntity> selectEventParticipantByEventId(int eventId);

	List<EventParticipantEntity> selectEventParticipantByUserId(String userId);
	
	long insertEventParticipant(EventParticipantEntity eventParticipantEntity);
	
	long updateEventParticipant(EventParticipantEntity eventParticipantEntity);

}
