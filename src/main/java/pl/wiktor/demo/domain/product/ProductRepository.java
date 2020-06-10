package pl.wiktor.demo.domain.product;

import pl.wiktor.demo.domain.ContentId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(ContentId contentId);
    void save(Product product);
    void delete(Product product);
    void update(Product product);
    List<Product> getAll();
}
