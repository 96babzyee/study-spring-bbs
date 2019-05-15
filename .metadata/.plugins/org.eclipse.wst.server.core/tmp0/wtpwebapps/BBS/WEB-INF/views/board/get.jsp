<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">Board Read</h3>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
					<label>글번호</label> <input class="form-control" name='bno'
						value='<c:out value="${board.bno}"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>제목</label> <input class="form-control" name='title'
						value='<c:out value="${board.title}"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="3" name='content'
						readonly="readonly"><c:out value="${board.content}" /></textarea>
				</div>
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name='writer'
						value='<c:out value="${board.writer}"/>' readonly="readonly">
				</div>

				<button data-oper='modify' class="btn btn-default">수정하기</button>
				<button data-oper='list' class="btn btn-info">목록으로</button>

				<form id='operForm' action="/board/modify" method="get">
					<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
					<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
					<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
					<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
					<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
				</form>
				
			</div>
			<!--  end panel-body -->
		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->

<div class='row'>
	<div class="col-lg-12">
    <!-- /.panel -->
		<div class="panel panel-default">
			<div class="panel-heading">
			  <i class="fa fa-comments fa-fw"></i>댓글
			  <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>댓글 달기</button>
			</div>      
	      	<!-- /.panel-heading -->
		<div class="panel-body">        
			<ul class="chat">

			</ul>
        	<!-- ./ end ul -->
		</div>
      	<!-- /.panel .chat-panel -->

	<div class="panel-footer"></div>

		</div>
	</div>
  <!-- ./ end row -->
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">댓글</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>댓글</label> <input class="form-control" name='reply'
						value='댓글 내용 입력'>
				</div>
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name='replyer'
						value='작성자'>
				</div>
				<div class="form-group">
					<label>작성 날짜</label> <input class="form-control"
						name='replyDate' value='2018-01-01 13:13'>
				</div>

			</div>
			<div class="modal-footer">
				<button id='modalModBtn' type="button" class="btn btn-warning">수정</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">삭제</button>
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">등록</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default">닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript" src="/resources/js/reply.js"></script>

<!-- <script>
	console.log("== js test ==");
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	
	//for replyService add test
	replyService.add(
		{reply:"js test", replyer:"tester",bno:bnoValue},
		function(result){
			alert("result : "+result);
		}
	);
	 
	 
	// reply List test
	replyService.getList({bno:bnoValue, page:1}, function(list){
	  for(var i = 0,  len = list.length||0; i < len; i++ ){
	    console.log(list[i]);
	  }
	});
	
	// 42번 댓글 삭제 test
	replyService.remove(42, function(count) {
	
		console.log(count);
		
		if(count === "success") {
			alert("removed");
		}
	}, function(err) {
		alert("error...");
	});
	 
	 
	 // 13번 댓글 수정
	 /* replyService.update({
		 rno : 13,
		 bno : bnoValue,
		 reply : "modified reply.."
	 }, function(result) {
		 alert("수정완료");
	 });
	 
	 replyService.get(13, function(data){
		 console.log(data);
	 })
</script> -->

<script>
	$(document).ready(function() {
		var bnoValue = '<c:out value="${board.bno}"/>';
		var replyUL = $(".chat");
		  
		showList(1);
		
		// 댓글 목록 처리
		function showList(page){
			
			console.log("show list "+ page);
		      
			replyService.getList({bno:bnoValue,page: page|| 1 }, function(replyCnt, list) {
		        
			console.log("replyCnt: "+ replyCnt);
			console.log("list: "+ list);
			console.log(list);
			
			if(page == -1){
				pageNum = Math.ceil(replyCnt/10.0);
				showList(pageNum);
				return;
			}
			
			var str="";
		        
			if(list == null || list.length == 0){
		        
				replyUL.html("");
		        
				return;
			}
			
			for (var i = 0, len = list.length || 0; i < len; i++) {
				str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
				str +="  <div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>"; 
				str +="    <small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
				str +="    <p>"+list[i].reply+"</p></div></li>";
			}

			replyUL.html(str);
			
			showReplyPage(replyCnt);

			});//end function
		      
		}
		
		// 댓글 목록 상단의 댓글달기 버튼을 클릭하면 모달창이 뜨도록
		var modal = $(".modal");
	    var modalInputReply = modal.find("input[name='reply']");
	    var modalInputReplyer = modal.find("input[name='replyer']");
	    var modalInputReplyDate = modal.find("input[name='replyDate']");
	    
	    var modalModBtn = $("#modalModBtn");
	    var modalRemoveBtn = $("#modalRemoveBtn");
	    var modalRegisterBtn = $("#modalRegisterBtn");
	    
	    $("#modalCloseBtn").on("click", function(e){
	    	
	    	modal.modal('hide');
	    });
	    
	    $("#addReplyBtn").on("click", function(e){
	      
	      modal.find("input").val("");
	      modalInputReplyDate.closest("div").hide();
	      modal.find("button[id !='modalCloseBtn']").hide();
	      
	      modalRegisterBtn.show();
	      
	      $(".modal").modal("show");
	      
	    });
	    
		// 새로운 댓글 추가 이벤트
		modalRegisterBtn.on("click",function(e){
		       
			var reply = {
			      reply: modalInputReply.val(),
			      replyer:modalInputReplyer.val(),
			      bno:bnoValue
			    };
			replyService.add(reply, function(result){
			  
			  alert(result);
			  
			  modal.find("input").val("");
			  modal.modal("hide");
		
			  showList(-1);
			  
			});
	        
		});
		
		// 댓글 클릭 이벤트
		$(".chat").on("click", "li", function(e){
		      
			var rno = $(this).data("rno");
			
			replyService.get(rno, function(reply){
			      
		        modalInputReply.val(reply.reply);
		        modalInputReplyer.val(reply.replyer);
		        modalInputReplyDate.val(replyService.displayTime( reply.replyDate))
		        .attr("readonly","readonly");
		        modal.data("rno", reply.rno);
		        
		        modal.find("button[id !='modalCloseBtn']").hide();
		        modalModBtn.show();
		        modalRemoveBtn.show();
		        
		        $(".modal").modal("show");
		            
			});
			
		});
		
		// 댓글 수정
		modalModBtn.on("click", function(e){
		      
			var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
		      
			replyService.update(reply, function(result){
		            
				alert(result);
				modal.modal("hide");
				showList(pageNum);
		        
			});
		      
		});
		
		// 댓글 삭제
		modalRemoveBtn.on("click", function (e){
		   	  
			var rno = modal.data("rno");
		  	  
			replyService.remove(rno, function(result){
		  	        
				alert(result);
				modal.modal("hide");
				showList(pageNum);
		  	      
			});
		  	  
		});
		
	// 댓글 페이지 번호를 출력하는 로직
	var pageNum = 1;
	var replyPageFooter = $(".panel-footer");
	   
	function showReplyPage(replyCnt){
	     
		var endNum = Math.ceil(pageNum / 10.0) * 10;  
		var startNum = endNum - 9; 
	     
		var prev = startNum != 1;
		var next = false;
	     
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt/10.0);
		}
	     
		if(endNum * 10 < replyCnt){
			next = true;
		}
	     
		var str = "<ul class='pagination pull-right'>";
	     
		if(prev){
			str+= "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
		}
	     
		for(var i = startNum ; i <= endNum; i++){
	       
			var active = pageNum == i? "active":"";
	       
			str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
	     
		if(next){
			str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
		}
	     
		str += "</ul></div>";
	     
		console.log(str);
	     
		replyPageFooter.html(str);
	}
    
    // 페이지 번호를 클릭했을 때 새로운 댓글을 가져오도록 하는 부분
	replyPageFooter.on("click","li a", function(e){
		e.preventDefault();
		console.log("page click");
      
		var targetPageNum = $(this).attr("href");
      
		console.log("targetPageNum: " + targetPageNum);
      
		pageNum = targetPageNum;
      
		showList(pageNum);
	}); 
	
});
</script>

<script type="text/javascript">
	$(document).ready(function() {

		var operForm = $("#operForm");

		$("button[data-oper='modify']").on("click", function(e) {
			operForm.attr("action", "/board/modify").submit();
		});

		$("button[data-oper='list']").on("click", function(e) {
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list")
			operForm.submit();
		});

	});
</script>

<%@include file="../includes/footer.jsp"%>
