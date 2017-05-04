package code.controllers;

import code.model.pojo.StorageUnit;
import code.model.pojo.User;
import code.services.StorageUnitService;
import code.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 *
 */
@Controller
@RequestMapping(value = "/listEntitiesForAdmins")
public class ListForAdminsController {

    private StorageUnitService storageUnitService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStorageUnitService(StorageUnitService storageUnitService) {
        this.storageUnitService = storageUnitService;
    }

    ArrayList<StorageUnit> storageUnits = null;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showList(ModelAndView mav) {
        try {
            storageUnits=storageUnitService.getAllStorageUnits();
            mav.addObject("books", storageUnits);
            mav.addObject("users", userService.getAllUsers());
        } catch (SQLException e) {
            mav.addObject("error","Sorry we are have a problem with database, but we are" +
                    " fix it in 5 minutes");
            mav.addObject("/error");
        }
        mav.addObject("currentURL","/listEntitiesForAdmins");
        mav.setViewName("listEntitiesForAdmins/forAdmins");

        return mav;
    }

    @RequestMapping(value = "/readStorageUnit",method = RequestMethod.GET)
    public ModelAndView readStorageUnit(@RequestParam(value = "isn", required = true) String isn) {

        ModelAndView mav = new ModelAndView();
        if (storageUnits!=null) {
            StorageUnit storageUnit=storageUnits.stream().filter(x->x.getIsn().equals(isn)).findFirst().get();
            mav.addObject("title", storageUnit.getTitle());
            mav.addObject("text", storageUnit.getText());
            mav.addObject("admin", 1);
            mav.setViewName("read/readStorageUnit");
        }
        return mav;
    }

    @RequestMapping(value = "/deleteStorageUnit",method = RequestMethod.GET)
    public ModelAndView deleteStorageUnit(@RequestParam(value = "isn", required = true) String isn) {
        ModelAndView mav = new ModelAndView();
        try {
            storageUnitService.delStorageUnitByISN(isn);
        } catch (SQLException e) {
            mav.addObject("error","Sorry we are have a problem with database, but we are" +
                    " fix it in 5 minutes");
            mav.addObject("/error");
        }
        mav.setViewName("redirect:/listEntitiesForAdmins");
        return mav;
    }

    @RequestMapping(value = "/changeStorageUnit",method = RequestMethod.GET)
    public ModelAndView changeStorageUnitGet(@RequestParam(value = "isn", required = true) String isn) {

        ModelAndView mav = new ModelAndView();

        if (storageUnits!=null) {
            StorageUnit storageUnit=storageUnits.stream().filter(x->x.getIsn().equals(isn)).findFirst().get();

            mav.addObject("author", storageUnit.getAuthor());
            mav.addObject("title", storageUnit.getTitle());
            mav.addObject("publishingHouse", storageUnit.getPublishingHouse());
            mav.addObject("city", storageUnit.getCity());
            mav.addObject("year", storageUnit.getYear());
            mav.addObject("pagesCount", storageUnit.getPagesCount());
            mav.addObject("isn", storageUnit.getIsn());
            mav.addObject("text", storageUnit.getText());

            mav.setViewName("/listEntitiesForAdmins/changeStorageUnit");
        }
        return mav;
    }

    @RequestMapping(value = "/changeStorageUnit",method = RequestMethod.POST)
    public ModelAndView changeStorageUnitPost(@RequestParam(value = "isnOld", required = true) String isnOld,
                                              @RequestParam(value = "author", required = true) String author,
                                              @RequestParam(value = "title", required = true) String title,
                                              @RequestParam(value = "publishingHouse", required = true) String publishingHouse,
                                              @RequestParam(value = "city", required = true) String city,
                                              @RequestParam(value = "year", required = true) String year,
                                              @RequestParam(value = "pagesCount", required = true) String pagesCount,
                                              @RequestParam(value = "isnNew", required = true) String isnNew,
                                              @RequestParam(value = "text", required = true) String text) {
        return changeOrAddStorageUnit(isnOld,author,title,publishingHouse,city,year,pagesCount,isnNew,text,true);
    }

    @RequestMapping(value = "/addStorageUnit",method = RequestMethod.GET)
    public String addStorageUnitGet() {
        return "listEntitiesForAdmins/changeStorageUnit";
    }

    @RequestMapping(value = "/addStorageUnit",method = RequestMethod.POST)
    public ModelAndView addStorageUnitPost(@RequestParam(value = "isnOld", required = true) String isnOld,
                                     @RequestParam(value = "author", required = true) String author,
                                     @RequestParam(value = "title", required = true) String title,
                                     @RequestParam(value = "publishingHouse", required = true) String publishingHouse,
                                     @RequestParam(value = "city", required = true) String city,
                                     @RequestParam(value = "year", required = true) String year,
                                     @RequestParam(value = "pagesCount", required = true) String pagesCount,
                                     @RequestParam(value = "isnNew", required = true) String isnNew,
                                     @RequestParam(value = "text", required = true) String text) {
        return changeOrAddStorageUnit(isnOld,author,title,publishingHouse,city,year,pagesCount,isnNew,text,false);
    }

    public ModelAndView changeOrAddStorageUnit(String isnOld,String author,String title,String publishingHouse,String city,
                                               String year, String pagesCount, String isnNew,String text,boolean change) {
        ModelAndView mav = new ModelAndView();
        boolean error = false;

        StorageUnit storageUnit = storageUnitService.validateStorageUnit(author, title, publishingHouse, city, year, pagesCount, isnNew, text);

        try {
            if (storageUnit.getAuthor().equals("@Error1")) {
                mav.addObject("changeAuthor",
                        "Field [Author] can be contain digits, latin symbols and cirilic symbols and symbols: [.,_-], and field don't be empty.");
                error = true;
            }
            if (storageUnit.getTitle().equals("@Error1")) {
                mav.addObject("changeTitle",
                        "Field [Title] can be contain digits, latin symbols and cirilic symbols and symbols: [.,_-], and field don't be empty.");
                error = true;
            }
            if (storageUnit.getPublishingHouse().equals("@Error1")) {
                mav.addObject("changePublishingHouse",
                        "Field [Publishing house] can be contain digits, latin symbols and cirilic symbols and symbols: [.,_-], and field don't be empty.");
                error = true;
            }
            if (storageUnit.getCity().equals("@Error1")) {
                mav.addObject("changeCity",
                        "Field [City] can be contain digits, latin symbols and cirilic symbols and symbols: [.,_-], and field don't be empty.");
                error = true;
            }
            if (storageUnit.getYear() == -1) {
                mav.addObject("changeYear",
                        "Field [Year] can be contain only digits, and field don't be empty.");
                error = true;
            }
            if (storageUnit.getPagesCount() == -1) {
                mav.addObject("changePagesCount",
                        "Field [Pages count] can be contain only digits, and field don't be empty.");
                error = true;
            }
            if (storageUnit.getIsn().equals("@Error1")) {
                mav.addObject("changeIsn",
                        "Field [City] can be contain digits, latin symbols and cirilic symbols and symbol: [-], and field don't be empty.");
                error = true;
            }
            if (!change) {
                if (storageUnitService.getStorageUnitByISN(isnNew) != null) {
                    mav.addObject("changeIsn",
                            "Field [ISN] is a unique, storage unit with this isn already contain in database!");
                    error = true;
                }
            }
            if (storageUnit.getText().equals("@Error1")) {
                mav.addObject("changeText",
                        "Field [Text] don't be empty.");
                error = true;
            }
            if (change) {
                if (!error) {
                    if (isnOld.equals(isnNew)) {
                        storageUnitService.delStorageUnitByISN(isnOld);
                    } else {
                        if (storageUnitService.getStorageUnitByISN(isnNew) != null) {
                            mav.addObject("changeIsn",
                                    "Field [ISN] is a unique, storage unit with this isn already contain in database!");
                            error = true;
                        } else {
                            storageUnitService.delStorageUnitByISN(isnOld);
                        }
                    }
                }
            }
            if (error) {
                mav.addObject("author", author);
                mav.addObject("title", title);
                mav.addObject("publishingHouse", publishingHouse);
                mav.addObject("city", city);
                mav.addObject("year", year);
                mav.addObject("pagesCount", pagesCount);
                mav.addObject("isn", isnNew);
                mav.addObject("text", text);
                mav.setViewName("/listEntitiesForAdmins/changeStorageUnit");
            } else {
                storageUnitService.addStorageUnit(storageUnit);
                mav.setViewName("redirect:/listEntitiesForAdmins");
            }
        }catch (SQLException e) {
            mav.addObject("error","Sorry we are have a problem with database, but we are" +
                    " fix it in 5 minutes");
            mav.addObject("/error");
        }
        return mav;
    }

    @RequestMapping(value = "/lockOrUnlock",method = RequestMethod.GET)
    public ModelAndView lockOrUnlock(@RequestParam(value = "nick", required = true) String nick,
                               @RequestParam(value = "lock", required = true) String lock) {
        ModelAndView mav = new ModelAndView();
        int newLock=0;
        newLock=lock.equals("0") ? 1:0;
        try {
            userService.lockOrUnlockUser(nick,newLock);
        } catch (SQLException e) {
            mav.addObject("error","Sorry we are have a problem with database, but we are" +
                    " fix it in 5 minutes");
            mav.setViewName("/error");
        }
        mav.setViewName("redirect:/listEntitiesForAdmins");
        return mav;
    }
}
