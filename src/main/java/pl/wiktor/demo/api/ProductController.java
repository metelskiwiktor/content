package pl.wiktor.demo.api;

import com.example.types.api.CreateProductRequest;
import com.example.types.api.ProductView;
import com.example.types.api.UpdateProductRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.demo.api.page.PageResponse;
import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.product.ISIN;
import pl.wiktor.demo.domain.product.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    @ApiOperation("Tworzenie nowego produktu")
    public ProductView createProduct(@RequestBody CreateProductRequest request){
        return productService.createProduct(
                request.getType(),
                ISIN.of(request.getIsin()),
                request.getPrice()
        );
    }

    @PutMapping("/update/{contentId}")
    @ApiOperation("Aktualizowanie produktu")
    public ProductView updateProduct(@RequestBody UpdateProductRequest request, @PathVariable String contentId){
        return productService.updateProduct(
                request.getType(),
                ISIN.of(request.getIsin()),
                request.getPrice(),
                ContentId.of(contentId)
        );
    }

    @DeleteMapping("/delete/{contentId}")
    @ApiOperation("Usuwanie produktu")
    public void deleteProduct(@PathVariable String contentId){
        productService.delete(ContentId.of(contentId));
    }

    @GetMapping("/get/{contentId}")
    @ApiOperation("Zwrócenie produktu")
    public ProductView getProduct(@PathVariable String contentId){
        return productService.getProduct(ContentId.of(contentId));
    }

    @GetMapping("/get-all")
    @ApiOperation("Zwrócenie wszystkich produktów w paginacji")
    public PageResponse<ProductView> getAllProducts(@RequestParam("elements") int elementsPerPage, @RequestParam("page") int page){
        return productService.getAllProducts(elementsPerPage, page);
    }
}
