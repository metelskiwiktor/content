package pl.wiktor.demo.domain.trade;

import pl.wiktor.demo.domain.ContentId;

public interface TradeRepository {
    Trade findById(ContentId contentId);
    void save(Trade trade);
}
