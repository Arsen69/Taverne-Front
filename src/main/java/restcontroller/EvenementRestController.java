package restcontroller;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import exception.EvenementException;
import model.JsonViews;
import model.JsonViews.Evenement;
import model.fonctionnalitees.Events;
import model.inventaire.Bar;
import service.EvenementService;

@RestController
@RequestMapping("/api/evenement")
public class EvenementRestController {

	@Autowired
	private EvenementService evenementService;

	@GetMapping("")
	@JsonView(JsonViews.Evenement.class)
	public List<Events> getAll(){
		return evenementService.getAll();
	}

	@GetMapping("/Bar/{idBar}")
	@JsonView(JsonViews.Evenement.class)
	public List<Events> getByIdBar(@PathVariable Long idBar) {
		return evenementService.getByIdBar(idBar);
	}
	
	@GetMapping("/Employe/{id}")
	@JsonView(JsonViews.Evenement.class)
	public List<Events> getByIdEmploye(@PathVariable Long id) {
		return evenementService.getByIdEmploye(id);
	}
	
	/////////////////////////////////////////////
	@GetMapping("/Jour/{jour}")
	@JsonView(JsonViews.Evenement.class)
	public List<Events> getByJour(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate jour) {
		return evenementService.getByJour(jour);
	}
	/////////////////////////////////////////////////
	
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Evenement.class)
	@PostMapping("")
	public Events create(@Valid @RequestBody Events evenement, BindingResult br) {
		if (br.hasErrors()) {
			throw new EvenementException();
		}
		evenementService.creationOuModification(evenement);
		return evenement;
	}

	
	@JsonView(JsonViews.Common.class)
	@PutMapping("/{id}")
	public Events replace(@Valid @RequestBody Events evenement, BindingResult br, @PathVariable("id") Long id) {
		evenementService.creationOuModification(evenement);
		return evenementService.getById(id);
	}

	@JsonView(JsonViews.Common.class)
	@PatchMapping("/{id}")
	public Events update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
		Events evenement = evenementService.getById(id);
		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Events.class, k);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, evenement, v);
		});
		evenementService.creationOuModification(evenement);
		return evenement;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		evenementService.suppression(id);
	}
	
	
	
	
	
}
