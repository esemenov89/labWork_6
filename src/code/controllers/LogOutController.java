package code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by admin on 27.04.2017.
 */
@Controller
@RequestMapping(value = "/logout")
public class LogOutController {

    @RequestMapping(method = RequestMethod.GET)
    public String doGet() {
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String closeSession(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
