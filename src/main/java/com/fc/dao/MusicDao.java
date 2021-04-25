package com.fc.dao;

import com.fc.vo.MusicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MusicDao {
    List<MusicVo> findByKeyword(@Param("keyword") String keyword);
}
