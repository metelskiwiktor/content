package pl.wiktor.demo.api.page;

import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PageResponse<T> {
    private Content<T> content;
    private int page;
    private int elementsInContent;
    private int allPages;
    private int allElements;

    public PageResponse(Content<T> content, int page, int elementsInContent, int allPages, int allElements) {
        this.content = content;
        this.page = page;
        this.elementsInContent = elementsInContent;
        this.allPages = allPages;
        this.allElements = allElements;
    }

    public Content getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getElementsInContent() {
        return elementsInContent;
    }

    public int getAllPages() {
        return allPages;
    }

    public int getAllElements() {
        return allElements;
    }

    public static class Build{
        private final ConversionService conversionService;

        public Build(ConversionService conversionService) {
            this.conversionService = conversionService;
        }

        public <T, Z> PageResponse<T> createPageResponse(int page, int elementsPerPage, Z[] objectsFromDb, Class<T> clazz){
            int allElements = objectsFromDb.length;
            int allPages = (int) Math.ceil((double)objectsFromDb.length / (double)elementsPerPage);

            List<Z> objects = new ArrayList<>();

            for(int i = (page-1)*elementsPerPage; i < (page-1)*elementsPerPage+elementsPerPage; i++){
                try{
                    objects.add(objectsFromDb[i]);
                } catch (Exception e){
                    break;
                }
            }

            List<T> trades = objects.stream()
                    .map(trade -> conversionService.convert(trade, clazz))
                    .collect(Collectors.toList());

            Content<T> content = new Content<>(trades);

            return new PageResponse<>(content, page, objects.size(), allPages, allElements);
        }
    }
}
