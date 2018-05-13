package org.footballregistration.dao;

import org.footballregistration.dao.entity.UserInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao {

	UserInfoEntity selectUserInfo(String userId);

	long insertUserInfo(UserInfoEntity userInfoEntity);

	long updateUserInfo(UserInfoEntity userInfoEntity);
}
