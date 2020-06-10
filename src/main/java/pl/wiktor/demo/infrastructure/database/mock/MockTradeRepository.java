package pl.wiktor.demo.infrastructure.database.mock;

import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.trade.Trade;
import pl.wiktor.demo.domain.trade.TradeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class MockTradeRepository implements TradeRepository {
    protected Map<ContentId, Trade> trades;

    public MockTradeRepository() {
        trades = new HashMap<>();
    }

    @Override
    public Optional<Trade> findById(ContentId contentId) {
        return Optional.ofNullable(trades.get(contentId));
    }

    @Override
    public void save(Trade trade) {
        trades.put(trade.getId(), trade);
    }

    @Override
    public void delete(Trade trade) {
        trades.remove(trade.getId());
    }

    @Override
    public void update(Trade trade) {
        trades.put(trade.getId(), trade);
    }
}
