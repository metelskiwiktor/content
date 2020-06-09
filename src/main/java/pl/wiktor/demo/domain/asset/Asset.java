package pl.wiktor.demo.domain.asset;

import pl.wiktor.demo.domain.AbstractContent;
import pl.wiktor.demo.domain.ContentId;

import java.time.Instant;

public class Asset extends AbstractContent {
    private String name;
    private Category category;

    public Asset(ContentId id, Instant createdTime, Instant lastUpdateTime, String name, Category category) {
        super(id, createdTime, lastUpdateTime);
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void update(String name, Category category){
        this.name = name;
        this.category = category;
    }
}
