package pl.wiktor.demo.infrastructure.database;

import org.springframework.stereotype.Component;
import pl.wiktor.demo.domain.asset.Asset;
import pl.wiktor.demo.infrastructure.database.mock.MockAssetRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomAssetRepository extends MockAssetRepository {

    @Override
    public List<Asset> getAll() {
        return Arrays.asList(this.assets.values().toArray(new Asset[0]));
    }
}
