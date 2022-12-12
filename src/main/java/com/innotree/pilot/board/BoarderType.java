package com.innotree.pilot.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum BoarderType {

    Notice("공지사항"),
    FAQ("자주하는질문"),
    QNA("질문과답변");
    private String name;

    BoarderType(String name) {
        this.name = name;
    }
}
