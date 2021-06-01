package com.example.nordicmotorhomes1.Controller;

import com.example.nordicmotorhomes1.Model.MotorhomeModelModel;
import com.example.nordicmotorhomes1.Service.MotorhomeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MotorhomeModelController {

    @Autowired
    MotorhomeModelService motorhomeModelService;

    /* This method lists all motorhome models in the database on an HTML site. */
    @GetMapping("/listMotorhome")
    public String listMotorhomeModel(Model model){
        List<MotorhomeModelModel> motorhomeList = motorhomeModelService.fetchAll();
        model.addAttribute("motorhomeModels", motorhomeList);
        return "MotorhomeModel/listMotorhome";
    }
    /* This method takes inputted search parameters and queries the database for motorhome model
     * entities that match the criteria. They are then displayed on a HTML page.*/
    @PostMapping("/listMotorhomeModelParam")
    public String searchMotorhomeModelParam(
            @RequestParam(value = "brand", required = false) String br,
            @RequestParam(value = "modelName", required = false) String mod,
            @RequestParam(value = "pricePerDay", required = false) String ppd,
            @RequestParam(value = "numberOfBeds", required = false) String nob,
            @RequestParam(value = "numberOfSeats", required = false) String nos,
            @RequestParam(value = "numberOfSeatBelts", required = false) String nosb,
            @RequestParam(value = "weight", required = false) String w,
            @RequestParam(value = "maxLoadedWeight", required = false) String mlw,
            @RequestParam(value = "modelDescriptionNote", required = false) String mdn,
            Model model
    )
    {
        List<MotorhomeModelModel> motorhomeModelList = motorhomeModelService.searchMotorhomeModelParam(br, mod, ppd, nob, nos, nosb, w, mlw, mdn);
        model.addAttribute("motorhomeModels", motorhomeModelList);
        return "MotorhomeModel/listMotorhome";
    }

    /* This method takes inputted search parameters and queries the database for motorhome model
    * entities that match the criteria. They are then displayed on the front page. The idea is
    * show which motorhome models are available during a certain period as the customer
    * specifies. It is shown in the front page because it is thought to be the most frequent use
    * of the application. */
    @PostMapping("/searchMotorhomeModelParam")
    public String searchMotorhomeModel(
            @RequestParam(value = "startDate", required = false) String sd,
            @RequestParam(value = "endDate", required = false) String ed,
            @RequestParam(value = "pricePerDay", required = false) String ppd,
            @RequestParam(value = "brand", required = false) String br,
            @RequestParam(value = "modelName", required = false) String mod,
            @RequestParam(value = "numberOfBeds", required = false) String nob,
            @RequestParam(value = "numberOfSeats", required = false) String nos,
            @RequestParam(value = "numberOfSeatBelts", required = false) String nosb,
            Model model
    ){
        List<MotorhomeModelModel> motorhomeModelList = motorhomeModelService.searchMotorhomeModel(sd, ed, ppd, br, mod, nob, nos, nosb);
        model.addAttribute("motorhomeModels", motorhomeModelList);
        return "home/index";
    }

    /*The HTML site referenced by this method prompts the user for information about a new model of
    * motorhome.*/
    @GetMapping("/createMotorhomeModel")
    public String createMotorhomeModel(){
        return "MotorhomeModel/createMotorhomeModel";
    }

    /* This method takes data inputted about a new motorhome and instantiates a MotorhomeModelModel
    * object using that data. The data from the object is saved as an entity in the database.*/
    @PostMapping("/createMotorhomeModel")
    public String createMotorhomeModel(@ModelAttribute MotorhomeModelModel motorhome){
        motorhomeModelService.addMotorhomeModel(motorhome);
        return "redirect:/listMotorhome";
    }

    /* The method takes a motorhomeModelID and queries the database for the corresponding motorhomeModel.
    * Information about that model is displayed on the HTML site, and the user has access to editing that
    * information. */
    @GetMapping("/updateMotorhomeModel/{motorhomeModelID}")
    public String updateMotorhomeModel(@PathVariable("motorhomeModelID") int motorhomeModelID, Model model){
        model.addAttribute("motorhomemodel", motorhomeModelService.findMotorhomeModelById(motorhomeModelID));
        return "MotorhomeModel/updateMotorhomeModel";
    }

    /* The method takes the newly edited information about the motorhome model, and instantiates a
    * MotorhomeModelModel object from it. The data from the object is used to update the existing
    * motorhomeModel entity in the database. */
    @PostMapping("/updateMotorhomeModel")
    public String updateMotorhomeModel(@ModelAttribute MotorhomeModelModel motorhomeModel, Model model){
        motorhomeModelService.updateMotorhomeModel(motorhomeModel.getMotorhomeModelID(), motorhomeModel);
        List<MotorhomeModelModel> motorhomeList = motorhomeModelService.fetchAll();
        model.addAttribute("motorhomeModels", motorhomeList);
        return "MotorhomeModel/listMotorhome";
    }
}
