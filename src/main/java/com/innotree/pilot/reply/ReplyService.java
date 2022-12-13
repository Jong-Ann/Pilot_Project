package com.innotree.pilot.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReplyService {

    List<Reply> replyList();


}
