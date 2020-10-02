package com.ksv.analyticaltool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestType {
    C("waiting timeline"),
    D("query");

    private final String name;
}
