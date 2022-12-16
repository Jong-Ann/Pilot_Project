package com.innotree.pilot.reply;

import com.innotree.pilot.board.BoardService;
import com.innotree.pilot.security.PilotUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.Date;

@Controller
public class ReplyController {
    @Autowired
    private BoardService boardService;
    @PostMapping("/create-reply")
    public String replyCreate(Reply reply, @RequestParam(name = "boardId") Integer boardId, @AuthenticationPrincipal PilotUserDetails pilotUserDetails) {
//        Date now = Calendar.getInstance().getTime();
//        reply.setCreateDate(now);
        boardService.saveReply(reply,boardId,pilotUserDetails);
        return "redirect:/board/" + boardId;
    }

//    @PostMapping("/delete-reply/{replyId}")
//    public String replyDelete(Reply reply,@Param("boardId") Integer boardId,@PathVariable(name = "replyId") Integer replyId){
//        boardService.deleteReply(replyId);
//        return "redirect:/board/" + boardId;
//    }



}
