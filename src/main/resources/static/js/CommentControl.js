//댓글 리스트 가져오기 : 서버로부터 온 값 브라우저에 뿌리기
        //showList의 실행 결과값을 보여주기 위해 li태그 이용해 ul을 구성한 다음 넣음
        //comments라는 배열이 들어온 것을 li태그를 이용해 ul을 구성한 다음 넣음
        let toHtml = function(comments){
        let tmp = "<ul>";
        const count = Object.keys(comments).length;

        comments.forEach(function(comment){
            tmp += '<li data-con='+comment.cno //댓글번호
            tmp += ' data-pcno='+comment.pcno //부모댓글 번호
            tmp += ' data-bno='+comment.bno+'>'
            if(comment.cno != comment.pcno)
                tmp += '답글 : '
            tmp += ' commenter=<span class="commenter">' + comment.commenter +'</span>' //span을 넣어야 작성자만 읽어올 때 편리
            tmp += 'comment=<span class="comment">' + comment.comment + '</span>'
            //tmp += ' regdate=' comment.c_regdate
            tmp += '<button class="delBtn">삭제</button>'
            tmp += '<button class="modBtn">수정</button>'
            tmp += '<button class="replyBtn">답글</button>'
            tmp += '</li>'
        });
        return tmp + "</ul>"
        }
// 댓글 리스트 가져오기2
        let getCommentList = function(comments){
            let html = "";
            //댓글 개수
            const count = Object.keys(comments).length;


            if(count>0){
                for(let i=0;i<count;i++){

                    html += "<div class='mb-2'>";
                    html += "<input type='hidden' id='cno_"+i+"' name='cno' value='"+comments[i].cno+"'>"
                    html += "<b id='commenterWriter_"+i+"'>" + comments[i].commenter +"</b>";
                    html += "<span style='float:right;' align='right' id='c_regdate"+i+"'>" + displayTime(comments[i].c_regdate)+"</span>";
                    html += "<div class='mb-1 comment_container' >"
                    html += "<input type='text' name='comment' id='comment_"+i+"' value='"+ comments[i].comment +"' readonly>"
                    html += "<button class='btn btn-danger delBtn'>삭제</button>";
                    html += "<button class='btn btn-warning modBtn'>수정</button>";
                    html += "</div>"
                    html += "<span style='cursor : pointer; color:blue' class='reCommentBtn' id='reCommentBtn_"+i+"'>답글달기</span>";
                    html += "<span style='display:none; cursor:pointer; color:blue' class='reCommentCloseBtn' id='reCommentCloseBtn_"+i+"'>닫기</span>";

                    html += "<hr>";
//                    html += "<div class='mx-4 reCommentDiv' id='reCommentDiv_"+i+"'></div></div>";
                    html += "</div>";

                }
            }else{
                html += "<div class='mb-2'>";
                html += "<h6><strong>등록된 댓글이 없습니다</strong></h6>";
                html += "</div>";

            }
            return html;

        } // end getCommentList()

//대댓글 리스트 가져오기
let getReCommentList = function(comments){
            let html = "";
            const count = Object.keys(comments).length;
            console.log("getReCommentList count : "+count);
            //대댓글의 개수
            //해당 con에 cdep이 1인 애들 개수
            //style='border:2px solid red'
            if(count > 0){
                for(let i=0;i<count;i++){

                    html += "<div class='mb-2' >";
                    html += "<input type='hidden' id='cno_"+i+"' name='cno' value='"+comments[i].cno+"'>"
                    html += "<b id='commenterWriter_"+i+"'>" + comments[i].commenter +"</b>";
                    html += "<span style='float:right;' align='right' id='c_regdate'"+i+"'>" + displayTime(comments[i].c_regdate)+"</span>";
                    html += "<div class='mb-1 comment_container' >"
                    html += "<input type='text' name='comment' id='comment_"+i+"' value='"+ comments[i].comment +"' readonly>"
                    html += "<button class='btn btn-danger delBtn'>삭제</button>";
                    html += "<button class='btn btn-secondary modBtn'>수정</button>";
                    html += "</div>"
                    html += "<hr>";
                    html += "</div>";
                }
            }else{
                html += "<div class='mb-2'>";
                html += "<h6><strong>등록된 댓글이 없습니다</strong></h6>";
                html += "</div>";

            }
            return html;

        } // end getReCommentList()



//시간설정 함수
function displayTime(timeValue) {

        let today = new Date();
        let gap = today.getTime() - timeValue;
        let dateObj = new Date(timeValue);
        let str = "";

        if (gap < (1000 * 60 * 60 * 24)) {

            let hh = dateObj.getHours();
            let mi = dateObj.getMinutes();
            let ss = dateObj.getSeconds();

            return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
                ':', (ss > 9 ? '' : '0') + ss ].join('');

        } else {
            let yy = dateObj.getFullYear();
            let mm = dateObj.getMonth() + 1; // getMonth() is zero-based
            let dd = dateObj.getDate();

            return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
                    (dd > 9 ? '' : '0') + dd ].join('');
        }
    }


//댓글 리스트 가져오기 서버부르기

    let showList = function(bno){
    console.log('bno : '+$('#bno').val()); //이렇게 값이 가져와지는지 체크
        $.ajax({
            type:'GET', //요청 메서드
            url : '/comments', //요쳥 URI
            dataType : "JSON",
            data : { bno : $('#bno').val() },
            success : function(result){
                console.log("리스트 뿌리기");
                console.log(result);
                //$("#commentList").html(toHtml(result)); //서버로부터 응답이 도착하면 호출될 함수
                $("#commentList").html(getCommentList(result));
                const count = Object.keys(result).length;
                console.log('댓글수 : '+count);
                $("#commentCnt").html(count+"개의 댓글");

            },
            error : function(){alert("error") } //에러가 발생했을 때, 호출될 함수
        }); //$.ajax()
    } //showList() end

//페이지열리면 바로 동작
$(document).ready(function(){
        const bno = $("input[name=bno]").val(); //bno를 가져옴 -> url에 매핑하기 위해
        console.log("bno="+bno);

        showList(bno); // 페이지 실행하면 동작 -> 리스트 가져오기 위해


        // 태그 내의 텍스트 가져오기
        let commentTextarea = document.querySelector('#comment');



//댓글 수정
//동적으로 생성된 요소에 이벤트 등록 위해 아래의 on메서드 사용
//기본형 : $([document|'이벤트 대상의 상위요소 선택']).on('이벤트종류','이벤트대상',function(){ js코드; })
$("#commentList").on("click",".modBtn",function(){
    let modBtn = this;
    console.log(modBtn);

    let cno = $(this).parent().siblings('input').val();
    let comment = $(this).siblings('input').val();
    let commenter = modBtn.parentElement.previousSibling.previousSibling.innerText; // 현재위치에서 부모요소의 이전,이전요소의 내용을 가져옴

    console.log('cno:'+cno);
    console.log('comment:'+comment);
    console.log('commenter:'+commenter);


    $(this).siblings('input').removeAttr('readonly'); //readonly제거
        //$(this).setAttribute('class','saveBtn'); jquery사용시
    modBtn.setAttribute('class','saveBtn'); //js사용시
        //$(this).textContent= '저장';
    modBtn.textContent= '저장';
        //modBtn.setAttribute('class','saveBtn'); // class명 변경
    //요소 추가하는 방법
    /*let saveBtn = document.createElement("button"); //btn생성
    saveBtn.textContent = '저장'; // 자바스크립트 프로퍼티인 textContent는 텍스트를 추가
    saveBtn.setAttribute('class', 'saveBtn'); //속성추가
    comment_container[i].appendChild(saveBtn); //i번째
    */
}); // end $("#commentList").on("click",".modBtn",function(){})

//수정 댓글 저장
$("#commentList").on("click",".saveBtn",function(){
    let modBtn = this;
    console.log(modBtn);
    let cno = $(this).parent().siblings('input').val();
    let comment = $(this).siblings('input').val();
    let commenter = modBtn.parentElement.previousSibling.previousSibling.innerText; // 현재위치에서 부모요소의 이전,이전요소의 내용을 가져옴

    if(comment.trim()==''){
        alert("댓글을 입력해주세요");
        commentTextarea.focus();
        return;
    }
    $.ajax({
        type:'PATCH', //요청메서드
        url : '/comments/'+cno, //요청 URI /comments/{cno}
        headers : { "content-type": "application/json"}, // 요청 헤더
        data : JSON.stringify({cno:cno, comment:comment, commenter:commenter}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success : function(result){
            showList(bno);
        },
        error:function(){console.log("error")}
    });//end $.ajax({})



}); // end $("#commentList").on("click",".saveBtn",function(){})


//댓글 등록
//send에 click이벤트 걸기
$("#sendBtn").click(function(){

        let comment = commentTextarea.value; //댓글 내용
        console.log("comment="+comment);
        //pcno 는 con의 값이 들어가야함
            console.log("comment : "+comment);
            if(comment.trim()==''){
                alert("댓글을 입력해주세요");
                commentTextarea.focus();
                return;
            }
            $.ajax({
                type:'POST', //요청 메서드
                url : '/comments/'+bno, //요청 URI /comments/{bno}
                headers : {"content-type" : "application/json"}, //요청 헤더
                dataType : "JSON", // 컨트롤러로 부터 return응답을 받는 데이터의 형태 text, xml, json, html가능 -> 문자열 : text, 객체/Map/List : Json으로 지정
                data : JSON.stringify({bno: bno, comment:comment}), //서버로 전송할 데이터, stringify()로 직렬화, 자바스크립트의 값은 json문자열로 변환
                success : function(result){ // 컨트롤러와 통신성공시 : return 데이터를 result에 담게 되는 콜백함수
                    console.log("success"); //result 는 서버가 전송한 데이터
                    showList(bno); //showList를 요청했지만 결과는 아직 오지 않은 상태에서 아래의 삭제이벤트 실행, 이벤트가 걸리지 않게 됨
                },
                error : function(){
                    alert("등록되지 않았습니다");
                } //에러가 발생했을 때, 호출될 함수
            });//ajax()
        });


//삭제
$("#commentList").on("click", ".delBtn", function(){
    let cno = $(this).parent().siblings('input').val();
    let bno = $('#bno').val();
    let commenter = this.parentElement.previousSibling.previousSibling.innerText; // 현재위치에서 부모요소의 이전,이전요소의 내용을 가져옴
    console.log('cno클릭 : '+cno)
    console.log('commenter클릭 : '+commenter)

    $.ajax({
        type : "DELETE", //요청메서드
        url : '/comments/'+cno, //요청 URI
        headers : {"content-type" : "application/json"}, //요청 헤더
        dataType : "JSON",
        data : JSON.stringify({commenter:commenter, bno:bno}),
        success : function(result){
            showList(bno);
        },
        error:function(){alert("error");}
    });// end $.ajax()
});// end $("#commentList").on("click", ".delBtn", function(){ })



//대댓글 목록 - 보류
//답글달기 버튼 클릭 -> 클릭이벤트와 셀렉터를 이용해 해당 모댓글의 cno를 통해 목록나타냄
//$(elements).on(events, selector, handler); events : js이벤트 or 사용자 정의 함수, selector : 이벤트를 발생할 객체 선택자, handler : 이벤트 발생시 실행될 함수
<!--$(document).on("click", ".reCommentBtn",function(){-->
$("#commentList").on("click", ".reCommentBtn", function(){

    let cno = $(this).siblings('input').val(); //자신을 제외한 형제요소를 찾는 함수: siblings(), $(this) : 이벤트가 발생한 요소 추적

    //this.parentElement.setAttribute('value','{cno}');
    $(this).parent().attr('value', cno); // mb-2 div에 댓글의 cno값을 보내줌

    console.log("cno : "+cno);

    let bno = $('#bno').val();
    console.log("bno : "+bno);

    $(this).siblings('.reCommentDiv').show();
    $(this).hide();
    $(this).siblings('.reCommentCloseBtn').show();

    //1. replyForm을 옮기고
    $("#replyForm").appendTo($(this).parent());
    //2. 답글을 입력할 폼을 보여주고,
    $("#replyForm").css("display", "block");

}); //end $(document).on("click","reCommentBtn", function(){})


$("#wrtRepBtn").click(function(){
    let comment = $("input[name=replyComment]").val();
    console.log("comment : "+comment);
    let pcno = $("#replyForm").parent().attr("value"); //답글의 부모의 cno값
    console.log("pcno : "+pcno);
        if(comment.trim()==''){
            alert("댓글을 입력해주세요");
            $("input[name=comment]").focus()
            return
        }
    $.ajax({
        type : 'POST',
        url : '/comments/'+pcno,
        headers : { "content-type": "application/json"}, // 요청 헤더
        data : JSON.stringify({pcno:pcno, bno:bno, comment:comment}),
        success : function(result){
            alert("result ="+result);       // result는 서버가 전송한 데이터
            showList(bno); //showList를 요청했지만 결과는 아직 오지 않은 상태에서 아래의 삭제이벤트 실행, 이벤트가 걸리지 않게 됨
        },
        error   : function(){ alert("error") }
    }); //$.ajax()
    $("#replyForm").css("display", "none");
    $("input[name=replyComment]").val('')
    $("#replyForm").appendTo("body");
});// end $("#wrtRepBtn").click(function(){})





    });

/*
    $.ajax({
        type : "GET",
        url : '/comments/'+cno, //요쳥 URI
        headers : {"content-type" : "application/json"},
        dataType : "JSON",
        data : {"bno" : bno},
        success : function(result){
            console.log("자식 리스트 뿌리기");
            console.log(result);
            const count = Object.keys(result).length;
            console.log(count+"개");
            $("#commentCnt").html(count+"개의 댓글");

        $("").html(getReCommentList(result));


        }, //end success
        error:function(request, status, error){
            alert("code: "+request.status +"\n" +"error: "+error);
        }
    }); //end $.ajax()

*/
