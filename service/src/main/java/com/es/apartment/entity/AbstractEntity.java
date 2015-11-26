package com.es.apartment.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (o.getClass() != getClass())) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) o;
        if (id == null) {
            if (that.id != null) {
                return false;
            }
        }
        else if (!id.equals(that.id)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
