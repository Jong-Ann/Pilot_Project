let index = {
    init : function(){
        $("#reply-create-button").on("click",()=>{
            this.replySave();
        }),
        $(".btn-delete-photos").on("click", function (e) {
            e.preventDefault();
            attach = $(this);
//            boardId = $(this).attr("boardId");
            $("#Title").text("주의!!");
            $("#Text").text("해당 하는 게시글 사진"  +" (을)를 삭제하시겠습니까?");
            $("#Modal").modal("show");
            $("#okButton").attr("href",attach.attr("href"));
         }),

        $("[pattern]").on("keyup",function(e) {
            var regExp = new RegExp($(this).attr("pattern"));
            if(!regExp.test(e.key)){
            e.preventDefault();
            }
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
};

index.init()
function replyDelete(boardId,replyId) {
        $(".btn-delete-reply").on("click", function(){
               attach = $(this);
               $("#Title").text("주의!!");
               $("#Text").text("해당 하는 게시글 사진"  +" (을)를 삭제하시겠습니까?");
               $("#Modal").modal("show");
               $("#okButton").attr("href",attach.attr("href"));
//               $.ajax({
//                   type: "GET",
//                   url: `/delete-reply/${boardId}/${replyId}`,
//                   dataType: "json",
//                   success : function(resp){
//                       console.log(resp.message);
//                    }
//                });
    });
};


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