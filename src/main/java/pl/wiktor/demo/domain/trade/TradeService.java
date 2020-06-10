package pl.wiktor.demo.domain.trade;

import com.example.types.api.TradeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import pl.wiktor.demo.api.page.PageResponse;
import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.exception.DomainException;
import pl.wiktor.demo.domain.exception.ExceptionCode;
import pl.wiktor.demo.lib.Assertion;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.List;

@Service
public class TradeService {
    private final Logger logger = LoggerFactory.getLogger(TradeService.class);
    private final TradeRepository tradeRepository;
    private final Clock clock;
    private final ConversionService conversionService;

    public TradeService(TradeRepository tradeRepository, Clock clock, ConversionService conversionService) {
        this.tradeRepository = tradeRepository;
        this.clock = clock;
        this.conversionService = conversionService;
    }

    public TradeView createTrade(BigDecimal notional, State state, String clientName) {
        logger.info("Create trade with notional '{}', state '{}', client name '{}'", notional, state, clientName);

        TradeValidator.validate(notional, clientName);

        Trade trade = new Trade(
                ContentId.generate(),
                clock.instant(),
                clock.instant(),
                notional,
                state,
                clientName
        );

        tradeRepository.save(trade);

        return conversionService.convert(trade, TradeView.class);
    }

    public TradeView updateTrade(BigDecimal notional, State state, String clientName, ContentId contentId) {
        logger.info("Update trade with notional '{}', state '{}', client name '{}' and id '{}'", notional, state, clientName, contentId.getValue());

        TradeValidator.validate(notional, clientName);

        Trade trade = tradeRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID));

        trade.update(notional, state, clientName, clock.instant());

        tradeRepository.save(trade);

        return conversionService.convert(trade, TradeView.class);
    }

    public void deleteTrade(ContentId contentId) {
        logger.info("Delete trade with id '{}'", contentId);

        Trade trade = tradeRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID));

        tradeRepository.delete(trade);
    }

    public TradeView getTrade(ContentId contentId) {
        logger.info("Get trade with id '{}'", contentId.getValue());

        Trade trade = tradeRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID));

        return conversionService.convert(trade, TradeView.class);
    }

    public PageResponse<TradeView> getAllTrades(int elementsPerPage, int page) {
        logger.info("Get all trades");

        List<Trade> trades = tradeRepository.getAll();

        return new PageResponse.Build(conversionService).createPageResponse(page, elementsPerPage, trades.toArray(), TradeView.class);
    }


    private static class TradeValidator {
        static void validate(BigDecimal notional, String clientName) {
            Assertion.notEmpty(clientName, () -> new DomainException(ExceptionCode.INVALID_CLIENT_NAME, clientName));
            Assertion.isTrue(notional.compareTo(BigDecimal.ZERO) > 0, () -> new DomainException(ExceptionCode.INVALID_NOTIONAL, notional));
        }
    }
}
