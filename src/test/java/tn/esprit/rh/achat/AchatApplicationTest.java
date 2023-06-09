package tn.esprit.rh.achat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.IStockService;

@Slf4j
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AchatApplicationTest {
	@Autowired
	 IStockService iStock;
	@MockBean
	StockRepository stockRepository;
	
	  @Test
	  @Order(0)
	    public void getStocksTest() {
	       
	        when(stockRepository.findAll()).thenReturn(Stream
	                .of(new Stock("P001", 12,12))
	                .collect(Collectors.toList()));
	        assertEquals(1, iStock.retrieveAllStocks().size());
	    }
	  
	  @Test
	  @Order(1)
	  public void retrieveStock() {
		  Stock s = new Stock("P001", 12,12);
		Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
		Stock stock=iStock.retrieveStock((long) 2);
		assertNotNull(stock);
		log.info("get"+stock.toString());
		verify(stockRepository).findById(Mockito.anyLong());
	  }

	
	

}
