package pl.wiktor.demo.domain.trade;

import pl.wiktor.demo.domain.AbstractContent;
import pl.wiktor.demo.domain.ContentId;

import java.math.BigDecimal;
import java.time.Instant;

public class Trade extends AbstractContent {
    private BigDecimal notional;
    private State state;
    private String clientName;


    public Trade(ContentId id, Instant createdTime, Instant lastUpdateTime, BigDecimal notional, State state, String clientName) {
        super(id, createdTime, lastUpdateTime);
        this.notional = notional;
        this.state = state;
        this.clientName = clientName;
    }

    public BigDecimal getNotional() {
        return notional;
    }

    public State getState() {
        return state;
    }

    public String getClientName() {
        return clientName;
    }

    public void update(BigDecimal notional, State state, String clientName, Instant updateTime) {
        this.notional = notional;
        this.state = state;
        this.clientName = clientName;
        updateTime(updateTime);
    }
}
