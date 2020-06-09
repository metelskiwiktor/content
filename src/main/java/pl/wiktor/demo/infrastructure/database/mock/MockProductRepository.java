package pl.wiktor.demo.infrastructure.database.mock;

import org.springframework.stereotype.Component;
import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.product.Product;
import pl.wiktor.demo.domain.product.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MockProductRepository implements ProductRepository {
    private final Map<ContentId, Product> products;

    public MockProductRepository() {
        this.products = new HashMap<>();
    }

    @Override
    public Optional<Product> findById(ContentId contentId) {
        return Optional.of(products.get(contentId));
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void delete(Product product) {
        products.remove(product.getId());
    }

    @Override
    public void update(Product product) {
        products.put(product.getId(), product);
    }
}
