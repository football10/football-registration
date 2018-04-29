package org.footballregistration.dao;

import org.footballregistration.dao.entity.EventInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface EventInfoDao {

	EventInfo selectEventInfo(int eventId);

	long insertEventInfo(EventInfo eventInfo);

}
