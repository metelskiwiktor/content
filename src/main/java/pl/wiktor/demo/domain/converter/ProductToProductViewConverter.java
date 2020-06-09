package pl.wiktor.demo.domain.converter;

import com.example.types.api.ProductView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.wiktor.demo.domain.product.Product;

import java.time.format.DateTimeFormatter;

@Component
public class ProductToProductViewConverter implements Converter<Product, ProductView> {
    private final DateTimeFormatter formatter;

    public ProductToProductViewConverter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public ProductView convert(Product product) {
        ProductView productView = new ProductView();
        productView.setId(product.getId().getValue());
        productView.setCreatedTime(formatter.format(product.getCreatedTime()));
        productView.setLastUpdateTime(formatter.format(product.getLastUpdateTime()));
        productView.setIsin(product.getIsin().getValue());
        productView.setType(product.getType().getValue());
        productView.setPrice(product.getPrice());

        return productView;
    }
}
