package pl.wiktor.demo.domain;

import java.time.Instant;

public abstract class AbstractContent {
    private ContentId id;
    private Instant createdTime;
    private Instant lastUpdateTime;

    public AbstractContent(ContentId id, Instant createdTime, Instant lastUpdateTime) {
        this.id = id;
        this.createdTime = createdTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public ContentId getId() {
        return id;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public Instant getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void updateTime(Instant instant){

    }
}
