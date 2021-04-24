package com.fc.dao;

import com.fc.bean.Navigation;
import com.fc.vo.MusicVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavigationDao {
    List<Navigation> findAll();

    List<MusicVo> findMusicsById(@Param("id") Integer id);
}
