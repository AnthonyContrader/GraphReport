package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.CategoriaDTO;
import it.contrader.service.CategoriaService;


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
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController extends AbstractController<CategoriaDTO>{

    @Autowired
    private CategoriaService categoriaService;

    
    @DeleteMapping("/deletebyid")
	public boolean deleteById(@RequestBody long id) {
        try{
        categoriaService.delete(id);
        }catch(Exception e){
            return false;
        }
        return true;
    
    }

    @PostMapping("/insert")
	public boolean insert (@RequestBody String nome){
        try{
        CategoriaDTO dto = new CategoriaDTO();
        categoriaService.insert(dto);
        }catch(Exception e){
            return false;
        }
		return true;
	}
    

}