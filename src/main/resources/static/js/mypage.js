        var slideIndex1 = 1;
        var slideIndex2 = 1;

        $(document).ready(function() {
            showSlides(slideIndex1, "main-carousel1");
            showSlides(slideIndex2, "main-carousel");

            $(".prev").on("click", function() {
                var obj = $(this).parent();
                plusSlides(-1, obj);
            }); 

            $(".next").on("click", function() {
                var obj = $(this).parent();
                plusSlides(1, obj);
            });
            // setInterval(function() {
            // showSlides(++slideIndex);
            // }, 3000);
        });
        
        function plusSlides(n, obj) {
            if(obj.hasClass("main-carousel1"))
                showSlides(slideIndex1 += n, "main-carousel1");
            else 
                showSlides(slideIndex2 += n, "main-carousel");
        }

        function showSlides(n, text) {
            var i;
            if(text == "main-carousel1") {
                var slides = document.getElementsByClassName("mySlides1");

                if (n > slides.length) {
                    slideIndex1 = 1
                }
                
                if (n < 1) {
                    slideIndex1 = slides.length
                }
            } else {
                var slides = document.getElementsByClassName("mySlides");

                if (n > slides.length) {
                    slideIndex2 = 1
                }
                
                if (n < 1) {
                    slideIndex2 = slides.length
                }
            }

            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";  
            }

            if(text == "main-carousel1")
                slides[slideIndex1 - 1].style.display = "block";
            else 
                slides[slideIndex2 - 1].style.display = "block";


        }
        
        
    function test() {
        alert("수정이 완료되었습니다.");
    }

    

    //프로필사진 등록 기능
    function readImage(input) {
    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]) {
        // 이미지 파일인지 검사 (생략)
        // FileReader 인스턴스 생성
        const reader = new FileReader()
        // 이미지가 로드가 된 경우
        reader.onload = e => {
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0])
    }
}
// input file에 change 이벤트 부여
const inputImage = document.getElementById("input-image")
inputImage.addEventListener("change", e => {
    readImage(e.target)
})
//끝 - 프로필사진 등록 기능
