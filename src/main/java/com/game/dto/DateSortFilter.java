package com.game.dto;

import com.game.controller.PlayerOrder;

import java.util.Objects;

public class DateSortFilter {

    private final PlayerOrder order;

    private final Integer pageNumber;

    private final Integer pageSize;

    public DateSortFilter(PlayerOrder order, Integer pageNumber, Integer pageSize) {
        this.order = order == null ? PlayerOrder.ID : order;
        this.pageNumber = pageNumber == null ? 0 : pageNumber;
        this.pageSize = pageSize == null ? 3 : pageSize;
    }

    public PlayerOrder getOrder() {
        return order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateSortFilter)) return false;
        DateSortFilter that = (DateSortFilter) o;
        return getOrder() == that.getOrder() && getPageNumber().equals(that.getPageNumber()) && getPageSize().equals(that.getPageSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getPageNumber(), getPageSize());
    }

    @Override
    public String toString() {
        return "DateSortFilter{" +
                "order=" + order +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
