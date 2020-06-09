package pl.wiktor.demo.domain.asset;

import com.example.types.api.AssetView;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.exception.DomainException;
import pl.wiktor.demo.domain.exception.ExceptionCode;
import pl.wiktor.demo.lib.Assertion;

import java.time.Clock;

@Service
public class AssetService {
    private final AssetRepository assetRepository;
    private final ConversionService conversionService;
    private final Clock clock;

    public AssetService(AssetRepository assetRepository, ConversionService conversionService, Clock clock) {
        this.assetRepository = assetRepository;
        this.conversionService = conversionService;
        this.clock = clock;
    }

    public AssetView createAsset(String name, String category){
        AssetValidator.validate(name, category);
        Asset asset = new Asset(
                ContentId.generate(),
                clock.instant(),
                clock.instant(),
                name,
                Category.valueOf(category)
        );
        assetRepository.save(asset);
        return conversionService.convert(asset, AssetView.class);
    }

    public AssetView updateAsset(String name, String category, ContentId contentId){
        AssetValidator.validate(name, category);

        Asset asset = assetRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID, contentId.getValue()));

        asset.update(name, Category.valueOf(category));

        assetRepository.save(asset);

        return conversionService.convert(asset, AssetView.class);
    }

    public void deleteAsset(ContentId contentId){
        Asset asset = assetRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID, contentId.getValue()));

        assetRepository.delete(asset);
    }

    public AssetView getAsset(ContentId contentId){
        Asset asset = assetRepository.findById(contentId)
                .orElseThrow(() -> new DomainException(ExceptionCode.INVALID_CONTENT_ID, contentId.getValue()));

        return conversionService.convert(asset, AssetView.class);
    }

    private static class AssetValidator{
        static void validate(String name, String category){
            Assertion.notEmpty(name, () -> new DomainException(ExceptionCode.INVALID_NAME, name));
            Assertion.notEmpty(category, () -> new DomainException(ExceptionCode.INVALID_CATEGORY, category));
        }
    }
}
