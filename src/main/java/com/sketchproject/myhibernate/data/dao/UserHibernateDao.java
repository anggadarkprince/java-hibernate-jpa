package com.sketchproject.myhibernate.data.dao;

import com.sketchproject.myhibernate.data.dao.interfaces.UserDao;
import com.sketchproject.myhibernate.entities.User;
import java.util.List;

public class UserHibernateDao extends AbstractDao<User, Long> implements UserDao {

    @Override
    public List<User> findByFirstName(String firstName) {
        //add implementation here...
        return null;
    }

}
