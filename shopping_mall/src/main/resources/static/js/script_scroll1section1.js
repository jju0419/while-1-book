/**
 * @File Name : script_scroll1section1.js
 * @Description : jQuery Code. 스크롤 섹션.
 * @Modification Information
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * 2016.11.03 | 문영신 | 최초 등록
 * 2016.11.30 | 문영신 | 요구반영. 결함개선. 고도화.
 * 2017.03.24 | 문영신 | 파일명 변경. jq_scroll1section1.js 를 script_scroll1section1.js 로
 * 2018.03.26 | 문영신 | 콘텐츠 맞춤 setoff 적용
 * </pre>
 * @author 웹표준화실 문영신
 * @since 2016.10.26
 *
 * @Copyright (C) IACTS.CO.KR All rights reserved.
 */

/* Table of contents )) ☆ 코드 변하면 목차 갱신하세요.
************************************************************
Var --
	변수 초기화
Event --
	마우스휠
메인UX연결
	$선택객체
	동작연결
Function --
	smoothScrollToHash() // 섹션내비.동작
	smoothScrollTop() // 부드러운 세로 스크롤
즉시실행 --

************************************************************
 */

/** ◇◆◇◆◇◆ (function(){})(); 20161024. 20161130. MoonYoungshin
 */
//(function(){ // 20161130. 즉시 실행 익명 함수 사용말자! 여기 함수들을 전역으로 사용하기 위해서!

	var setoff = 0, // 상쇄값 최종 상계
		do1 = {}; // 동작정의


	/** ◇◆  마우스휠. 20160721. MoonYoungshin
	 */
	$(function(){
		$("html, body").on('mousewheel DOMMouseScroll', function(e) {
			var e = e.originalEvent,
				delta = 0;
				//console.log(e);
			if (e.detail) { // [FF]
				delta = e.detail * -28; // [CR][IE]과 동일하게 계상
				//console.log('detail: '+ delta);
			}else{ // [CR][IE]
				delta = e.wheelDelta;
				//console.log('wheelDelta: '+ delta);
			}
		});
	});


	/* ◇◆ Call Functions, Plugins ◇◆◇◆◇◆ */

	$(function(){ // 20161024~. 20180326
		
		var $nav1a = $('.mousewheel1[class*="scroll1"]'), // 현재 페이지 섹션으로 가기
			$nav1top = $('#gotop1'), // 현재 페이지 상단으로 가기
			$head = $('#head'); // 상단 fixed 요소

		// 20180326
		setoff = -$head.height();


		/** ◇◆ 이벤트연결. 20161024. MoonYoungshin
		 */
		$nav1a.each(function(index, element){
			// element == this
			$(this).on('click', function(e){
				e.preventDefault();
				smoothScrollToHash($(this), setoff);
			});
		});

		$nav1top.on('click', function(e){ // 맨 위로 가기
			e.preventDefault();
			smoothScrollTop(0);
		});

	});


	/* ◇◆ Function ◇◆◇◆◇◆ */


	/** ◇◆ 섹션내비.동작. 20160725. MoonYoungshin
	 */
	function smoothScrollToHash($this, setoff){ // (제이쿼리객체, 상쇄값)
		this_hash = $this[0].hash; // = $this.attr('href');
		//console.log(this_hash);
		this_hash_top = $(this_hash).offset().top;
		smoothScrollTop( this_hash_top, setoff ); // setoff 단위 없이 정숫값
	}


	/** ◇◆ 부드러운 세로 스크롤. 20160725. MoonYoungshin
	 */
	function smoothScrollTop(end_top, setoff){ // (이동할곳, 상쇄값)
		(setoff)? setoff: setoff = 0;
		$('html, body').stop().animate({ // $('html, body').animate() ☆[호환]OK!
			//scrollTop: end_top // end_top 이동은 여기서 하고.. 끝나면 상쇄값 이동 시작하려는거
			scrollTop: end_top + setoff // 상쇄 합침
		}, 400, 'swing', function(){
			// 아래처럼 2번 동작하는 거는 어지러워 주석처리하고 상쇄값 합쳐서 1번에 동작시킴.
			//$('html, body').stop().animate({
			//	scrollTop: $(document).scrollTop() + setoff
			//}, 200, 'swing', function(){});
		});
	}


//})();
/** /◇◆◇◆◇◆ (function(){})(); */