package com.innotree.pilot.reply;

import com.innotree.pilot.security.PilotUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface ReplyService {

    List<Reply> replyList();

    Reply saveReply(Reply reply,int boardId,@AuthenticationPrincipal PilotUserDetails pilotUserDetails);


}
