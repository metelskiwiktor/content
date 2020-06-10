package pl.wiktor.demo.domain.product;

import com.example.types.api.ProductView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import pl.wiktor.demo.api.page.PageResponse;
import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.exception.DomainException;
import pl.wiktor.demo.domain.exception.ExceptionCode;
import pl.wiktor.demo.lib.Assertion;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.List;

@Service
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ConversionService conversionService;
    private final Clock clock;

    public ProductService(ProductRepository productRepository, ConversionService conversionService, Clock clock) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;
        this.clock = clock;
    }

    public ProductView createProduct(Type type, ISIN isin, BigDecimal price) {
        logger.info("Create product with type '{}', isin '{}' and price '{}'", type, isin.getValue(), price);

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

    public ProductView updateProduct(Type type, ISIN isin, BigDecimal price, ContentId contentId) {
        logger.info("Update product with type '{}', isin '{}' and price '{}'", type, isin.getValue(), price);

        ProductValidator.validate(price);

        Product product = productRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID));

        product.update(type, isin, price, clock.instant());

        productRepository.save(product);

        return conversionService.convert(product, ProductView.class);
    }

    public void delete(ContentId contentId) {
        logger.info("Delete product with id '{}'", contentId.getValue());

        Product product = productRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID));

        productRepository.delete(product);
    }

    public ProductView getProduct(ContentId contentId) {
        logger.info("Get product with id '{}'", contentId.getValue());

        Product product = productRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID));

        return conversionService.convert(product, ProductView.class);
    }

    public PageResponse<ProductView> getAllProducts(int elementsPerPage, int page) {
        logger.info("Get all products");

        List<Product> products = productRepository.getAll();

        return new PageResponse.Build(conversionService).createPageResponse(page, elementsPerPage, products.toArray(), ProductView.class);
    }


    private static class ProductValidator {
        static void validate(BigDecimal price) {
            Assertion.isTrue(price.compareTo(BigDecimal.ZERO) > 0 && getNumberOfDecimalPlaces(price) <= 2, () -> new DomainException(ExceptionCode.INVALID_PRICE, price));
        }

        static int getNumberOfDecimalPlaces(BigDecimal value){
            return Math.max(0, value.stripTrailingZeros().scale());
        }
    }
}
