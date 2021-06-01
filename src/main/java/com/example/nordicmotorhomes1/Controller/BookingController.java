package com.example.nordicmotorhomes1.Controller;

import com.example.nordicmotorhomes1.Model.*;
import com.example.nordicmotorhomes1.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class BookingController {

    @Autowired
    MotorhomeModelService motorhomeModelService;
    @Autowired
    CustomerService customerService;
    @Autowired
    BookingService bookingService;
    @Autowired
    MotorhomeUnitService motorhomeUnitService;
    @Autowired
    ExtraService extraService;
    @Autowired
    BookingExtrasService bookingExtrasService;
    @Autowired
    CityService cityService;
    @Autowired
    PriceCalculator priceCalculator;

    /* The method shows a list of all motorhomeModelModels on an HTML site. */
    @GetMapping("/selectMotorhome")
    public String selectMotorhome(Model model){
        List<MotorhomeModelModel> mmList = motorhomeModelService.searchMotorhomeModel("","","","","","","","");
        model.addAttribute("motorhomeModels", mmList);
        return "Bookings/selectMotorhome";
    }

    /* The method takes a motorhomeModelID and from that selects a random moterhomeUnitModel with a
    * corresponding motorhomeModelID. The HTML site it shows displays information about that vehicle,
    * and prompts the user to add a new customer, or an existing customer. */
    @GetMapping("/bookings/addCustomer/{motorhomeModelID}")
    public String createBooking(@PathVariable("motorhomeModelID") int motorhomeModelID, Model model){
        List<MotorhomeUnitModel> muList = motorhomeUnitService.fetchAllByMotorhomeModelID(motorhomeModelID);
        Random rand = new Random();
        MotorhomeUnitModel mu = muList.get(rand.nextInt(muList.size()-1));
        model.addAttribute("motorhomeModel", motorhomeModelService.findMotorhomeModelById(motorhomeModelID));
        model.addAttribute("motorhomeUnit", mu);
        return "Bookings/addCustomer";
    }

    /* The HTML site this method shows prompts the user for information about a new customer. The
    * new customer will be added to the booking the user is currently creating. A motorhomeUnitID is
    * transferred as parameter in the URL. This will be used for the booking the user is creating. */
    @GetMapping("/bookings/newCustomer/{motorhomeUnitID}")
    public String newCustomer(@PathVariable("motorhomeUnitID") int motorhomeUnitID, Model model){
        model.addAttribute("motorhomeUnitID", motorhomeUnitID);
        return "Bookings/newCustomer";
    }

    /* The method creates a new CustomerModel object from the information the user entered on the
    * HTML site. A motorhomeUnitID is transferred as parameter in the URL. This will be used for
    * the booking the user is creating. */
    @PostMapping("/bookings/newCustomer/{motorhomeUnitID}")
    public String newCustomer(@PathVariable("motorhomeUnitID") int motorhomeUnitID, @ModelAttribute CustomerModel customer){
        customerService.addCustomer(customer);
        List<CustomerModel> cList = customerService.fetchAll();
        CustomerModel cNew = cList.get(cList.size()-1);
        return "redirect:/bookings/finalBookingDetails/"+motorhomeUnitID+"/"+cNew.getCustomerID();
    }

    /* The method gets a list with information about all customers in the database. The information
    * is displayed in the HTM site. The user is prompted to chose one to add to the booking they are
    * creating. A motorhomeUnitID is transferred as parameter in the URL. This will be also used for
    * the booking. */
    @GetMapping("/bookings/returningCustomer/{motorhomeUnitID}")
    public String returningCustomer(@PathVariable("motorhomeUnitID") int motorhomeUnitID, Model model){
        List<CustomerModel> cList = customerService.fetchAll();
        model.addAttribute("customers", cList);
        model.addAttribute("motorhomeUnitID", motorhomeUnitID);
        return "Bookings/returningCustomer";
    }

    /* The HTML site this method shows prompts the user for details about the booking they are creating.
    * MotorhomeUnitID and customerID is being transferred in the URL. */
    @GetMapping("/bookings/finalBookingDetails/{motorhomeUnitID}/{customerID}")
    public String finalBookingDetails(@PathVariable("motorhomeUnitID") int motorhomeUnitID, @PathVariable("customerID") int customerID, Model model){
        model.addAttribute("motorhomeUnitID", motorhomeUnitID);
        model.addAttribute("customerID", customerID);
        return "Bookings/finalBookingDetails";
    }

    /* The method takes the information about the booking that the user has just been inputting, along
    * with the motorhomeUnitID and the customerID that has been transferred in the URL, and instantiates
    * a BookingModel object with that information. The information is stored in the database. The
    * bookingID of the latest booking added (ie. the one just created), is transferred in the URL to the
    * next HTML site. */
    @PostMapping("/bookings/finalBookingDetails")
    public String finalBookingDetails(@ModelAttribute BookingModel booking){
        bookingService.addBooking(booking);
        BookingModel bNew = bookingService.fetchLatest();
        return "redirect:/addExtras/" + bNew.getBookingID();
    }

    /* The method takes a list of all available extra items from the database, and displays it on the
    * HTML site. The user is prompted to fill out how many they want to add to the booking. */
    @GetMapping("/addExtras/{bookingID}")
    public String addExtras(@PathVariable("bookingID") int bookingID, Model model){
        List<ExtraModel> eList = extraService.fetchAll();
        model.addAttribute("bookingID", bookingID);
        model.addAttribute("extras", eList);
        return "Bookings/addExtras";
    }

    /* The method takes the information the user put in about the amount of extra items to add to the
    * booking, and instantiates BookingExtrasModel objects. The information is saved in the database.
    * Bookingextra entities with an amount of 0 are deleted. This is to not clutter the database with
    * entities carrying no information. */
    @PostMapping("/addExtras/{bookingID}")
    public String addExtras(
            @PathVariable("bookingID") int bookingID,
            @RequestParam("amount") String amounts
    ){
        List<ExtraModel> eList = extraService.fetchAll();
        ArrayList<Integer> numbersOfItems = new ArrayList<>();
        Scanner seperateAmounts = new Scanner(amounts);
        seperateAmounts.useDelimiter(",");
        while(seperateAmounts.hasNext()){
            String num = seperateAmounts.next();
            try{
                numbersOfItems.add(Integer.parseInt(num));
            }catch(Exception e){
                numbersOfItems.add(0);
            }
        }
        int sizeOfEList = eList.size();
        for(int i = 0; i < sizeOfEList; i++){
            bookingExtrasService.addBookingExtras(
                    new BookingExtrasModel(bookingID, eList.get(i).getExtraID(), numbersOfItems.get(i))
            );
        }
        bookingExtrasService.deleteBookingExtrasWithZeroAmount();
        return "redirect:/viewBookingDetails/" + bookingID;
    }

    /* This methods treat all the data to send to the site that displays all details about a certain booking.
     * It gets data from the motorhome and the customer as well, and also information about which extra
     * items has been added to the booking. Furthermore the total cost of the booking is calculated. */
    @GetMapping("/viewBookingDetails/{bookingID}")
    public String viewBookingDetails(@PathVariable("bookingID") int bookingID, Model model){
        BookingModel b = bookingService.findBookingById(bookingID);
        CustomerModel c = customerService.findCustomerById(b.getCustomerID());
        CityModel city = cityService.findCityByZipcode(c.getZipCode());
        MotorhomeUnitModel mu = motorhomeUnitService.findMotorhomeUnitbyID(b.getMotorhomeUnitID());
        MotorhomeModelModel mm = motorhomeModelService.findMotorhomeModelById(mu.getMotorhomeModelID());
        List<BookingExtrasModel> beList = bookingExtrasService.fetchAllByBookingId(bookingID);
        List<ExtraModel> eList = extraService.fetchAllByBookingId(bookingID);
        ArrayList<ArrayList<String>> extraItems = new ArrayList<>();
        for(int i = 0; i < beList.size(); i++){
            ArrayList<String> extraItem = new ArrayList<>();    //This for-loop makes an ArrayList<String>
            extraItem.add(eList.get(i).getItem());              //with values (0) as the name of the extra-
            extraItem.add(""+beList.get(i).getAmount());        //item, (1) as amounts of items for that one
            extraItem.add(""+eList.get(i).getPrice());          //booking, and (2) as the unit price for
            extraItems.add(extraItem);                          //said item.
        }
        double contractPrice = priceCalculator.getPriceOfContract(
                mm.getPricePerDay(), b, priceCalculator.getDailyPriceOfExtraItems(extraItems)
        );
        model.addAttribute("booking", b);
        model.addAttribute("customer", c);
        model.addAttribute("city", city);
        model.addAttribute("motorhomeUnit", mu);
        model.addAttribute("motorhomeModel", mm);
        model.addAttribute("extraItemList", extraItems);
        model.addAttribute("totalPrice", contractPrice);
        return "Bookings/viewBookingDetails";
    }

    /* The HTML site shown from this page displays information about the booking corresponding to
    * the bookingID transferred in the URL. The site allows the user to edt the information. */
    @GetMapping("/bookings/editBooking/{bookingID}")
    public String editBooking(@PathVariable("bookingID") int bookingID, Model model){
        BookingModel b = bookingService.findBookingById(bookingID);
        model.addAttribute("booking", b);
        return"Bookings/editBooking";
    }

    /* This method saves information about changes to a booking in the database. */
    @PostMapping("/bookings/editBooking")
    public String editBooking(@ModelAttribute BookingModel b){
        bookingService.updateBooking(b.getBookingID(), b);
        return "redirect:/viewBookingDetails/" + b.getBookingID();
    }

    /* The HTML site this page refers to shows information about the customer connected with the
    * booking correspnding to the ID-number from the URL. It then displays a list of all customers
    * in the database and gives the user the opportunity to connect a new customer. */
    @GetMapping("/bookings/changeCustomerOnBooking/{bookingID}")
    public String changeCustomerOnBooking(@PathVariable("bookingID") int bookingID, Model model){
        BookingModel b = bookingService.findBookingById(bookingID);
        CustomerModel c = customerService.findCustomerById(b.getCustomerID());
        CityModel city = cityService.findCityByZipcode(c.getZipCode());
        List<CustomerModel> cList = customerService.fetchAll();
        model.addAttribute("booking", b);
        model.addAttribute("customer", c);
        model.addAttribute("city", city);
        model.addAttribute("cList", cList);
        return "Bookings/changeCustomerOnBooking";
    }

    /* This method updates a booking with a new customerID and saves the change to the database. */
    @GetMapping("/bookings/changeCustomerOnBooking/{bookingID}/{customerID}")
    public String changeCustomerUnitOnBooking(@PathVariable("bookingID") int bookingID, @PathVariable("customerID") int customerID){
        BookingModel b = bookingService.findBookingById(bookingID);
        b.setCustomerID(customerID);
        bookingService.updateBooking(bookingID, b);
        return "redirect:/viewBookingDetails/" + bookingID;
    }

    /* The HTML site this page refers to shows information about the vehicle connected to the
     * booking correspnding to the ID-number from the URL. It then displays a list of all motorhome units
     * available at the duration of the booking and gives the user the opportunity to connect
     * a new motorhome unit. */
    @GetMapping("/bookings/changeMotorhomeUnitOnBooking/{bookingID}")
    public String changeMotorhomeUnitOnBooking(@PathVariable("bookingID") int bookingID, Model model){
        BookingModel b = bookingService.findBookingById(bookingID);
        MotorhomeUnitModel muCurrent = motorhomeUnitService.findMotorhomeUnitbyID(b.getMotorhomeUnitID());
        MotorhomeModelModel mmCurrent = motorhomeModelService.findMotorhomeModelById(muCurrent.getMotorhomeModelID());
        List<MotorhomeModelModel> mmList = motorhomeModelService.searchMotorhomeModel(b.getStartDate(), b.getEndDate(),"","",
                "","","","");
        List<List<MotorhomeUnitModel>> muList = new LinkedList<>();
        for(MotorhomeModelModel mm : mmList){
            muList.add(motorhomeUnitService.searchAvailableMotorhomeUnit(b.getStartDate(), b.getEndDate(), mm.getMotorhomeModelID()));
        }
        model.addAttribute("booking", b);
        model.addAttribute("muCurrent", muCurrent);
        model.addAttribute("mmCurrent", mmCurrent);
        model.addAttribute("mmList", mmList);
        model.addAttribute("muList", muList);
        return"bookings/changeMotorhomeUnitOnBooking";
    }

    /* This method updates a booking with a new motorhomeUnitID and saves the change to the database. */
    @GetMapping("/bookings/changeMotorhomeOnBooking/{bookingID}/{motorhomeUnitID}")
    public String changeMotorhomeUnitOnBooking(@PathVariable("bookingID") int bookingID, @PathVariable("motorhomeUnitID") int motorhomeUnitID){
        BookingModel b = bookingService.findBookingById(bookingID);
        b.setMotorhomeUnitID(motorhomeUnitID);
        bookingService.updateBooking(bookingID, b);
        return "redirect:/viewBookingDetails/" + bookingID;
    }

    /* The HTML site this method refers to shows a list of all additional items, and allows the user to
    * input how many the customer wants to rent. The input fields contain information about how many
    * items are already connected to that booking. */
    @GetMapping("/bookings/addRemoveExtras/{bookingID}")
    public String addRemoveExtras(@PathVariable("bookingID") int bookingID, Model model){
        List<ExtraModel> eList = extraService.fetchAll();
        List<BookingExtrasModel> beList = new ArrayList<>();
        for(ExtraModel e : eList){
            try{
                BookingExtrasModel be = bookingExtrasService.findBookingExtrasByIds(bookingID, e.getExtraID());
                beList.add(be);
            }catch (Exception exception){
                BookingExtrasModel be = new BookingExtrasModel(bookingID, e.getExtraID(), 0);
                beList.add(be);
            }
        }
        model.addAttribute("bookingID", bookingID);
        model.addAttribute("extras", eList);
        model.addAttribute("bookingExtras", beList);
        return "bookings/addRemoveExtras";
    }

    /* The method takes the information the user put in about the amount of extra items to add to the
     * booking, and instantiates BookingExtrasModel objects. The information is saved in the database,
     * but not before existing entities with the corresponding bookingID's are deleted from the database.
     * Bookingextra entities with an amount of 0 are deleted. This is to not clutter the database with
     * entities carrying no information. */
    @PostMapping("/addRemoveExtras/{bookingID}")
    public String addRemoveExtras(
            @PathVariable("bookingID") int bookingID,
            @RequestParam("amount") String amounts
    ){
        List<ExtraModel> eList = extraService.fetchAll();
        ArrayList<Integer> numbersOfItems = new ArrayList<>();
        Scanner seperateAmounts = new Scanner(amounts);
        seperateAmounts.useDelimiter(",");
        while(seperateAmounts.hasNext()){
            String num = seperateAmounts.next();
            try{
                numbersOfItems.add(Integer.parseInt(num));
            }catch(Exception e){
                numbersOfItems.add(0);
            }
        }
        int sizeOfEList = eList.size();
        for(int i = 0; i < sizeOfEList; i++){
            bookingExtrasService.deleteBookingExtras(bookingID, eList.get(i).getExtraID());
            bookingExtrasService.addBookingExtras(
                    new BookingExtrasModel(bookingID, eList.get(i).getExtraID(), numbersOfItems.get(i))
            );
        }
        bookingExtrasService.deleteBookingExtrasWithZeroAmount();
        return "redirect:/viewBookingDetails/" + bookingID;
    }

    /* The HTML site this method refers to shows a list of all bookings. It has fields with which
    * the user can search for specific bookings. Buttons enable the user to view details about
    * the specific booking. */
    @GetMapping("/listBooking")
    public String searchBooking(Model model){
        List<BookingModel> bList = bookingService.fetchAll();
        model.addAttribute("bookings", bList);
        return "Bookings/listBooking";
    }

    /* This method takes parameters from search fields and queries the database for a list of matching
    * bookings. These bookings are displayed on the HTML site with a button allowing the user to
    * view more details about the individual bookings. */
    @PostMapping("/searchBookingParam")
    public String searchBooking(
            @RequestParam(value = "bookingID", required = false) String bid,
            @RequestParam(value = "motorhomeUnitID", required = false) String mid,
            @RequestParam(value = "customerID", required = false) String cid,
            @RequestParam(value = "startDate", required = false) String sd,
            @RequestParam(value = "endDate", required = false) String ed,
            Model model
    ){
        List<BookingModel> bList = bookingService.searchBookings(sd, ed, bid, cid, mid);
        model.addAttribute("bookings", bList);
        return "Bookings/listBooking";
    }


}
