package com.fc.dao;

import com.fc.bean.Navigation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavigationDao {
    List<Navigation> findAll();
}
