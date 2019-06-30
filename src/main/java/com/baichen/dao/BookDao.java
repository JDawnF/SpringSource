package com.baichen.dao;

import org.springframework.stereotype.Repository;

/**
 * @Program: BookDao
 * @Author: baichen
 * @Description:
 */
@Repository
public class BookDao {
    private String label = "1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BookDao [label=" + label + "]";
    }
}
