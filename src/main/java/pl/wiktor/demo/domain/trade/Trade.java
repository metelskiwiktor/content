package pl.wiktor.demo.domain.trade;

import pl.wiktor.demo.domain.AbstractContent;
import pl.wiktor.demo.domain.ContentId;

import java.time.Instant;

public class Trade extends AbstractContent {
    private Double notional;
    private State state;
    private String clientName;


    public Trade(ContentId id, Instant createdTime, Instant lastUpdateTime) {
        super(id, createdTime, lastUpdateTime);
    }

    public Double getNotional() {
        return notional;
    }

    public State getState() {
        return state;
    }

    public String getClientName() {
        return clientName;
    }
}
