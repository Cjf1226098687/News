package com.fc.service.impl;

import com.fc.bean.Navigation;
import com.fc.dao.NavigationDao;
import com.fc.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationServiceImpl implements NavigationService {
    @Autowired
    private NavigationDao navigationDao;

    @Override
    public List<Navigation> findAll() {
        return navigationDao.findAll();
    }
}
