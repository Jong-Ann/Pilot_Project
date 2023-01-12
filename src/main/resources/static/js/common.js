$(document).ready(function(){
    $("#backButton").on("click",function(){
        history.back();
    }),
       $.ajax({
         type: "GET",
         url: "/board/all/1",
         data: [],
         dataType: 'json',
         success: function(data){
            $("#m3").empty()
            let str = '<tr>'
            $.each(data,function(index) {
                const dataId = data[index].id;
                str += '<td></td>'
                str += '<td>' + data[index].id + '</td>'
                + '<td>' +  data[index].boarderType + '</td>'
                +'<td>'+'<a class="text-decoration=none"; href=/board/' + dataId + '>' + data[index].title +'</a>' + '[' + data[index].replySize+']' +'</td>'
                +'<td>'+ data[index].user.username + '</td>'
                +'<td>'+ data[index].creationTime.split('T')[0] + '</td>'
                +'<td>'+  '<a class="btn btn-danger m-2"'+'id="btn-delete-modal1"' + 'href="/board/another-delete-board/' + dataId + '">'+ '삭제' +'</a>'+'<a class="btn btn-primary"' + 'href=/board/edit-board/'+ dataId + '>'+ '편집' +'</a>'+'</td>'
                str += '</tr>'
//$("#m3").append( '<td>' + data[index].title + '</td>');
            });
            $("#m3").append(str);
            const rowsPerPage = 2;
            const rows = document.querySelectorAll("#my-table tbody tr");
            const rowsCount = rows.length;
            console.log("tr의 개수: " +rows);
            console.log("trCount: " + rowsCount)
            const pageCount = Math.ceil(rowsCount / rowsPerPage);
            const numbers = document.querySelector('#pagination');
            console.log("페이지의 개수: " + pageCount);
            console.log("집어넣을 id 값 : " + numbers);
            for(let i = 1; i<=pageCount; i++){
                numbers.innerHTML += ' <li class="page-item"><a class="page-link" href="">' +  `${i}` +'</a></li>';
            };
            const numberBtn = numbers.querySelectorAll('a');
            console.log(numberBtn);

            numberBtn.forEach((item,idx)=>{
                item.addEventListener('click',(e)=>{
                    e.preventDefault();
                    const id = item.getAttribute('id');
                    for(nb of numberBtn) {
                        nb.classList.remove('active');
                    }
                    e.target.classList.add('active');
                    displayRow(idx);
                    console.log(idx);
                })
            })
          function displayRow(idx) {
                let start = idx * rowsPerPage;
                let end = start + rowsPerPage;
                let rowsArray = Array.from(rows);
                console.log(rowsArray);
                for(ra of rowsArray){
                    ra.style.display = 'none';
                }
                let newRows = rowsArray.slice(start,end);
                for( nr of newRows){
                    nr.style.display = '';
                }
            }
         },
});
   $("#aioConceptName").on("change",function(){
       let langSelect = document.getElementById("aioConceptName");
       let selectText = langSelect.options[langSelect.selectedIndex].value;
       if (selectText === '공지사항'){
       alert(selectText)
           $.ajax({
             type: "GET",
             url: "/board/NOTICE/1",
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
                  +'<td>'+ data[index].creationTime.split('T')[0] + '</td>'
                  +'<td>'+  '<a class="btn btn-danger m-2"'+'id="btn-delete-modal"' + 'href="/board/another-delete-board/' + dataId + '">'+ '삭제' +'</a>'+'<a class="btn btn-primary"' + 'href=/board/edit-board/'+ dataId + '>'+ '편집' +'</a>'+'</td>'

                    str += '</tr>'
    //$("#m3").append( '<td>' + data[index].title + '</td>');
                });
                $("#m3").append(str)
             },
            });
        } else if( selectText === '자주묻는질문'){
           alert(selectText)
                   $.ajax({
                     type: "GET",
                     url: "/board/FAQ/1",
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
                            +'<td>'+ data[index].creationTime.split('T')[0] + '</td>'
                            +'<td>'+  '<a class="btn btn-danger m-2"'+'id="btn-delete-modal"' + 'href="/board/another-delete-board/' + dataId + '">'+ '삭제' +'</a>'+'<a class="btn btn-primary"' + 'href=/board/edit-board/'+ dataId + '>'+ '편집' +'</a>'+'</td>'

                            str += '</tr>'
            //$("#m3").append( '<td>' + data[index].title + '</td>');
                        });
                        $("#m3").append(str)
                     },
                    });
            } else if( selectText === '질문과답변') {
               alert(selectText)
                 $.ajax({
                 type: "GET",
                 url: "/board/QNA/1",
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
                          +'<td>'+ data[index].creationTime.split('T')[0] + '</td>'
                          +'<td>'+  '<a class="btn btn-danger m-2"'+'id="btn-delete-modal"' + 'href="/board/another-delete-board/' + dataId + '">'+ '삭제' +'</a>'+'<a class="btn btn-primary"' + 'href=/board/edit-board/'+ dataId + '>'+ '편집' +'</a>'+'</td>'

                        str += '</tr>'
        //$("#m3").append( '<td>' + data[index].title + '</td>');
                    });
                    $("#m3").append(str)
                 },
                });
            } else if( selectText === 'all') {
                  $.ajax({
                     type: "GET",
                     url: "/board/all/1",
                     data: [],
                     dataType: 'json',
                     success: function(data){
                        $("#m3").empty()
                        let str = '<tr>'
                        console.log(data)
                        $.each(data,function(index) {
                            const dataId = data[index].id;
                            str += '<td></td>'
                            str += '<td id="m4">' + data[index].id + '</td>'
                            + '<td>' + data[index].boarderType + '</td>'
                            +'<td>'+'<a href=/board/' + dataId + '>' + data[index].title +'</a>' + '[' + data[index].replySize+']' +'</td>'
                            +'<td>'+  data[index].user.username + '</td>'
                             +'<td>'+ data[index].creationTime.split('T')[0] + '</td>'
                             +'<td>'+  '<a class="btn btn-danger m-2"'+'id="btn-delete-modal"' + 'href="/board/another-delete-board/' + dataId + '">'+ '삭제' +'</a>'+'<a class="btn btn-primary"' + 'href=/board/edit-board/'+ dataId + '>'+ '편집' +'</a>'+'</td>'

                            str += '</tr>'
            //$("#m3").append( '<td>' + data[index].title + '</td>');
                        });
                     },
                    });
            }
   });
     $(".btn-delete").on("click", function (e) {
        e.preventDefault();
        attach = $(this);
        boardId = $(this).attr("boardId");
        $("#Title").text("주의!!");
        $("#Text").text("해당 하는 게시글 " + boardId +" (을)를 삭제하시겠습니까?");
        $("#Modal").modal("show");
        $("#okButton").attr("href",attach.attr("href"));
     });
    $("[pattern]").on("keyup",function(e) {
        var regExp = new RegExp($(this).attr("pattern"));
        if(!regExp.test(e.key)){
        e.preventDefault();
        }
    });
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
