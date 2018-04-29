package org.footballregistration.dao;

import org.footballregistration.dao.entity.Authority;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao {

	Authority selectGroupInfo(String groupID);

}
