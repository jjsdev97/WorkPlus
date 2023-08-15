<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<%
 // 결제 승인 API 호출하기 
 
  String orderId = request.getParameter("orderId");
  String paymentKey = request.getParameter("paymentKey");
  String amount = request.getParameter("amount");
  String secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R:";
 // String secretKey = "test_ck_N5OWRapdA8dnGqjvaEYVo1zEqZKL:";
  
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
    }

    table.centered-table {
        margin: 50px auto; /* 중앙에 위치 */
        border-collapse: collapse; /* 테이블 간격 제거 */
        width: 60%; /* 테이블 폭 설정 */
    }

    table.centered-table th, table.centered-table td {
        padding: 15px 20px; /* 셀 패딩 설정 */
        text-align: left; /* 텍스트 왼쪽 정렬 */
    }

    table.centered-table th {
        background-color: #353535;
        color: #FFFFFF; /* 헤더 배경 및 텍스트 색상 */
    }

    table.centered-table tr:nth-child(even) {
        background-color: #284B63; 
        color: #FFFFFF;
    }

    table.centered-table tr:nth-child(odd) {
        background-color: #3C6E71; 
        color: #FFFFFF;
    }

    h1 {
        text-align: center;
        color: #353535;
    }
</style>
	<jsp:include page="payheader.jsp"/>
    <title>결제 성공</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
</head>
<body>
<section>
    <%
    if (isSuccess) { %>
        <h1>결제 성공</h1>
        <table class="centered-table">
            <tbody>
                <tr>
                    <th>상품 이름</th>
                    <td><%= jsonObject.get("orderName") %></td>
                </tr>
                <tr>
                    <th>상품 가격</th>
                    <td><%= jsonObject.get("balanceAmount") %></td>
                </tr>
                <tr>
                    <th>결제 방식</th>
                    <td><%= jsonObject.get("method") %></td>
                </tr>
                <tr>
                    <th>상세 정보</th>
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
        <!-- 결제 실패 부분 (이전과 동일하게 유지) -->
    <%} %>
</section>
</body>
</html>