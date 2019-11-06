package io.yodo.whisper.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.Collection;

@SuppressWarnings("unused")
public class PageResponse<T> {

    private Collection<T> items;

    @JsonProperty("page")
    private int pageNum;

    @JsonProperty("page_size")
    private int pageSize;

    public PageResponse() {
    }

    private PageResponse(Collection<T> items, int pageNum, int pageSize) {
        this.items = items;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(page.getContent(), page.getNumber()+1, page.getSize());
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = items;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
