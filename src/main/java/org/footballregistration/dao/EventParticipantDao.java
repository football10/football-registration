package org.footballregistration.dao;

import java.util.List;

import org.footballregistration.dao.entity.EventParticipantEntity;
import org.footballregistration.dao.parameter.EventParticipantParameter;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipantDao {

	List<EventParticipantEntity> selectEventParticipantByEventId(int eventId);

	List<EventParticipantEntity> selectEventParticipantByUserId(String userId);

	EventParticipantEntity selectEventParticipantByPk(EventParticipantParameter eventParticipantParameter);
	
	long insertEventParticipant(EventParticipantEntity eventParticipantEntity);
	
	long updateEventParticipant(EventParticipantEntity eventParticipantEntity);
	
	long deleteEventParticipant(EventParticipantParameter eventParticipantParameter);

}
