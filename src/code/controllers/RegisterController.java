package code.controllers;

import code.model.dao.UserDAOImpl;
import code.model.pojo.User;
import code.services.UserService;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * Created by admin on 27.04.2017.
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet() {
        return "register/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView register(@RequestParam(value = "login", required = false) String login,
                               @RequestParam(value = "password", required = false) String password,
                               @RequestParam(value = "email", required = false) String mail){

        User user = null;
        ModelAndView mav = new ModelAndView();
        try {
            user = userService.validateUser(login,password,mail);
            boolean error=false;

            mav.addObject("registerLogin", "");
            mav.addObject("registerPassword", "");
            mav.addObject("registerMail", "");

            if(user.getLogin().equals("@Error1")){
                mav.addObject("registerLogin", "Login can be contain digits, and length don't be less 2 symbols and don't be over 16 symbols");
                error=true;
            }
            if(user.getLogin().equals("@Error2")){
                mav.addObject("registerLogin", "User with this login already exist in database");
                error=true;
            }
            if(user.getPassword().equals("@Error1")){
                mav.addObject("registerPassword", "Password can be contain digits, and length don't be less 8 symbols and don't be over 16 symbols");

                error=true;
            }
            if(user.getMail().equals("@Error1")){
                mav.addObject("registerMail", "Mail can be contain digits, latin symbols and symbols: [.,_-@], and length don't be less 8 symbols and don't be over 30 symbols.");
                error=true;
            }
            if(user.getMail().equals("@Error2")){
                mav.addObject("registerMail", "User with this email already exist in database");
                error=true;
            }
            if(error){
                mav.setViewName("register/register");
            }
            else {
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                userService.addUser(user);
                mav.setViewName("redirect:/");
            }
            System.out.println(user);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            mav.addObject("error","Sorry we are have a problem with database, but we are" +
                    " fix it in 5 minutes");
            mav.setViewName("/error");
        }
        return mav;
    }
}
