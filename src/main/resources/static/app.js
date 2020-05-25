var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#replies").html("");
}

function connect() {
    var socket = new SockJS('/ws-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/reply', function (command) {
            showResponse(JSON.parse(command.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendCommand() {
    stompClient.send("/app/listen", {}, JSON.stringify({'name': $("#name").val()}));
}

function showResponse(message) {
    $("#replies").append("<tr><td>" + message + "</td></tr>");
}

function disableCommandButton() {
    $("#name").attr("disabled", "disabled");
    $("#send").attr("disabled", "disabled");
}

function enableCommandButton() {
    $("#name").removeAttr("disabled");
    $("#send").removeAttr("disabled");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    disableCommandButton();
    $( "#connect" ).click(function() { connect(); enableCommandButton(); });
    $( "#disconnect" ).click(function() { disconnect(); disableCommandButton();});
    $( "#send" ).click(function() { sendCommand(); });
});

