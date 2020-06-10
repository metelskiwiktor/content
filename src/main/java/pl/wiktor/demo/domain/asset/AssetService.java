package pl.wiktor.demo.domain.asset;

import com.example.types.api.AssetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import pl.wiktor.demo.api.page.PageResponse;
import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.exception.DomainException;
import pl.wiktor.demo.domain.exception.ExceptionCode;
import pl.wiktor.demo.lib.Assertion;

import java.time.Clock;
import java.util.List;

@Service
public class AssetService {
    private final Logger logger = LoggerFactory.getLogger(AssetService.class);
    private final AssetRepository assetRepository;
    private final ConversionService conversionService;
    private final Clock clock;

    public AssetService(AssetRepository assetRepository, ConversionService conversionService, Clock clock) {
        this.assetRepository = assetRepository;
        this.conversionService = conversionService;
        this.clock = clock;
    }

    public AssetView createAsset(String name, Category category){
        logger.info("Create asset with name '{}' and category '{}'", name, category);

        AssetValidator.validate(name);

        Asset asset = new Asset(
                ContentId.generate(),
                clock.instant(),
                clock.instant(),
                name,
                category
        );
        assetRepository.save(asset);
        return conversionService.convert(asset, AssetView.class);
    }

    public AssetView updateAsset(String name, Category category, ContentId contentId){
        logger.info("Update asset with name '{}', category '{}' and id '{}'", name, category, contentId.getValue());

        AssetValidator.validate(name);

        Asset asset = assetRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID, contentId.getValue()));

        asset.update(name, category, clock.instant());

        assetRepository.save(asset);

        return conversionService.convert(asset, AssetView.class);
    }

    public void deleteAsset(ContentId contentId){
        logger.info("Delete asset with id '{}'", contentId.getValue());

        Asset asset = assetRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID, contentId.getValue()));

        assetRepository.delete(asset);
    }

    public AssetView getAsset(ContentId contentId){
        logger.info("Get asset with id '{}'", contentId.getValue());

        Asset asset = assetRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID, contentId.getValue()));

        return conversionService.convert(asset, AssetView.class);
    }

    public PageResponse<AssetView> getAllAssets(int elementsPerPage, int page) {
        logger.info("Get all assets");

        List<Asset> assets = assetRepository.getAll();

        return new PageResponse.Build(conversionService).createPageResponse(page, elementsPerPage, assets.toArray(), AssetView.class);
    }
    private static class AssetValidator{
        static void validate(String name){
            Assertion.notEmpty(name, () -> new DomainException(ExceptionCode.INVALID_NAME, name));
        }
    }
}
