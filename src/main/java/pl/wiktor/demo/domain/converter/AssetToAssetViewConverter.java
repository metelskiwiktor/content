package pl.wiktor.demo.domain.converter;

import com.example.types.api.AssetView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.wiktor.demo.domain.asset.Asset;

import java.time.format.DateTimeFormatter;

@Component
public class AssetToAssetViewConverter implements Converter<Asset, AssetView> {
    private final DateTimeFormatter formatter;

    public AssetToAssetViewConverter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public AssetView convert(Asset asset) {
        AssetView assetView = new AssetView();
        assetView.setName(asset.getName());
        assetView.setCategory(asset.getCategory().toString());
        assetView.setCreatedTime(formatter.format(asset.getCreatedTime()));
        assetView.setLastUpdateTime(formatter.format(asset.getLastUpdateTime()));
        assetView.setId(asset.getId().getValue());

        return assetView;
    }
}
