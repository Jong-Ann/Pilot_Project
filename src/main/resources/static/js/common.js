$(document).ready(function(){
    $("#backButton").on("click",function(){
        history.back();
    }),
   $("#aioConceptName").on("change",function(){

       let langSelect = document.getElementById("aioConceptName");
       let selectText = langSelect.options[langSelect.selectedIndex].value;
       if (selectText === '공지사항'){
           $.ajax({
             type: "GET",
             url: "/board/NOTICE",
             data: [],
             dataType: 'json',
             success: function(data){
                $("#m3").empty()
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
                    +'<td>'+  '<a class="btn btn-danger"'+'id="btn-delete-modal"' + 'href="/board/another-delete-board/' + dataId + '">'+ '삭제' +'</a>'+'</td>'
                    +'<td>'+  '<a class="btn btn-primary"' + 'href=/board/edit-board/'+ dataId + '>'+ '편집' +'</a>'+'</td>'
                    str += '</tr>'
    //$("#m3").append( '<td>' + data[index].title + '</td>');
                });
                $("#m3").append(str)
             },
            });
        } else if( selectText === '자주묻는질문'){

                   $.ajax({
                     type: "GET",
                     url: "/board/FAQ",
                     data: [],
                     dataType: 'json',
                     success: function(data){
                     $("#m3").empty()
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
                            +'<td>'+  '<a class="btn btn-danger"'+'id="btn-delete-modal"' + 'href="/board/delete-board/' + dataId + '">'+ '삭제' +'</a>'+'</td>'
                            +'<td>'+  '<a class="btn btn-primary"' + 'href=/board/edit-board/'+ dataId + '>'+ '편집' +'</a>'+'</td>'
                            str += '</tr>'
            //$("#m3").append( '<td>' + data[index].title + '</td>');
                        });
                        $("#m3").append(str)
                     },
                    });
            } else if( selectText === '질문과답변') {
                 $.ajax({
                 type: "GET",
                 url: "/board/QNA",
                 data: [],
                 dataType: 'json',
                 success: function(data){
                    $("#m3").empty()
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
                        +'<td>'+  '<a class="btn btn-danger"'+'id="btn-delete-modal"' + 'href="/board/delete-board/' + dataId + '">'+ '삭제' +'</a>'+'</td>'
                        +'<td>'+  '<a class="btn btn-primary"' + 'href=/board/edit-board/'+ dataId + '>'+ '편집' +'</a>'+'</td>'
                        str += '</tr>'
        //$("#m3").append( '<td>' + data[index].title + '</td>');
                    });
                    $("#m3").append(str)
                 },
                });
            }
   });
});

     $(".btn-delete-modal").on("click", function (e) {
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

// fncInputReset : function(argObj){
//        // select 초기화
//        $('#'+argObj).find('select').each(function(){
//            this.value = '';
//            tmJs.fncComboSelectAndTextChange(this.id, this.value);  // 콤보박스 값 셋팅
//        });
//
//        // input 초기화
//        $('#'+argObj).find('input').each(function(){
//            this.value = '';
//        });
//
//        // textarea 초기화
//        $('#'+argObj).find('textarea').each(function(){
//            this.value = '';
//        });
//
//
//    }
//fncInputReset();
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
