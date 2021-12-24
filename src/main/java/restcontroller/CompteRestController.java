package restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import exception.CompteException;
import model.JsonViews;
import model.comptes.Admin;
import model.comptes.Client;
import model.comptes.Compte;
import model.comptes.Employe;
import service.CompteService;

@RestController
@RequestMapping("/comptes")
public class CompteRestController {
	
	@Autowired
	private CompteService compteService;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Compte> getAll() {
		return compteService.getAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	private Compte getById(@PathVariable Long id) {
		return compteService.getBy(id);
	}
	
	public Compte getBy(Long id) {
		return getById(id);
	}
	
	@PostMapping("/client")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	public Compte create(@Valid @RequestBody Client compte, BindingResult br) {
		if (br.hasErrors()) {
			throw new CompteException();
		}
		compteService.creationClient(compte);
		return compte;
	}
	
	@PostMapping("/admin")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	public Compte create(@Valid @RequestBody Admin compte, BindingResult br) {
		if (br.hasErrors()) {
			throw new CompteException();
		}
		compteService.creationAdmin(compte);
		return compte;
	}
	
	@PostMapping("/employe")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	public Compte create(@Valid @RequestBody Employe compte, BindingResult br) {
		if (br.hasErrors()) {
			throw new CompteException();
		}
		compteService.creationEmploye(compte);
		return compte;
	}
	
}
