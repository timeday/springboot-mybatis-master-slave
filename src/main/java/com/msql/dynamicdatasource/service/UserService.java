package com.msql.dynamicdatasource.service;

import com.msql.dynamicdatasource.mapper.UserDao;
import com.msql.dynamicdatasource.common.ServiceException;
import com.msql.dynamicdatasource.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public User select(long userId) throws ServiceException {
        User user = userDao.select(userId);
        if (user == null) {
            throw new ServiceException("User:" + userId + " not found");
        }
        return user;
    }


    @Transactional(rollbackFor = DataAccessException.class)
    public User update(long userId, User user) throws ServiceException {

        if (userDao.update(user) <= 0) {
            throw new ServiceException("Update User:" + userId + "failed");
        }
        return user;
    }


    @Transactional(rollbackFor = DataAccessException.class)
    public boolean add(User user) throws ServiceException {
        Integer num = userDao.insert(user);
        if (num <= 0) {
            throw new ServiceException("Add User failed");
        }
        return true;
    }


    @Transactional(rollbackFor = DataAccessException.class)
    public boolean delete(long userId) throws ServiceException {
        Integer num = userDao.delete(userId);
        if (num <= 0) {
            throw new ServiceException("Delete User:" + userId + "failed");
        }
        return true;
    }


    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
