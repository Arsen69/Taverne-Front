package restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import model.JsonViews;
import model.inventaire.Bar;
import model.inventaire.Stock;
import service.BarService;
import service.StockService;

@RestController
@RequestMapping("api/bar")
public class StockRestController {

	@Autowired
	private StockService stockService;

	@Autowired
	private BarService barService;

	@GetMapping("/{id}/stocks")
	@JsonView(JsonViews.Common.class)
	public List<Stock> getAll(@PathVariable Long id) {
		return stockService.getAllByBar(id);
	}
	

}
