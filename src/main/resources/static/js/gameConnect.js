//<![CDATA[
var stompClient = null;
$("#gameroom").hide();
connect();


function setConnected(connected) {
    //$("#connect").prop("disabled", connected);

}

function connect() {
    //showJoinedUser("I just connected!");
    var socket = new SockJS('/game-web-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/gameroom', function (grv) {
           // showJoinedUser(JSON.parse(str.body).content);
            console.log(grv);
            showJoinedUser(JSON.parse(grv.body));

            //showJoinedUser(grv.body)
        });
    });
}


function go(){
    stompClient.send("/app/ready",{},{});
}

function showJoinedUser(grv){
    //$("#playerlist").append("<button type=\"button\" class=\"player-btn\">"+you+" has connected!</button>");
    $("#playerlist").append("<button type=\"button\" class=\"player-btn\">"+grv.name+" has connected!</button>");

    if(grv.numJoined == 3){
        $("#gameroom").show();
    }

}

$(function () {

   // $("#connect").click(function() { connect(); });
    $("#joingame").click(function() { go(); $("#joingame").prop("disabled", true);});

});

//]]>