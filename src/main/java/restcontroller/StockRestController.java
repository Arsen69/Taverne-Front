package restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Stock> getAllStocks(@PathVariable Long id) {
		return stockService.getAllByBar(id);
	}

	@GetMapping("/{id}/stocks/{idStock}")
	@JsonView(JsonViews.Common.class)
	public Stock getStockByIdStock(@PathVariable("id") Long idBar, @PathVariable Long idStock) {
		return stockService.getByIdStockAndBar(idStock, idBar);
	}

	@PutMapping("/{id}/stocks/{idStock}")
	@JsonView(JsonViews.Common.class)
	public Stock updateLimit(@Valid @RequestBody Stock stock, BindingResult br, @PathVariable("id") Long idBar,
			@PathVariable Long idStock) {
		if (stock.getIdStock() == null) {
			stock.setIdStock(idStock);
		}
		return stockService.getByIdStockAndBar(idStock, idBar);
	}

}
