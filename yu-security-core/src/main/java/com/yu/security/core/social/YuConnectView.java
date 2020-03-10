package com.yu.security.core.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 社交软件关系解除与绑定
 */
public class YuConnectView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, List<Connection>> connections = (Map<String, List<Connection>>)model.get("connectionMap");

        Map<String, Boolean> result = new HashMap<>();
        for (String key : connections.keySet()){
            result.put(key, CollectionUtils.isNotEmpty(connections.get(key)));
        }
        response.getWriter().write("");
    }
}
