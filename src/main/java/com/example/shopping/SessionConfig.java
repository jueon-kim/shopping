package com.example.shopping;

import com.example.shopping.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@ControllerAdvice
public class SessionConfig {

    @ModelAttribute("id")
    public String loginId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member member = (Member) session.getAttribute("loginMember");
            if (member != null) {
                return member.getId(); // String 타입 반환
            }
        }
        return null;
    }
}