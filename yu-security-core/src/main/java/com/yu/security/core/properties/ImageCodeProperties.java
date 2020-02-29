package com.yu.security.core.properties;

import lombok.Data;

/**
 * @Author yuchl
 * @Date 2020/2/23 0023
 * @Description 图片配置
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties() {
        setLength(4);
    }

    private Integer width = 67;

    private Integer height = 23;

    private String formatName = "JPEG";

}
