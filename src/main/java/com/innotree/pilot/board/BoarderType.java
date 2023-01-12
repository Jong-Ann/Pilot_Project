package com.innotree.pilot.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum BoarderType {
    NOTICE("NOTICE"),
    FAQ("FAQ"),
    QNA("QNA");
    private String name;
    BoarderType(String name) {
        this.name = name;
    }
}
