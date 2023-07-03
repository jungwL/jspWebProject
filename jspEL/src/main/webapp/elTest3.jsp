<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>표현언어로 여러가지 데이터 출력(비교연산자)</h1>
	
	<h3>
		\${20 ==20} : ${20==20} <br>
		\${20 eq 20} : ${20 eq 20} <br>
		\${"kbs" == "kbs"} : ${"kbs" == "kbs"} <br>
		\${20 !=20} : ${20 != 20} <br>
		\${20 != 20} : ${20 ne 20} <br>
		\${"kbs" != "mbc"} : ${"kbs" != "mbc"} <br>
		\${30 > 20} : ${30 gt 20} <br>
		\${30 < 20} : ${30 lt 20} <br>
		\${30 >= 20} : ${30 ge 20} <br>
		\${30 <= 20} : ${30 le 20} <br>
	</h3>
</body>
</html>