package org.springframework.samples.petclinic.recoveryroom;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecoveryRoomController {
	
	private static final String VIEW_RR_CREATE = "recoveryroom/createOrUpdateRecoveryRoomForm";
	
	private static final String WELCOME = "welcome";
	
	private final RecoveryRoomService rrService;
	
	@Autowired
	public RecoveryRoomController(RecoveryRoomService rrService) {
		this.rrService = rrService;
	}
	
	@ModelAttribute("types")
	public Collection<RecoveryRoomType> populatedRoomTypes(){
		return this.rrService.getAllRecoveryRoomTypes();
	}
	
	
	@GetMapping(value = "/recoveryroom/create")
	public String initCreationForm(ModelMap model) {
		RecoveryRoom recoveryRoom = new RecoveryRoom();
		model.put("recoveryRoom", recoveryRoom);
		return VIEW_RR_CREATE;
	}

	@PostMapping(value = "/recoveryroom/create")
	public String processCreationForm(@Valid RecoveryRoom recoveryRoom, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("recoveryRoom", recoveryRoom);
			return VIEW_RR_CREATE;
		}
		else {
                    try{
                    	this.rrService.save(recoveryRoom);
                    }catch(Exception ex){
                        result.rejectValue("name", "duplicate", "already exists");
                        return VIEW_RR_CREATE;
                    }
                    return WELCOME;
		}
	}
	
	
    
}
