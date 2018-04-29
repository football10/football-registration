package org.footballregistration.dao;

import org.footballregistration.dao.entity.EventInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EventInfoDao {

	EventInfoEntity selectEventInfo(int eventId);

	long insertEventInfo(EventInfoEntity eventInfo);

}
