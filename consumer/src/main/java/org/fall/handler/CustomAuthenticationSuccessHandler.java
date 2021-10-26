package org.fall.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fall.api.MySQLRemoteService;
import org.fall.entity.po.UserPO;
import org.fall.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    MySQLRemoteService mySQLRemoteService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String phoneNumber = userDetails.getUsername();
        ResultEntity<UserPO> userByPhone = mySQLRemoteService.getUserByPhone(phoneNumber);
        UserPO userPO = userByPhone.getData();
        userPO.setPassword(null);
        String sessionId = request.getSession().getId();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message","");
            JSONObject cookies = new JSONObject();
            cookies.put("JSESSIONID",sessionId);
            jsonObject.put("cookies",cookies);
            JSONObject data = new JSONObject();
            BASE64Encoder encoder = new BASE64Encoder();
            String headPortrait = encoder.encode(userPO.getHeadPortrait());
            data.put("id",userPO.getId());
            data.put("nickname",userPO.getNickname());
            data.put("authentication",userPO.getAuthentication());
            data.put("headPortrait",headPortrait);
            jsonObject.put("data",data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }
}
