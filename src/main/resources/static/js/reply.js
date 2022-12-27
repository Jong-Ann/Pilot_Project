function replyDelete(boardId,replyId) {
        alert("boardId: " + boardId + 'replyId: '+ replyId)
          $.ajax({
            type: "GET",
            url: `/delete-reply/${boardId}/${replyId}`,
            dataType: "json"
        }).done(function(resp){
            alert("삭제가 완료되었습니다.");
            location.reload();
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }


//let index = {
//    init: function(){
//        $("#btn-reply-delete").on("click",()=>{
//        this.replyDelete();
//        });
//      },
//
//    replyDelete: function(){
//        let data = {
//            boardId : $("#boardId").val(),
//            replyId : $("#replyId").val()
//        }
//        alert(boardId)
//            var replyId= $("#replyId").val();
//                $.ajax({
//                            type: "DELETE",
//                            url: `/delete-reply/${boardId}/${replyId}`,
//                            contentType:"application/json; charset=utf-8",
//                            dataType: "json"
//                        }).done(function(resp){
//                            alert("삭제가 완료되었습니다.");
//                            location.reload();
//                        }).fail(function(error){
//                            alert(JSON.stringify(error));
//                        });
//          },
//  }
//index.init();