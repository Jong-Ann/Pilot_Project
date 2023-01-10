$(document).ready(function(){
    $("#backButton").on("click",function(){
        history.back();
    }),

     $("#save-button").on("click",function(e){
        e.preventDefault();
         let data = {
            title : $("#title").val(),
            contents : $("#board-contents").val(),
            boarderType : $("#typeBoard").val();
         };
         console.log(data);
           $.ajax({
             type: "POST",
             url: "/board/save-board/test",
             data: JSON.stringify(data),
             contentType: "application/json; charset=utf-8",
             dataType: 'json',
             success: function(data){
                 console.log(data);
                 alert("게시글이 생성되었습니다.")
                } ,
                error: function(error){
                    alert(error);
                }
                });

         });

    });
