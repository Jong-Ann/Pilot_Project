let index = {
    init : function(){
        $("#reply-create-button").on("click",()=>{
            this.replySave();
        });
    },

    replySave: function(){
        let data = {
                comments: $("#reply-content").val(),
                boardId: $("#boardId").val()
        };
        console.log(data.boardId,data.comments);
        $.ajax({
            type:"POST",
            url: `/create-reply/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp){
            alert("해당하는 댓글 생성 완료");
//            location.href=`/board/ + ${data.boardId}`
//            location.reload();
               history.go(0);
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
        }
}


function replyDelete(boardId,replyId) {
          $.ajax({
            type: "DELETE",
            url: `/delete-reply/${boardId}/${replyId}`,
            dataType: "json"
        }).done(function(resp){
            alert("삭제가 완료되었습니다.");
            location.reload();
//            location.href=`/board/ + ${data.boardId}`
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
index.init();


//        $.ajax({
//                type: "POST",
//                url: `/create-reply/${boardId}`,
//                data:JSON.stringify(data),
//                contentType:"application/json; charset=utf-8",
//                dataType: "json"
//            }).done(function(resp){
//                location.href=`/board/${boardId}/`;
//                alert("생성이 완료되었습니다.");
//            }).fail(function(error){
//                alert(JSON.stringify(error));
//            });