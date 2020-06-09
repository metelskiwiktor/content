package pl.wiktor.demo.domain.product;

import pl.wiktor.demo.domain.AbstractContent;
import pl.wiktor.demo.domain.ContentId;

import java.time.Instant;

public class Product extends AbstractContent {
    private Type type;
    private ISIN isin;
    private Double price;

    public Product(ContentId id, Instant createdTime, Instant lastUpdateTime, Type type, ISIN isin, Double price) {
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

    public Double getPrice() {
        return price;
    }
}
