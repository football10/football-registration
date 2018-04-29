package org.footballregistration.dao;

import org.footballregistration.dao.entity.AuthorityEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao {

	AuthorityEntity selectGroupInfo(String groupID);

}
