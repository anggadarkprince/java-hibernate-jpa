package com.sketchproject.myhibernate.data.dao.interfaces;

import com.sketchproject.myhibernate.entities.User;
import java.util.List;

public interface UserDao extends Dao<User, Long> {

    public List<User> findByFirstName(String firstName);
}
