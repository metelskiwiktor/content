package pl.wiktor.demo.domain.asset;

import pl.wiktor.demo.domain.ContentId;

import java.util.Optional;

public interface AssetRepository {
    Optional<Asset> findById(ContentId contentId);
    void delete(Asset asset);
    void save(Asset asset);
    void update(Asset asset);
}
