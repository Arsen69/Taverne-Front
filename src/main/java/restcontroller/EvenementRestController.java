package restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;


import model.JsonViews;
import model.fonctionnalitees.Events;
import service.EvenementService;

@RestController
@RequestMapping("/api/Evenement")
public class EvenementRestController {

	@Autowired
	private EvenementService evenementService;

	@GetMapping("")
	@JsonView(JsonViews.Evenement.class)
	public List<Events> getAll(){
		return evenementService.getAll();
	}
	
	@GetMapping("/jour")
	@JsonView(JsonViews.Evenement.class)
	public List<Events> getByJour() {
		return evenementService.getAll();
	}
	
	@GetMapping("/Bar")
	@JsonView(JsonViews.Evenement.class)
	public List<Events> getByBar() {
		return evenementService.getAll();
	}
	
	@GetMapping("/Employe")
	@JsonView(JsonViews.Evenement.class)
	public List<Events> getByEmploye() {
		return evenementService.getAll();
	}
	
	
//	@ResponseStatus(code = HttpStatus.CREATED)
//	@JsonView(JsonViews.Evenement.class)
//	@PostMapping("")
//	public Compagnon create(@Valid @RequestBody Compagnon compagnon, BindingResult br) {
//		if (br.hasErrors()) {
//			throw new CompagnonException();
//		}
//		compagnonService.creationOuModification(compagnon);
//		return compagnon;
//	}
//
//	
//	
//	
//	
//	
//	@JsonView(JsonViews.Common.class)
//	@PutMapping("/{id}")
//	public Compagnon replace(@Valid @RequestBody Compagnon compagnon, BindingResult br, @PathVariable("id") Long id) {
//		compagnonService.creationOuModification(compagnon);
//		return compagnonService.getById(id);
//	}
//
//	@JsonView(JsonViews.Common.class)
//	@PatchMapping("/{id}")
//	public Compagnon update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
//		Compagnon compagnon = compagnonService.getById(id);
//		fields.forEach((k, v) -> {
//			Field field = ReflectionUtils.findField(Compagnon.class, k);
//			ReflectionUtils.makeAccessible(field);
//			ReflectionUtils.setField(field, compagnon, v);
//		});
//		compagnonService.creationOuModification(compagnon);
//		return compagnon;
//	}
//
//	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable("id") Long id) {
//		compagnonService.suppression(id);
//	}
	
	
	
	
	
}
