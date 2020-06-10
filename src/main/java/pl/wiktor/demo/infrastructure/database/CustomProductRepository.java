package pl.wiktor.demo.infrastructure.database;

import org.springframework.stereotype.Component;
import pl.wiktor.demo.domain.product.Product;
import pl.wiktor.demo.infrastructure.database.mock.MockProductRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomProductRepository extends MockProductRepository {

    @Override
    public List<Product> getAll() {
        return Arrays.asList(this.products.values().toArray(new Product[0]));
    }
}