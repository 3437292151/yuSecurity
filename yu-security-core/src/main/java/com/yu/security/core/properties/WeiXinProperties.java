package com.yu.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

@Data
public class WeiXinProperties extends SocialProperties {

    private String providerId = "weixin";

}
