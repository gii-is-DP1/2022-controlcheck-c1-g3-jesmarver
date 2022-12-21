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

    private static final String VIEW_CREATE_OR_UPDATE_RECOVERY_ROOM = "recoveryroom/createOrUpdateRecoveryRoomForm";

    private RecoveryRoomService service;

    @Autowired
    public RecoveryRoomController(RecoveryRoomService rService){
        this.service = rService;
    }


    @ModelAttribute("types")
    public List<RecoveryRoomType> poblateTypes(){
        return service.getAllRecoveryRoomTypes();
    }

    @GetMapping(value="/create")
    public ModelAndView createOrUpdateRecoveryRoomForm(){
        ModelAndView mav = new ModelAndView(VIEW_CREATE_OR_UPDATE_RECOVERY_ROOM);
        RecoveryRoom room = new RecoveryRoom();

        mav.addObject("recoveryRoom", room);
        return mav;
    }

    @PostMapping(value="/create")
    public ModelAndView createOrUpdateRecoveryRoom(@Valid RecoveryRoom room, BindingResult br){
        ModelAndView mav;

        if(!br.hasErrors()){

            try{
                service.save(room);
            }catch(DuplicatedRoomNameException e){
                System.out.println("Exception "+e);
            }
            mav = new ModelAndView("welcome");
        }else{
            mav = new ModelAndView(VIEW_CREATE_OR_UPDATE_RECOVERY_ROOM);
            mav.addObject("recoveryRoom", room);
        }

        return mav;
    }

    
}
