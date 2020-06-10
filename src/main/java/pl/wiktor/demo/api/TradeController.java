package pl.wiktor.demo.api;

import com.example.types.api.CreateTradeRequest;
import com.example.types.api.TradeView;
import com.example.types.api.UpdateTradeRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.demo.api.page.PageResponse;
import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.trade.TradeService;

@RestController
@RequestMapping("/trade")
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping("/create")
    @ApiOperation("Utworzenie nowego handlu")
    public TradeView createTrade(@RequestBody CreateTradeRequest request){
        return tradeService.createTrade(
                request.getNotional(),
                request.getState(),
                request.getClientName()
        );
    }

    @PutMapping("/update/{contentId}")
    @ApiOperation("Zaktualizowanie handlu")
    public TradeView updateTrade(@RequestBody UpdateTradeRequest request, @PathVariable String contentId){
        return tradeService.updateTrade(
                request.getNotional(),
                request.getState(),
                request.getClientName(),
                ContentId.of(contentId)
        );
    }

    @DeleteMapping("/delete/{contentId}")
    @ApiOperation("Usunięcie handlu")
    public void deleteTrade(@PathVariable String contentId){
        tradeService.deleteTrade(ContentId.of(contentId));
    }

    @GetMapping("/get/{contentId}")
    @ApiOperation("Zwrócenie handlu")
    public TradeView getTrade(@PathVariable String contentId){
        return tradeService.getTrade(ContentId.of(contentId));
    }

    @GetMapping("/get-all")
    @ApiOperation("Zwrócenie wszystkich handlów w paginacji")
    public PageResponse<TradeView> getAllTrades(@RequestParam("elements") int elementsPerPage, @RequestParam("page") int page){
        return tradeService.getAllTrades(elementsPerPage, page);
    }
}
