package pl.wiktor.demo.domain.converter;

import com.example.types.api.TradeView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.wiktor.demo.domain.trade.Trade;

import java.time.format.DateTimeFormatter;

@Component
public class TradeToTradeViewConverter implements Converter<Trade, TradeView> {
    private final DateTimeFormatter formatter;

    public TradeToTradeViewConverter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public TradeView convert(Trade trade) {
        TradeView  tradeView = new TradeView();
        tradeView.setId(trade.getId().getValue());
        tradeView.setCreatedTime(formatter.format(trade.getCreatedTime()));
        tradeView.setLastUpdateTime(formatter.format(trade.getLastUpdateTime()));
        tradeView.setNotional(trade.getNotional());
        tradeView.setState(trade.getState().getValue());

        return tradeView;
    }
}
