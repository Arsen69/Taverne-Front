package restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import exception.BoissonException;
import model.JsonViews;
import model.inventaire.Alcool;
import model.inventaire.Boisson;
import model.inventaire.Soft;
import service.BoissonService;

@RestController
@RequestMapping("/boisson")

public class BoissonRestController {
	
	@Autowired 
	private BoissonService boissonService;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Boisson> getAll() {
		return boissonService.getAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Boisson getById(@PathVariable("id") Long id) {
		return boissonService.getById(id);
	}
	

	//Post soft	
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("/soft")
	public Soft create(@Valid @RequestBody Soft soft, BindingResult br) {
		if(br.hasErrors()) {
			throw new BoissonException();
		}
		boissonService.create(soft);
		return soft;
	}
	
	//Post alcool	
		@ResponseStatus(code = HttpStatus.CREATED)
		@JsonView(JsonViews.Common.class)
		@PostMapping("/alcool")
		public Alcool create(@Valid @RequestBody Alcool alcool, BindingResult br) {
			if(br.hasErrors()) {
				throw new BoissonException();
			}
			boissonService.create(alcool);
			return alcool;
		}
		

	//Put Soft
	@JsonView(JsonViews.Common.class)
	@PutMapping("/soft")
	public Soft replace(@Valid @RequestBody Soft soft, BindingResult br, @PathVariable("id") Long id) {
		boissonService.update(soft);
		return soft;
	}
	
	//Put Alcool
		@JsonView(JsonViews.Common.class)
		@PutMapping("/alcool")
		public Alcool replace(@Valid @RequestBody Alcool alcool, BindingResult br, @PathVariable("id") Long id) {
			boissonService.update(alcool);
			return alcool;
		}
//	
//	@JsonView(JsonViews.Common.class)
//	@PatchMapping("/{id}")
//	public Compagnon update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
//		Compagnon compagnon = compagnonService.getById(id);
//		fields.forEach((k,v) -> {
//			Field field = ReflectionUtils.findField(Compagnon.class,k);
//			ReflectionUtils.makeAccessible(field);
//			ReflectionUtils.setField(field, compagnon, v);
//		
//		});
//		compagnonService.creationOuModification(compagnon);
//		return compagnon;
//	}
//	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		boissonService.suppression(id);
	}
	
	
}

