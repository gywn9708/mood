$(document).ready(function(){
	    // Scroll Animation (sa, 스크롤 애니메이션)
    const saTriggerMargin = 300;
    let saTriggerHeight = 0;
    const saElementList = document.querySelectorAll('.sa');

    const saFunc = function() {
        for (const element of saElementList) {
            if (!element.classList.contains('show')) {
                if (element.dataset.saTrigger) {
                    saTriggerHeight = document.querySelector(element.dataset.saTrigger).getBoundingClientRect().top + saTriggerMargin;
                } else {
                    saTriggerHeight = element.getBoundingClientRect().top + saTriggerMargin;
                }

                if (window.innerHeight > saTriggerHeight) {
                    element.classList.add('show');
                }
            }
        }
    }

    window.addEventListener('load', saFunc);
    window.addEventListener('scroll', saFunc);
    //일정 시간마다 배경사진 자동 변경
    var interval = setInterval(function() {
        var backgroundImg = "/images/movie/마녀_배경0";
        var number = Math.floor(Math.random() * 3) + 1;
        backgroundImg += number + ".jpg";
        $("#home").css("background-image", "url(" + backgroundImg + ")");
    }, 3000);
     
    //하트 아이콘 '나의 작품 리스트에 추가/삭제' 기능
    $("#heart").hover( //클릭할 수 있는 요소라고 알려주기
        function () { // mouseover
            $(this).attr("width", "45px");
        },
        function () { // mouseout
            $(this).attr("width", "40px");
        }
    );

    let emptyHeart = true;
    $("#heart").on("click", function() {
        if(emptyHeart) {
            $(this).attr("src", "/images/icon_red_heart.png");
            emptyHeart = false;
            alert("M O O D L I S T에 추가되었습니다.");
        } else {
            $(this).attr("src", "/images/icon_white_heart.png");
            emptyHeart = true;
            alert("나만의 작품 리스트에서 삭제하였습니다.");
        }
    });

    $("submit-button").on("click", function() {
        alert("나의 리뷰가 등록되었습니다.");
    });

    //기본정보 섹션의 탭전환 기능
    $('ul.tabs li').click(function(){
        var tab_id = $(this).attr('data-tab');

        $('ul.tabs li').removeClass('current');
        $('.tab-content').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    })

    //별점&리뷰 섹션의 나의 별점 매기기 기능
    $(".rating-star span").click(function () {
        $(this).parent().children('span').removeClass('on');
        $(this).addClass('on').prevAll('span').addClass('on');
        return false;
    });

    $('ul.tabs li').click(function(){
        var tab_id = $(this).attr('data-tab');

        $('ul.tabs li').removeClass('current');
        $('.tab-content').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    })
});