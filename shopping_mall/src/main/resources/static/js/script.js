/**
 * @File Name : script.js
 * @Description : Javascript jQuery Code (�޼ҵ�� �Լ� ȣ��, ����..)
 * @Modification Information
 * <pre>
 * ������ | ������ | ��������
 * 2017.05.15 | ������ | ���� ���
 * </pre>
 * @author ��ǥ��ȭ�� ������
 * @since 2017.03.23
 *
 * @Copyright (C) IACTS.CO.KR All rights reserved.
 */

/*! All JavaScript jQuery v.20170323~ 20170517. 20170623 | by MoonYoungshin[myshin@naver.com] | MIT License
 * 20170323. ( jq.js (v.20170314) �� script_base.js (��ý���, �޼ҵ�, �Լ� ����) �� script.js (�޼ҵ�, �Լ� ȣ��, ����..) ���� �и� )
 */

/* Table of contents )) �� �ڵ� �߰��Ǹ�.. ���� �����ϼ���.
************************************************************
jQuery(function($){
	�޼ҵ�, �Լ� ȣ��
});
�ڵ� ������ �ʿ��� �޼ҵ�, �Լ��� �Ʒ��� �д�.
Plugin --
	none
Function --
	addOnNav() ����Ȱ��
************************************************************
 */


//jQuery.noConflict();
/* �ޡ� jQuery(document).ready(function($){}); �ޡߡޡߡޡߡޡߡޡ� */
jQuery(function($){


/* �ޡ� �޼ҵ�, �Լ� ȣ�� �ޡߡޡߡޡ�
 * HTML �ڵ� �ٷ� �Ʒ��� ȣ���ϸ� ���� ���ȴ�.
 */

$(function(){ // DOM ready �̺�Ʈ �Լ� ��ø�ϸ� ���� �����Ѵ� .

}); /* /$(function(){}); */


/* �ޡ� Compatible */

// Scroll
jQscrollTouch('.scroll1wrap', {setDesktop: false});
jQscrollTouch('.scroll1wrap1all1', {setDesktop: true});

// backgroundSize[IE6~8]Support. 20150703. 20161110
// * ��required: jquery.backgroundSize.js
$(window).on('load', function(){ // ��� �̹��� �ε� �� ����
	$('.bsContain').css({backgroundSize: 'contain'});
	$('.bsCover').css({backgroundSize: 'cover'});
});


/* �ޡ� UX 20170224~ 20171026. 20171222. �ޡ�
 * ���� ���� HTML �ڵ� �ؿ� ȣ���Ѵ�.
 * �Լ��� ���⼭ ȣ���ϴ��� ��ȣ�� ���������� �ι� ����ȴ�. )))
 * �޼���(�÷�����)�� $(���ϼ�����).�޼���({�ɼǰ�}); ��ȣ�� �ȵȴ�.
 */

/* �ޡ� AllPages */
	addOnNav(); // ����Ȱ��1��2��3��4��
	makeActive2ClickHover2('#tnb1'); // �ָ޴� ����. ����ũž 1���޴� 'focusin mouseenter' �� �޴� li �� .over ����
	$('#tnb1c>.bg').on('click', function(){ // 20171222
		$('#tnb1c .b2.close').triggerHandler('click');
	});
	//$('#tnb1 .b1.toggle').triggerHandler('click'); // �ٸ����Ȯ�ο�
	//$('#tnb1 div.d1').find('>ul>li').addBack().addClass('over'); // �ٵ���ũžȮ�ο�
	//$('#tnb1 div.d1').find('>ul>li.m1').addBack().addClass('over'); // �ٵ���ũžȮ�ο�
	//take2tnb1d2height();

/* �ޡ� MainPage  */
	//$('#vpr1').jQmPR1({interval:6000, mTabindex:'-1'});
	//$('#banner1').jQmTicker1({interval:3000});
	//jQtabCont2('click', '#bbs1', '.tab', 1, '.cont');
	//jQtabCont2('click', '#tab1cont1', '.tab', 1, '.cont');

/* �ޡ� SubPages */
	//makeActive('.cnb1');
	// ���� ȣ���Ѵ�.



/** �ޡ� ���̾� Ȱ���Ǹ� �ٴ��� ��ũ�� ���´�. 20171226. MoonYoungshin.
 */
//$('#tnb1 .b1.toggle').add('#search1 .b1.toggle').on('click', function(){
$('#tnb1 .b1.toggle').on('click', function(){
	//if( $('#tnb1c').is('.on') || $('#search1c').is('.on') ){
	if( $('#tnb1c').is('.on') ){
		$('html').addClass('ofh');
	}else{
		$('html').removeClass('ofh');
	}
});
$(window).on('resize', function(){ // ��� �̹��� �ε� �� ����
	if( !$('html').is('.Mobile') ){
		$('html').removeClass('ofh');
	}
});


/** �ޡ� #head ������ �Ǵ� ���콺�����ϸ� body �±׿� CSS Ŭ���� �߰�. 20171228. MoonYoungshin.
 */
$('#head').on('focusin mouseenter', function(){
	$('body').addClass('in-head');
}).on('focusout mouseleave', function(){
	if( !$('#search1c').is('.on') && !$('#anb1c').is('.on') ){
		$('body').removeClass('in-head');
	}
});


/** �ޡ� #tnb1 ������ �Ǵ� ���콺�����ϸ� body �±׿� CSS Ŭ���� �߰�. 20180510 ���ȭ.
 */
$('#tnb1 .d1').on('focusin mouseenter', function(){
	$('body').addClass('in-head2');
}).on('focusout mouseleave', function(){
		$('body').removeClass('in-head2');
});


}); /* /jQuery(function($){})(); �ޡߡޡߡޡߡޡߡޡ� */


/* �ޡ� Plugin �ޡߡޡߡޡߡޡߡޡ� */





/* �ޡ� Function �ޡߡޡߡޡߡޡߡޡ� */


/** �ޡ� addOnNav. 20140919~~20171122. 20171229. MoonYoungShin. ����Ȱ��1��2��3��4��
 * Use) addOnNav();
 * menu_all.* ���� ���� ������ ���� �ڵ� <body class="d1 d1_1 d1_1_1 d1_1_1_1"> �� �̿��Ѵ�.
 * ���� �غ�Ǳ� ���� �Լ� ���� �Ѵ�. HTML �ڵ� �߰������� ȣ�� �����ϵ���..
 * �ָ޴�, �θ޴� ������ ���� Ŀ��Ʈ����¡ �ؾ��Ѵ�.
 */ 

function addOnNav(){
	var dn_n_n_n = $('body').attr('class'); 
	var depthArr = dn_n_n_n;  
	if(!depthArr) return false; // ��������. �˾������� �� menu_all ���� ��� 
	$('#anb1 div.d1>ul>li.m'+depthArr[1]).addClass('on'); // ��ü�޴�1��
	$('#anb1 div.d1>ul>li.on>div.d2>ul>li.m'+depthArr[2]).addClass('on'); // ��ü�޴�2��
	$('#anb1 div.d2>ul>li.on>div.d3>ul>li.m'+depthArr[3]).addClass('on'); // ��ü�޴�3��
	// 20171229. �ڡ�ToEdit) ���� or ��Ÿ ���������� �޴� 1�� ù��° Ȱ�� (����� �޴� 1�� ���ι�ġ���� �����޴� ������ ä��)
	if( depthArr[1] == 0 || depthArr[1] > 9 ){
		//$('#anb1 div.d1>ul>li.m1').addClass('on');
		//$('#tnb1 div.d1>ul>li.m1').addClass('on');
	}
	$('#tnb1 div.d1>ul>li.m'+depthArr[1]).addClass('on'); // �ָ޴�1��
	$('#tnb1 div.d1>ul>li.on>div.d2>ul>li.m'+depthArr[2]).addClass('on'); // �ָ޴�2��
	$('#tnb1 div.d2>ul>li.on>div.d3>ul>li.m'+depthArr[3]).addClass('on'); // �ָ޴�3��
	$('#tnb1 div.d3>ul>li.on>div.d4>ul>li.m'+depthArr[4]).addClass('on'); // �ָ޴�4��
	$('#snb1 div.d2>ul>li.m'+depthArr[2]).addClass('on'); // �θ޴�2��
	$('#snb1 div.d2>ul>li.on>div.d3>ul>li.m'+depthArr[3]).addClass('on'); // �θ޴�3��
	$('#snb1 div.d3>ul>li.m'+depthArr[3]).addClass('on'); // �θ޴�3��
	$('#snb1 div.d3>ul>li.on>div.d4>ul>li.m'+depthArr[4]).addClass('on'); // �θ޴�4��
	// �������޴�. HTML�ڵ� ���� addOnNav(); �����ϸ� �������
	$('#lnb1d1 li.m'+depthArr[1]).addClass('on'); // ������ġ��ħ�޴�1��
	//$('#cnb1 .m'+depthArr[2]).addClass('on');
	//$('#cnb1 .m'+depthArr[3]).addClass('on');
	//$('#cnb1 .m'+depthArr[4]).addClass('on');
	//$('#cnb2 .m'+depthArr[5]).addClass('on');
	//$('#cnb2 .m'+depthArr[6]).addClass('on');
	//console.log(depthArr); // Test
}


/** �ޡ� �ָ޴���ħ('div.d2')���̸���. 20170615. 20170623. MoonYoungshin.
 * 3���޴��� ������ġ�� �� 2���޴� ������ ���� ���� �缳�����ش�.
 * Use) takeTnb1D2Height();
 * Task) ���� ���������� ��ȭ
 */
function takeTnb1D2Height(){
	var d3height =	0;
	$('#tnb1 div.d2>ul>li>a').on('mouseenter focusin', function(){
		var $this = $(this);
		var $d2 = $this.closest('div.d2');
		var $d3 = $this.next('.d3');
		setTimeout(function(){
			$d2.css({height: 'auto'});
			d3height = $d3.height();
			//console.log( $d3.css('display') +'  '+ d3height +'  '+ $d2.height() );
			if( $d2.height() < d3height ){
				$d2.css({
					height: d3height + 'px'
				});
			}
		}, 0);
	});
};

/** �ޡ� �ָ޴���ħ('div.d2')���̸���. 20170726. MoonYoungshin.
 * �������� 'load resize' ���� ���� ������ �ΰ� .over ���� style="height:��px;" �־��༭.. Ű��ƽ Ȱ��.
 * Use) take2tnb1d2height();
 * Task) jQuery animate() ���� CSS �ִϸ��̼� �̿��ϱ� ����
 */
function take2tnb1d2height(){
	var d2newHeight = 0;
	var $d2 = $('#tnb1 div.d2');
	$(window).on('load resize', function(){
		$d2.each(function(){
			var $this = $(this);
			$this.css({height: 'auto'});
			//setTimeout(function(){ // ������������! [IE~11]����. �ָ޴�1�� ���콺������ä�� �ε��ϸ� �ٸ� �޴� �� ������. ([CR]�̻�)
				d2newHeight = $this.height();
				$this.css({
					height: d2newHeight + 'px'
				});
				$this.attr({
					'data-height': d2newHeight
				});
				$this.css({height: ''});
			//}, 0);
		});
	});
	$('#tnb1 div.d1>ul>li>a').on('mouseenter focusin', function(){
		var $it = $(this).next('div.d2');
		//console.log( $it.data('height') );
		$d2.css({height: ''});
		//$this.css({height: 'auto'}); // ��������! CSS transition ���� ������ auto ���̸� ��õ ���� ���� ���°� �ȴ�.
		$it.css({
			height: $it.data('height') + 'px'
		});
	}).on('mouseleave focusout', function(){
		var $it = $(this).next('div.d2');
		$d2.css({height: ''});
	});
};