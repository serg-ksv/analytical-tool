package com.ksv.analyticaltool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseType {
    P("first answer"),
    N("next answer");

    private final String name;
}
