package tn.esprit.rh.achat.controllers;


import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.dto.StockDto;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.services.IStockService;

import java.util.List;

@RestController
@Api(tags = "Gestion des stocks")
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockRestController {

    @Autowired
    IStockService stockService;

    @Autowired
    ModelMapper modelMapper;

    // http://localhost:8089/SpringMVC/stock/retrieve-all-stocks
    @GetMapping("/retrieve-all-stocks")
    @ResponseBody
    public List<Stock> getStocks() {
        return stockService.retrieveAllStocks();
    }

    // http://localhost:8089/SpringMVC/stock/retrieve-stock/8
    @GetMapping("/retrieve-stock/{stock-id}")
    @ResponseBody
    public Stock retrieveStock(@PathVariable("stock-id") Long stockId) {
        return stockService.retrieveStock(stockId);
    }

    // http://localhost:8089/SpringMVC/stock/add-stock
    @PostMapping("/add-stock")
    @ResponseBody
    public Stock addStock(@RequestBody StockDto s) {
        Stock stock = modelMapper.map(s, Stock.class);
        return stockService.addStock(stock);
    }

    @DeleteMapping("/remove-stock/{stock-id}")
    @ResponseBody
    public void removeStock(@PathVariable("stock-id") Long stockId) {
        stockService.deleteStock(stockId);
    }

    // http://localhost:8089/SpringMVC/stock/modify-stock
    @PutMapping("/modify-stock")
    @ResponseBody
    public Stock modifyStock(@RequestBody StockDto s) {
        Stock stock = modelMapper.map(s, Stock.class);
        return stockService.updateStock(stock);
    }
}