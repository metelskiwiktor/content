package pl.wiktor.demo.infrastructure.database.mock;

import pl.wiktor.demo.domain.ContentId;
import pl.wiktor.demo.domain.asset.Asset;
import pl.wiktor.demo.domain.asset.AssetRepository;

import java.util.*;

public abstract class MockAssetRepository implements AssetRepository {
    protected final Map<ContentId, Asset> assets;

    public MockAssetRepository() {
        assets = new HashMap<>();
    }

    @Override
    public Optional<Asset> findById(ContentId contentId) {
        return Optional.ofNullable(assets.get(contentId));
    }

    @Override
    public void delete(Asset asset) {
        assets.remove(asset.getId());
    }

    @Override
    public void save(Asset asset) {
        assets.put(asset.getId(), asset);
    }

    @Override
    public void update(Asset asset) {
        assets.put(asset.getId(), asset);
    }
}
