$(document).ready(function(){
    $("#backButton").on("click",function(){
        history.back();
    }),
     $(".btn-delete").on("click", function (e) {
        e.preventDefault();
        attach = $(this);
        boardId = $(this).attr("boardId");
        $("#Title").text("주의!!");
        $("#Text").text("해당 하는 게시글 " + boardId +" (을)를 삭제하시겠습니까?");
        $("#Modal").modal("show");
        $("#okButton").attr("href",attach.attr("href"));
  });
});

function deleteAlert(){
    alert("해당 내용을 삭제합니다.");
}


