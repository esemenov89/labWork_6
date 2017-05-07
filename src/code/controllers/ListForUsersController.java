package code.controllers;

import code.model.pojo.StorageUnit;
import code.services.StorageUnitService;
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
@RequestMapping(value = "/listEntitiesForUsers")
public class ListForUsersController {

    private StorageUnitService storageUnitService;

    @Autowired
    public void setStorageUnitService(StorageUnitService storageUnitService) {
        this.storageUnitService = storageUnitService;
    }

    ArrayList<StorageUnit> storageUnits = null;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showList(ModelAndView mav) throws Exception{
        storageUnits=storageUnitService.getAllStorageUnits();
        mav.addObject("books", storageUnits);
        mav.setViewName("listEntitiesForUsers/forUsers");

        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView readStorageUnit(@RequestParam(value = "isn", required = false) String isn) {

        ModelAndView mav = new ModelAndView();
        if (storageUnits!=null) {
            StorageUnit storageUnit=storageUnits.stream().filter(x->x.getIsn().equals(isn)).findFirst().get();

            mav.addObject("title", storageUnit.getTitle());
            mav.addObject("text", storageUnit.getText());

            mav.setViewName("read/readStorageUnit");
        }
        return mav;
    }
}
