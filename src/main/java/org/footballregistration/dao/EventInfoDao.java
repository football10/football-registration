package org.footballregistration.dao;

import java.util.List;

import org.footballregistration.dao.entity.EventInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EventInfoDao {

	EventInfoEntity selectEventInfo(int eventId);

	List<EventInfoEntity> selectEventInfoByUserId(String userId);
	
	long insertEventInfo(EventInfoEntity eventInfo);
	
	long updateEventInfo(EventInfoEntity eventInfo);
	
	long deleteEventInfo(int eventId);

}
