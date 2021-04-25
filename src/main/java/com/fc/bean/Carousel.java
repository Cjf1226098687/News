package com.fc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carousel implements Serializable {
    private Integer id;
    private Integer musicId;
    private String carouselUrl;
}
