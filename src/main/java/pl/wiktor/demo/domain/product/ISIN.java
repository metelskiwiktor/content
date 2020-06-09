package pl.wiktor.demo.domain.product;

import java.util.Objects;

public class ISIN {
    private String value;

    private ISIN(String value) {
        this.value = value;
    }

    public static ISIN of(String value){
        return new ISIN(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ISIN isin = (ISIN) o;
        return Objects.equals(value, isin.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
