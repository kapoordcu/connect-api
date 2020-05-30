class Store {
    static getSession(key) {
        let sessionId = [];
        if(localStorage.getItem(key)===null) {
            localStorage.setItem(key, JSON.stringify(sessionId));
        } else {
            sessionId = JSON.parse(localStorage.getItem(key));
        }
        return sessionId;
    }

    static setSessionId(key, value) {
        if(localStorage.getItem(key)===null) {
            localStorage.setItem(key, JSON.stringify(value));
        }
    }

    static clearOnDisconnect() {
        localStorage.clear();
    }
}

var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);

}

function connect() {
    var socket = new SockJS('/ws-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        saveIntoLocalStorage();
        stompClient.subscribe('/topic/reply', function (command) {
            showResponse(JSON.parse(command.body).content);
        });
        showResponse("HI, I AM " + Store.getSession("JSESSION"));
    });
}

function saveIntoLocalStorage() {
    let sessionId = createUUID();
    let timestamp = calculateTimestamp();
    Store.setSessionId("JSESSION", sessionId);
    Store.setSessionId("start", timestamp)
}

function calculateTimestamp() {
    return new Date().getTime();
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    let endTime = calculateTimestamp();
    let startTime = Store.getSession("start");
    showResponse("BYE, " + Store.getSession("name")  +  " WE SPOKE FOR " + (endTime-startTime) + " MS");
    Store.clearOnDisconnect();
    console.log("Disconnected");
}

function sendCommand() {
    let message = $("#name").val();
    if(message.toLowerCase().includes("i am")) {
        var keys = message.split(/\s+/);
        for (var i = 0; i < keys.length -1; i++) {
            if(keys[i].toLowerCase().includes("i") && keys[i+1].toLowerCase().includes("am")) {
                Store.setSessionId("name", keys[i+2]);
            }
        }
    }
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

function createUUID(){
    var dt = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (dt + Math.random()*16)%16 | 0;
        dt = Math.floor(dt/16);
        return (c=='x' ? r :(r&0x3|0x8)).toString(16);
    });
    return uuid;
}