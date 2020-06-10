package pl.wiktor.demo.api.page;

import java.util.List;

public class Content<T> {
    private List<T> contents;

    public Content(List<T> contents) {
        this.contents = contents;
    }

    public List<T> getContents() {
        return contents;
    }
}
