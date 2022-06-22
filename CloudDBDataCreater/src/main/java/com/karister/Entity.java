package com.karister;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author karister
 * @create 2021-11-28 10:30
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Entity implements Serializable{
    String _openid;
    String brand;
    String name;
    String phone;
    String area;
    String address;
    double latitude;
    double longitude;
    String[] label;
    String brandImgSrc;
    Integer browseNum;
    Integer authState;
    String[] authImgUrl;
    Integer viewState;
    Integer notTest;
}
