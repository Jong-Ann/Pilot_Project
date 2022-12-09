package com.innotree.pilot.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
public enum BoarderType {

    Notice("공지사항"),
    FAQ("자주하는질문"),
    QNA("질문과답변");
    private String value;

    BoarderType(String value) {
        this.value = value;
    }
}
