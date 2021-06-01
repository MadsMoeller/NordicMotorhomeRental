package com.example.nordicmotorhomes1.Controller;

import com.example.nordicmotorhomes1.Service.MotorhomeModelService;
import com.example.nordicmotorhomes1.Support.MotorhomeUnitAndModel;
import com.example.nordicmotorhomes1.Model.MotorhomeModelModel;
import com.example.nordicmotorhomes1.Model.MotorhomeUnitModel;
import com.example.nordicmotorhomes1.Service.MotorhomeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MotorhomeUnitController {
    @Autowired
    MotorhomeUnitService motorhomeUnitService;

    @Autowired
    MotorhomeModelService motorhomeModelService;

    /*The HTML site referenced by this method prompts the user for information about a new unit of
     * motorhome.*/
    @GetMapping("/createMotorhomeUnit")
    public String createMotorhomeUnit(){
        return "motorhomeunit/createMotorhomeUnit";

    }

    /* This method takes data inputted about a new vehicle and instantiates a MotorhomeUnitModel
     * object using that data. The data from the object is saved as an entity in the database.*/
    @PostMapping("/createMotorhomeUnit")
    public String createMotorhomeUnit(@ModelAttribute MotorhomeUnitModel motorhomeUnit, Model model){
        motorhomeUnitService.addMotorhomeUnit(motorhomeUnit);
        List<MotorhomeUnitAndModel> motorhomeUnitAndModelList = fetchAllMotorhomeUnitAndModels();
        model.addAttribute("motorhomeUnitAndModels", motorhomeUnitAndModelList);
        return "motorhomeunit/searchMotorhomeUnit";
    }

    /* The method takes a motorhomeUnitID and queries the database for the corresponding motorhomeUnit.
     * Information about that model is displayed on the HTML site, and the user has access to editing that
     * information. */
    @GetMapping("/updateMotorhomeUnit/{motorhomeUnitID}")
    public String updateMotorhomeUnit(@PathVariable("motorhomeUnitID") int id, Model model){
        model.addAttribute("motorhomeunit", motorhomeUnitService.findMotorhomeUnitbyID(id));
        return "MotorhomeUnit/updateMotorhomeUnit";
    }

    /* The method takes the newly edited information about the motorhome unit, and instantiates a
     * MotorhomeUnitModel object from it. The data from the object is used to update the existing
     * motorhomeUnit entity in the database. */
    @PostMapping("/updateMotorhomeUnit")
    public String updateMotorhomeUnit(@ModelAttribute MotorhomeUnitModel mhu, Model model){
        motorhomeUnitService.updateMotorhomeUnit(mhu);
        List<MotorhomeUnitAndModel> motorhomeUnitAndModelList = fetchAllMotorhomeUnitAndModels();
        model.addAttribute("motorhomeUnitAndModels", motorhomeUnitAndModelList);
        return "MotorhomeUnit/searchMotorhomeUnit";
    }

    /* The method queries the database for a list of all motorhome units and displays that
    * information on the HTML site. The site has fields that allows the user to input
    * search parameters to find specific units. */
    @GetMapping("/searchMotorhomeUnit")
    public String searchMotorhomeUnit(Model model){
        List<MotorhomeUnitAndModel> motorhomeUnitAndModelList = fetchAllMotorhomeUnitAndModels();
        model.addAttribute("motorhomeUnitAndModels", motorhomeUnitAndModelList);
        return "motorhomeUnit/searchMotorhomeUnit";
    }

    /* The method takes inputted search parameters and queries the database for all motorhome
    * models and units. A list of MotorhomeUnitAndModel is created. This auxiliary class holds
    * information from both motorhome models and units, and allows for an elegant way to
    * display all the information at the same time. */
    @PostMapping("/searchMotorhomeUnitParam")
    public String searchMotorhomeUnit(
            @RequestParam(value = "brand", required = false) String b,
            @RequestParam(value = "modelName", required = false) String m,
            @RequestParam(value = "numberOfBeds", required = false) String nob,
            @RequestParam(value = "numberOfSeats", required = false) String nos,
            @RequestParam(value = "numberOfSeatBelts", required = false) String nosb,
            @RequestParam(value = "weight", required = false) String w,
            @RequestParam(value = "maxLoadedWeight", required = false) String mlw,
            @RequestParam(value = "registrationYear", required = false) String ry,
            @RequestParam(value = "registrationYearFrom", required = false) String ryf,
            @RequestParam(value = "registrationYearTo", required = false) String ryt,
            @RequestParam(value = "drivenKilometers", required = false) String dk,
            @RequestParam(value = "drivenKilometersFrom", required = false) String dkf,
            @RequestParam(value = "drivenKilometersTo", required = false) String dkt,
            @RequestParam(value = "pricePerDay", required = false) String ppd,
            @RequestParam(value = "registration", required = false) String r,
            @RequestParam(value = "modelDescriptionNote", required = false) String mdn,
            @RequestParam(value = "unitNote", required = false) String un,
            Model model)
    {
        List<MotorhomeUnitModel> motorhomeUnitList = motorhomeUnitService.searchMotorhomeUnit(ry, ryf, ryt, dk, dkf, dkt, r, un);
        List<MotorhomeModelModel> motorhomeModelList = motorhomeUnitService.searchMotorhomeModel(b, m, nob, nos, nosb, w, mlw, ppd, mdn);
        List<MotorhomeUnitAndModel> motorhomeUnitAndModelList = new ArrayList<>();
        for(int i = 0; i < motorhomeUnitList.size(); i++){
            for(int j = 0; j < motorhomeModelList.size(); j++){
                if(motorhomeUnitList.get(i).getMotorhomeModelID() == motorhomeModelList.get(j).getMotorhomeModelID()){
                    motorhomeUnitAndModelList.add(new MotorhomeUnitAndModel(motorhomeUnitList.get(i).getMotorhomeUnitID(),
                            motorhomeUnitList.get(i).getMotorhomeModelID(), motorhomeModelList.get(j).getBrand(),
                            motorhomeModelList.get(j).getModelName(), motorhomeModelList.get(j).getNumberOfBeds(),
                            motorhomeModelList.get(j).getNumberOfSeats(), motorhomeModelList.get(j).getNumberOfSeatBelts(),
                            motorhomeModelList.get(j).getWeight(), motorhomeModelList.get(j).getMaxLoadedWeight(),
                            motorhomeModelList.get(j).getDescriptionNote(), motorhomeUnitList.get(i).getRegistrationYear(),
                            motorhomeUnitList.get(i).getDrivenKilometers(), motorhomeModelList.get(j).getPricePerDay(),
                            motorhomeUnitList.get(i).getRegistration(), motorhomeUnitList.get(i).getNotes()));
                }
            }
        }
        Collections.sort(motorhomeUnitAndModelList);
        model.addAttribute("motorhomeUnitAndModels", motorhomeUnitAndModelList);
        return "MotorhomeUnit/searchMotorhomeUnit";
    }

    /* This method returns a list with data all motorhome units from the database. It is coupled
    * with data on the corresponding motorhome model, and is stored in MotorhomeUnitAndModel objects.  */
    public List<MotorhomeUnitAndModel> fetchAllMotorhomeUnitAndModels(){
        List<MotorhomeUnitModel> motorhomeUnitList  = motorhomeUnitService.fetchAll();
        List<MotorhomeModelModel> motorhomeModelList = motorhomeModelService.fetchAll();
        List<MotorhomeUnitAndModel> motorhomeUnitAndModelList = new ArrayList<>();
        for(int i = 0; i < motorhomeUnitList.size(); i++){
            for(int j = 0; j < motorhomeModelList.size(); j++){
                if(motorhomeUnitList.get(i).getMotorhomeModelID() == motorhomeModelList.get(j).getMotorhomeModelID()){
                    motorhomeUnitAndModelList.add(new MotorhomeUnitAndModel(motorhomeUnitList.get(i).getMotorhomeUnitID(), motorhomeUnitList.get(i).getMotorhomeModelID(), motorhomeModelList.get(j).getBrand(),
                            motorhomeModelList.get(j).getModelName(), motorhomeModelList.get(j).getNumberOfBeds(),
                            motorhomeModelList.get(j).getNumberOfSeats(), motorhomeModelList.get(j).getNumberOfSeatBelts(),
                            motorhomeModelList.get(j).getWeight(), motorhomeModelList.get(j).getMaxLoadedWeight(), motorhomeModelList.get(j).getDescriptionNote(),
                            motorhomeUnitList.get(i).getRegistrationYear(), motorhomeUnitList.get(i).getDrivenKilometers(), motorhomeModelList.get(j).getPricePerDay(),
                            motorhomeUnitList.get(i).getRegistration(),
                            motorhomeUnitList.get(i).getNotes()));
                }
            }
        }
        Collections.sort(motorhomeUnitAndModelList);
        return motorhomeUnitAndModelList;
    }
}