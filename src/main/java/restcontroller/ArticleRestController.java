package restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import exception.ArticleException;
import model.JsonViews;
import model.inventaire.Article;
import service.ArticleService;
import service.BarService;
import service.StockService;

@RestController
@RequestMapping("api/articles")
public class ArticleRestController {
	
	@Autowired
	private StockService stockService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private BarService barService;
	
	@GetMapping("")
	@JsonView(JsonViews.ArticleWithFournisseur.class)
	public List<Article> getAll() {
		return articleService.getAll();
	}

	@GetMapping("/{idArticle}")
	@JsonView(JsonViews.ArticleWithFournisseur.class)
	public Article getById(@PathVariable Long idArticle) {
		return articleService.getById(idArticle);
	}

//	@GetMapping("/{id}/articles/{idFournisseur}")
//	@JsonView(JsonViews.Common.class)
//	public List<Article> getAllByFournisseur(@PathVariable Long idFournissseur) {
//		return articleService.getByFournisseur(idFournisseur);
//	}

	@GetMapping("/types/{typeArticle}")
	@JsonView(JsonViews.ArticleWithFournisseur.class)
	public List<Article> getAllById(@PathVariable String typeArticle) {
		return articleService.getAllByTypeArticle(typeArticle);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.ArticleWithFournisseur.class)
	@PostMapping("")
	public Article create(@Valid @RequestBody Article article, BindingResult br) {
		if (br.hasErrors()) {
			throw new ArticleException();
		}
		return articleService.create(article);
	}

	@PutMapping("/{idArticle}")
	public Article update(@Valid @RequestBody Article article, BindingResult br, @PathVariable("idArticle") Long idArticle) {
		if (br.hasErrors()) {
			throw new ArticleException();
		}
		if (article.getId()==null) {
			article.setId(idArticle);
		}
		return articleService.update(article);
	}

}
