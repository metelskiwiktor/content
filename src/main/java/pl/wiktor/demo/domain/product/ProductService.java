package pl.wiktor.demo.domain.product;

import com.example.types.api.ProductView;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.exception.DomainException;
import pl.wiktor.demo.domain.exception.ExceptionCode;
import pl.wiktor.demo.lib.Assertion;

import java.time.Clock;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ConversionService conversionService;
    private final Clock clock;

    public ProductService(ProductRepository productRepository, ConversionService conversionService, Clock clock) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;
        this.clock = clock;
    }

    public ProductView createProduct(Type type, ISIN isin, double price){
        ProductValidator.validate(price);

        Product product = new Product(
                ContentId.generate(),
                clock.instant(),
                clock.instant(),
                type,
                isin,
                price
        );

        productRepository.save(product);

        return conversionService.convert(product, ProductView.class);
    }

    private static class ProductValidator{
        public static void validate(double price){
            Assertion.isTrue(price > 0 && digitsAfterDot(price) <= 2, () -> new DomainException(ExceptionCode.INVALID_PRICE));
        }

        private static int digitsAfterDot(double value){
            return (int)((value + 0.001) * 100) % 100;
        }
    }
}
