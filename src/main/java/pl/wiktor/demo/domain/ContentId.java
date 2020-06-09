package pl.wiktor.demo.domain;

import java.util.Objects;
import java.util.UUID;

public class ContentId {
    private String value;

    private ContentId(String value){
        this.value = value;
    }

    public static ContentId of(String id){
        return new ContentId(id);
    }

    public static ContentId generate(){
        return new ContentId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentId contentId = (ContentId) o;
        return Objects.equals(value, contentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ContentId{" +
                "value='" + value + '\'' +
                '}';
    }
}
