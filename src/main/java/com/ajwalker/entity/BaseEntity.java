package com.ajwalker.entity;

public class BaseEntity {
    protected Long id;
    protected Integer state;
    

    public BaseEntity() {
    }

    public BaseEntity(Integer state) {
        this.state = state;
    }

    public BaseEntity(Long id, Integer state) {
        this.id = id;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", state=" + state +
                '}';
    }
}