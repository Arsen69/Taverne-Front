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
import model.fonctionnalitees.CarteFidelite;
import service.CarteFideliteService;

@RestController
@RequestMapping("/cartesFidelites")
public class CarteFideliteRestController {

	@Autowired
	CarteFideliteService carteFideliteService;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<CarteFidelite> getAll() {
		return carteFideliteService.getAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	private CarteFidelite getById(@PathVariable Long id) {
		return carteFideliteService.getBy(id);
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	public CarteFidelite create(@Valid @RequestBody CarteFidelite carte, BindingResult br) {
		if (br.hasErrors()) {
			throw new CompteException();
		}
		carteFideliteService.creation(carte);
		return carte;
	}
	
}
