package com.msql.dynamicdatasource.modal;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1435515995276255188L;

    private long id;
    private String name;
    private long age;

}
