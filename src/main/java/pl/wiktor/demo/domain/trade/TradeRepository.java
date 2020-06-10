package pl.wiktor.demo.domain.trade;

import pl.wiktor.demo.domain.ContentId;

import java.util.List;
import java.util.Optional;

public interface TradeRepository {
    Optional<Trade> findById(ContentId contentId);
    void save(Trade trade);
    void delete(Trade trade);
    void update(Trade trade);
    List<Trade> getAll();

}
