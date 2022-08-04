$(function() {
				//id 중복체크가 됐는지 확인하는 변수
				let checkId = false;
				let pwValidation = false;
				let pwCheck = false;
				
				$("#pwValidation").hide();
				$("#pwCheckResult").hide();
				
				//아이디 중복 체크
				$("#btnIdCheck").on("click", function() {
					if($("#userId").val() == null || $("#userId").val() == '') {
						alert("아이디를 입력하세요.");
						return;
					}
					console.log($("#joinForm").serialize());
					$.ajax({
						url: '/user/idCheck',
						type: 'post',
						data: $("#joinForm").serialize(),
						success: function(obj) {
							
							if(obj === "idOk") {
								if(confirm("사용가능한 아이디입니다. " + $("#userId").val() + "를(을) 사용하시겠습니까?")) {
									checkId = true;
									$("#btnIdCheck").attr("disabled", true);
								}
							} else {
								checkId = false;
								alert("이미 존재하는 아이디입니다.");
								$("#userId").focus();
								return;
							}
						},
						error: function(e) {
							console.log(e);
						}
					});
				});
				
				$("#userId").on("change", function() {
					checkId = false;
					$("#btnIdCheck").attr("disabled", false);
				});
				
				//비밀번호 유효성 검사
				function validatePassword(character) {
					return /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{9,}$/.test(character);
				}
				
				$("#userPw").on("change", function() {
					if(!validatePassword($("#userPw").val())) {
						pwValidation = false;
						$("#pwValidation").show();
						$("#userPw").focus();
					} else {
						pwValidation = true;
						$("#pwValidation").hide();
					}
					
					if($("#userPw").val() == $("#userPwCheck").val()) {
						pwCheck = true;
						$("#pwCheckResult").css("color", "green");
						$("#pwCheckResult").text("비밀번호가 일치합니다.");
					} else {
						pwCheck = false;
						$("#pwCheckResult").css("color", "red");
						$("#pwCheckResult").text("비밀번호가 일치하지 않습니다.");
					}
				});
				
				//비밀번호 확인
				$("#userPwCheck").on("change", function() {
					$("#pwCheckResult").show();
					
					if($("#userPw").val() == $("#userPwCheck").val()) {
						pwCheck = true;
						$("#pwCheckResult").css("color", "green");
						$("#pwCheckResult").text("비밀번호가 일치합니다.");
					} else {
						pwCheck = false;
						$("#pwCheckResult").css("color", "red");
						$("#pwCheckResult").text("비밀번호가 일치하지 않습니다.");
					}
				});
				
				//회원가입할(회원가입 폼 서브밋될) 때 마지막 유효성 검사
				$("#joinForm").on("submit", function(e) {
					if(!checkId) {
						alert("아이디 중복체크를 진행해주세요.");
						$("#userId").focus();
						e.preventDefault();
						return;
					}
					
					if(!pwValidation) {
						alert("비밀번호는 영문자, 숫자, 특수문자 조합의 9자리 이상으로 설정해주세요.");
						$("#userPw").focus();
						e.preventDefault();
						return;
					}
					
					if(!pwCheck) {
						alert("비밀번호가 일치하지 않습니다.");
						$("#userPwCheck").focus();
						e.preventDefault();
						return;
					}
				});
			});