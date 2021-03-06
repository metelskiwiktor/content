package pl.wiktor.demo.api;

import com.example.types.api.AssetView;
import com.example.types.api.CreateAssetRequest;
import com.example.types.api.UpdateAssetRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pl.wiktor.demo.api.page.PageResponse;
import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.asset.AssetService;

@RestController
@RequestMapping("/asset")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping("/create")
    @ApiOperation("Utworzenie nowego nabytku")
    public AssetView createAsset(@RequestBody CreateAssetRequest request){
        return assetService.createAsset(
                request.getName(),
                request.getCategory()
        );
    }

    @PutMapping("/update/{contentId}")
    @ApiOperation("Zaktualizowanie nabytku")
    public AssetView updateAsset(@PathVariable String contentId, @RequestBody UpdateAssetRequest request){
        return assetService.updateAsset(
                request.getName(),
                request.getCategory(),
                ContentId.of(contentId)
        );
    }

    @DeleteMapping("/delete/{contentId}")
    @ApiOperation("Usunięcie nabytku")
    public void deleteAsset(@PathVariable String contentId){
        assetService.deleteAsset(ContentId.of(contentId));
    }

    @GetMapping("/get/{contentId}")
    @ApiOperation("Zwrócenie nabytku")
    public AssetView getAsset(@PathVariable String contentId){
        return assetService.getAsset(ContentId.of(contentId));
    }

    @GetMapping("/get-all")
    @ApiOperation("Zwrócenie wszystkich assetów w paginacji")
    public PageResponse<AssetView> getAllAssets(@RequestParam("elements") int elementsPerPage, @RequestParam("page") int page){
        return assetService.getAllAssets(elementsPerPage, page);
    }
}
