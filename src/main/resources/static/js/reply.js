let index = {
    init: function(){
        $("#btn-reply-delete").on("click",()=>{
        this.replyDelete();
        });
      },

        replyDelete: function(){
            let data = {
                boardId : $("#boardId").val(),
            }
            var id = $("#replyId").val();
                $.ajax({
                            type: "GET",
                            url: `/delete-reply/` + id,
                            contentType: "application/json; charset=utf-8",
                            dataType: "json"
                        }).done(function(resp){
                            alert("삭제가 완료되었습니다.");
                            location.reload();
                        }).fail(function(error){
                            alert(JSON.stringify(error));
                        });
          },
  }
index.init();