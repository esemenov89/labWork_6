package code.controllers;

import code.model.pojo.User;
import code.services.UserService;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 *
 */
@Controller
@SessionAttributes({"userLogin","accountType"})
public class WelcomeController {
    private static final Logger LOGGER = Logger.getLogger(WelcomeController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello() {
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "login", required = true) String login,
                              @RequestParam(value = "password", required = true) String password) {
        ModelAndView mav = new ModelAndView();

        User user = null;
        try {
            if ((user = userService.auth(login, password)) != null) {
                if(user.getLocked()==1){
                    mav.addObject("errorLogin","You account was blocked!");
                    mav.setViewName("welcome");
                }
                else {
                    LOGGER.info("login: " + login);
                    mav.addObject("userLogin", login);
                    mav.addObject("errorLogin", "");
                    mav.addObject("accountType", user.getAccountType());
                    if (user.getAccountType() == 1) {
                        LOGGER.debug("ADMIN: " + login + " logged ");
                        mav.setViewName("redirect:/listEntitiesForAdmins");
                    } else {
                        LOGGER.debug("ADMIN: " + login + " logged ");
                        mav.setViewName("redirect:/listEntitiesForUsers");
                    }
                }
            }else{
                mav.addObject("errorLogin","Error: wrong username or password");
                mav.setViewName("welcome");
            }
        } catch (SQLException e) {
            mav.addObject("error","Sorry we are have a problem with database, but we are" +
                    " fix it in 5 minutes");
            mav.setViewName("/error");
        }
        return mav;
    }
}
