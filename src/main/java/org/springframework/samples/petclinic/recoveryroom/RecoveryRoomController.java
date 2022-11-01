package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recoveryroom")
public class RecoveryRoomController {

    private final RecoveryRoomService service;

    @Autowired
    public RecoveryRoomController(RecoveryRoomService rrservice){
        this.service = rrservice;
    }

    public static final String VIEW_RECOVERY_ROOM_FORM = "recoveryroom/createOrUpdateRecoveryRoomForm";


    @ModelAttribute("types")
    public List<RecoveryRoomType> getRecoveryRoomTypes(){
        return service.getAllRecoveryRoomTypes();
    }

    @GetMapping(value = "/create")
    public ModelAndView createOrUpdateRecoveryRoom(){
        ModelAndView mav = new ModelAndView(VIEW_RECOVERY_ROOM_FORM);
        mav.addObject(new RecoveryRoom());
        return mav;
    }

    @PostMapping(value = "/create")
    public ModelAndView saveRecoveryRoom(@Valid RecoveryRoom room, BindingResult bd){
        ModelAndView mav;
        if(bd.hasErrors()){
            mav = new ModelAndView(VIEW_RECOVERY_ROOM_FORM);
            mav.addObject("recoveryRoom", room);
            mav.addObject("types", service.getAllRecoveryRoomTypes());
            
        }else{
            try{
                service.save(room);
            }catch(DuplicatedRoomNameException e){
                 System.out.println(e);
            }
            mav = new ModelAndView("welcome");
        }
        return mav;
    }
    
}
