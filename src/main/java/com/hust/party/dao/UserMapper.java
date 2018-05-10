package com.hust.party.dao;

import com.hust.party.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
	User selectById(String Id);

	User selectByUser(User user);

	User selectUserbyId(String id);

	void resetpassword(User user);

	void changepassword(User user);

	void updatecreorbounds(User user);

}
