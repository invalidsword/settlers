//<![CDATA[
var stompClient = null;

function setConnected(connected) {
    $("#ready").prop("disabled", connected);

}

function connect() {
    showJoinedUser("I just connected!");
    var socket = new SockJS('/game-web-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/gameroom', function (str) {
            showJoinedUser(JSON.parse(str.body).content);
        });
    });
}

function go(){
    stompClient.send("/app/ready");
}

function showJoinedUser(user){
    $("#connections").append("<tr><td>" + user + "</td></tr>");

}

$(function () {

    $("#ready").click(function() { connect();});
    $("#go").click(function() { go();});

});

//]]>