package com.innotree.pilot.reply;


import com.innotree.pilot.board.BoardService;
import com.innotree.pilot.security.PilotUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReplyController {
    @Autowired
    private BoardService boardService;
    @PostMapping("/create-reply")
    public String replyCreate(Reply reply, @RequestParam(name = "boardId") Integer boardId, @AuthenticationPrincipal PilotUserDetails pilotUserDetails) {
        boardService.saveReply(reply,boardId,pilotUserDetails);
        return "redirect:/board/" + boardId;
    }

}
