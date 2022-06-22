package com.karister;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author karister
 * @create 2021-11-30 17:09
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandImgEntity implements Serializable {
    String brandID;
    String imgUrl;
}
