package pl.wiktor.demo.domain.product;

import pl.wiktor.demo.domain.AbstractContent;
import pl.wiktor.demo.domain.ContentId;

import java.math.BigDecimal;
import java.time.Instant;

public class Product extends AbstractContent {
    private Type type;
    private ISIN isin;
    private BigDecimal price;

    public Product(ContentId id, Instant createdTime, Instant lastUpdateTime, Type type, ISIN isin, BigDecimal price) {
        super(id, createdTime, lastUpdateTime);
        this.type = type;
        this.isin = isin;
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public ISIN getIsin() {
        return isin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void update(Type type, ISIN isin, BigDecimal price, Instant updateTime) {
        this.type = type;
        this.isin = isin;
        this.price = price;
        updateTime(updateTime);
    }
}
