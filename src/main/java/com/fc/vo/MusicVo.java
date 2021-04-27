package com.fc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicVo implements Serializable {
    private Integer id;
    private String musicName;
    private String albumName;
    private String artistName;
    private String musicMp3Url;
    private String albumPicUrl;
    private String navigationName;
}
