$(document).ready(function() {
    var webSocket = new WebSocket("ws://localhost:8088/WorkPlus/broadsocket");
  var messageTextArea = $("#messageTextArea");

  webSocket.onopen = function() {
    messageTextArea.val(messageTextArea.val() + "Server connect...\n");
  };

  webSocket.onclose = function() {
    messageTextArea.val(messageTextArea.val() + "Server Disconnect...\n");
  };



  webSocket.onerror = function() {
    messageTextArea.val(messageTextArea.val() + "error...\n");
  };

  webSocket.onmessage = function() {
    messageTextArea.val(messageTextArea.val() + event.data + "\n");
  };

   $("#sendButton").click(function() {
    var user = $("#user");
    var message = $("#textMessage");
    messageTextArea.val(messageTextArea.val() + user.val() + "(me) => " + message.val() + "\n");
    webSocket.send("{{" + user.val() + "}}" + message.val());
    message.val("");
  });

  $("#disconnectButton").click(function() {
    webSocket.close();
  });
});
