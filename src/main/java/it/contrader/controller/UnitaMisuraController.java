package it.contrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.UnitaMisuraDTO;
import it.contrader.service.UnitaMisuraService;


/**
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 * 
 * @param<UserDTO>
 * 
 * @see AbstractController
 *
 */
@RestController
@RequestMapping("/unitamisura")
@CrossOrigin(origins = "http://localhost:4200")
public class UnitaMisuraController extends AbstractController<UnitaMisuraDTO>{

    @Autowired
    private UnitaMisuraService unitamisuraService;

    
    @DeleteMapping("/deletebyid")
	public boolean deleteById(@RequestBody long id) {
        try{
        unitamisuraService.delete(id);
        }catch(Exception e){
            return false;
        }
        return true;
    
    }

    @PostMapping("/insertbynome")
	public boolean insertByNome (@RequestBody String nome){
        try{
        UnitaMisuraDTO dto = new UnitaMisuraDTO();
        unitamisuraService.insert(dto);
        }catch(Exception e){
            return false;
        }
		return true;
    }
    
    @PostMapping("/findAll")
    public List<UnitaMisuraDTO> findAll(@RequestParam("cerca") String daCercare){
        return unitamisuraService.findAll(daCercare);
    }
    
    

}