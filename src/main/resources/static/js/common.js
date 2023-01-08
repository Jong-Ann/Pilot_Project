$(document).ready(function(){
    $("#backButton").on("click",function(){
        history.back();
    }),
       $("#aioConceptName").on("change",function(){
           console.log("시작")
           $.ajax({
             type: "GET",
             url: "/testGet",
             data: [],
             dataType: 'json',
             success: function(data){
                let str = '<tr>'
                console.log(data)
                $.each(data,function(index) {
                    const dataId = data[index].id;
                    str += '<td></td>'
                    str += '<td>' + data[index].id + '</td>'
                    + '<td>' + data[index].boarderType + '</td>'
                    +'<td>'+'<a href=/board/' + dataId + '>' + data[index].title +'</a>' + '[' + data[index].replySize+']' +'</td>'
                    +'<td>'+ data[index].user.username + '</td>'
                    +'<td>'+ data[index].creationTime + '</td>'
                    +'<td>'+  '<a class="btn btn-danger btn-delete"' +'href=/board/delete-board/' + '}>' +'</a>'+'</td>'

                    str += '</tr>'
//$("#m3").append( '<td>' + data[index].title + '</td>');
                });
                $("#m3").append(str)
             },
       });
});
     $(".btn-delete").on("click", function (e) {
        e.preventDefault();
        attach = $(this);
        boardId = $(this).attr("boardId");
        $("#Title").text("주의!!");
        $("#Text").text("해당 하는 게시글 " + boardId +" (을)를 삭제하시겠습니까?");
        $("#Modal").modal("show");
        $("#okButton").attr("href",attach.attr("href"));
     }),
    $("[pattern]").on("keyup",function(e) {
        var regExp = new RegExp($(this).attr("pattern"));
        if(!regExp.test(e.key)){
        e.preventDefault();
        }
    });
function deleteAlert(){
    alert("해당 내용을 삭제합니다.");
}
});
//    var status = $('#test option:selected').attr('value');
//    alert(status.val())
//     $("#test").val(test).prop("selected",true);
//     $("#test").val(test).prop("selected",true);
//    $("#testBox").change(function(){
//        testvalue = $("#testBox").val($(this).val()).prop("selected", true);
//        console.log(testvalue.val())
//        var langSelect = document.getElementById("testBox");
//        var selectText = langSelect.options[langSelect.selectedIndex].text;
//        alert(selectText);
//        selectText.prop("selected",true);
//         testvalue1 = $("#testBox").val();
//         alert(testvalue1);
//         testvalue1.prop("selected", true);
//         $("#test").val('http://localhost:8080/board' + '/page-board/1' + '/NOTICE' + '?value=id&direction=descending').trigger('change');

//            $("#test").change(function(){
////                testvalue = $("#test").val($(this).val()).prop("selected", true);
////                $("#test").attr("value",attach.attr("value"));
//                var status = $('#test option:selected').attr('value');
//                alert(status)
//                $("option").attr("href",status);
////            });
//
