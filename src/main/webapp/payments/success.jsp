<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64"%>
<%@ page import="java.util.Base64.Encoder"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.Reader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.net.http.HttpClient" %>
<%@ page import="java.net.http.HttpRequest" %>
<%@ page import="java.net.http.HttpResponse" %>

<%
 // 결제 승인 API 호출하기 
 
  String orderId = request.getParameter("orderId");
  String paymentKey = request.getParameter("paymentKey");
  String amount = request.getParameter("amount");
  String secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R:";
  
  Encoder encoder = Base64.getEncoder(); 
  byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
  String authorizations = "Basic "+ new String(encodedBytes, 0, encodedBytes.length);

  paymentKey = URLEncoder.encode(paymentKey, StandardCharsets.UTF_8);
  
  URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
  
  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  connection.setRequestProperty("Authorization", authorizations);
  connection.setRequestProperty("Content-Type", "application/json");
  connection.setRequestMethod("POST");
  connection.setDoOutput(true);
  JSONObject obj = new JSONObject();
  obj.put("paymentKey", paymentKey);
  obj.put("orderId", orderId);
  obj.put("amount", amount);
  
  OutputStream outputStream = connection.getOutputStream();
  outputStream.write(obj.toString().getBytes("UTF-8"));
  
  int code = connection.getResponseCode();
  boolean isSuccess = code == 200 ? true : false;
  
  InputStream responseStream = isSuccess? connection.getInputStream(): connection.getErrorStream();
  
  Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
  JSONParser parser = new JSONParser();
  JSONObject jsonObject = (JSONObject) parser.parse(reader);
  responseStream.close();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <style>
        /* 기본 스타일 */
        body {
            font-family: Arial, sans-serif;
            background-color: #D9D9D9;
            margin: 40px;
        }

        section {
            background-color: #ffffffc2;
            border-radius: 12px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px 40px;
            min-height: 350px;
        }

        h1 {
            text-align: center;
            color: #353535;
            font-size: 1.5rem;
            margin-bottom: 20px;
        }
        
    	
        table.centered-table {
            width: 100%; /* 테이블 폭 설정 */
            border-collapse: collapse; /* 테이블 간격 제거 */
            border-radius: 12px;
            overflow: hidden;
        }

        table.centered-table th, table.centered-table td {
            padding: 12px 20px; /* 셀 패딩 설정 */
            text-align: left; /* 텍스트 왼쪽 정렬 */
        }

        table.centered-table th {
            background-color: #3C6E71;
            width: 25%;
            color: #FFFFFF; /* 헤더 배경 및 텍스트 색상 */
        }

        table.centered-table tr:nth-child(even) {
            background-color: #FFFFFF;
            color: #353535;
        }

        table.centered-table tr:nth-child(odd) {
            background-color: #D9D9D9; 
            color: #353535;
        }

        .error-message {
            background-color: #FFEBEB;
            border: 1px solid #FFC7C7;
            color: #FF5B5B;
            padding: 10px;
            margin-top: 20px;
            border-radius: 12px;
        }
        
        
    </style>
    <title>결제 완료</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
</head>
<body>
<section>
    <%
    if (isSuccess) { %>
        <h1>결제 완료</h1>
        <table class="centered-table">
    <tbody>
        <tr>
            <th>상품이름</th>
            <td><%= jsonObject.get("orderName") %></td>
        </tr>
        <tr>
            <th>상품가격</th>
            <td><%= jsonObject.get("balanceAmount") %></td>
        </tr>
        <tr>
            <th>결제방식</th>
            <td><%= jsonObject.get("method") %></td>
        </tr>
        <tr>
            <th>상세정보</th>
            <td>
                <% if(jsonObject.get("method").equals("카드")) { out.println(((JSONObject)jsonObject.get("card")).get("number"));} %>
                <% if(jsonObject.get("method").equals("가상계좌")) { out.println(((JSONObject)jsonObject.get("virtualAccount")).get("accountNumber"));} %>
                <% if(jsonObject.get("method").equals("계좌이체")) { out.println(((JSONObject)jsonObject.get("transfer")).get("bank"));} %>
                <% if(jsonObject.get("method").equals("휴대폰")) { out.println(((JSONObject)jsonObject.get("mobilePhone")).get("customerMobilePhone"));} %>
            </td>
        </tr>
    </tbody>
	</table>

    <%} else { %>
        <h1>결제 실패</h1>
        <div class="error-message">
            <p><%= jsonObject.get("message") %></p>
            <span>에러코드: <%= jsonObject.get("code") %></span>
        </div>
        <%
    }
    %>
</section>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        <% if (isSuccess) { %>
        clearCart(); // 장바구니 초기화 함수 호출
             function clearCart() {
                $.ajax({
                    url: '/WorkPlus/cartclear.cafe',
                    method: 'GET',
                    success: function() {
                        console.log('장바구니가 초기화되었습니다.');
                        location.reload();
                    },
                    error: function(error) {
                        console.log('장바구니 초기화에 실패하였습니다.', error);
                    }
                });
            } 
        <% } %>
    });
</script>
</body>
</html>

