package pl.wiktor.demo.infrastructure.database;

import org.springframework.stereotype.Component;
import pl.wiktor.demo.domain.trade.Trade;
import pl.wiktor.demo.infrastructure.database.mock.MockTradeRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomTradeRepository extends MockTradeRepository {

    @Override
    public List<Trade> getAll() {
        return Arrays.asList(this.trades.values().toArray(new Trade[0]));
    }
}
