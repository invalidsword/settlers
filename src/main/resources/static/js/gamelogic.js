/*<![CDATA[*/
//Event die faces
var eventFace1 = new Image();
eventFace1.src = "/images/barbarianShip.png";
var eventFace2 = new Image();
eventFace2.src = "/images/barbarianShip.png";
var eventFace3 = new Image();
eventFace3.src = "/images/barbarianShip.png";
var eventFace4 = new Image();
eventFace4.src = "/images/eventGreen.png";
var eventFace5 = new Image();
eventFace5.src = "/images/eventYellow.png";
var eventFace6 = new Image();
eventFace6.src = "/images/eventBlue.png";

//die1 faces
var die1Face1 = new Image();
die1Face1.src = "/images/Ydot1.png";
var die1Face2 = new Image();
die1Face2.src = "/images/Ydot2.png";
var die1Face3 = new Image();
die1Face3.src = "/images/Ydot3.png";
var die1Face4 = new Image();
die1Face4.src = "/images/Ydot4.png";
var die1Face5 = new Image();
die1Face5.src = "/images/Ydot5.png";
var die1Face6 = new Image();
die1Face6.src = "/images/Ydot6.png";

//die2 faces
var die2Face1 = new Image();
die2Face1.src = "/images/Rdot1.png";
var die2Face2 = new Image();
die2Face2.src = "/images/Rdot2.png";
var die2Face3 = new Image();
die2Face3.src = "/images/Rdot3.png";
var die2Face4 = new Image();
die2Face4.src = "/images/Rdot4.png";
var die2Face5 = new Image();
die2Face5.src = "/images/Rdot5.png";
var die2Face6 = new Image();
die2Face6.src = "/images/Rdot6.png";

//Attributes values
var nRoad = 0;
var nShip = 0;
var nKnight1 = 0;
var nKnight2 = 0;
var nKnight3 = 0;
var nTotalKnight = nKnight1 + nKnight2 + nKnight3;
var nCity = 0;
var nWall = 0;
var nSettlement = 0;
var nBrick = 0;
var nWood = 0;
var nOre = 0;
var nSheep = 0;
var nWheat = 0;
var nResourceCard = nBrick + nWood + nOre + nSheep + nWheat;
var nCoin = 0;
var nCloth = 0;
var nBook = 0;
var nCommodityCard = nCoin + nCloth + nBook;
var nVictoryPt = 0;
var nGold = 0;
var barbarianCount = 0;

/*Place road, ship, settlement, city
 var pRoad;
 var pShip;
 var pSettlement;
 var pCity;*/

//Attributes for id
var road;
var ship;
var knight1;
var knight2;
var knight3;
var totalKnight;
var city;
var wall;
var settlement;
var brick;
var wood;
var ore;
var sheep;
var wheat;
var resourceCard;
var coin;
var cloth;
var book;
var commodityCard;
var victoryPt;
var gold;
var barbarian;

//Maritime Trade
var gBrick;
var gWood;
var gOre;
var gSheep;
var gWheat;
var maritimeTrade;
var tBrick;
var tWood;
var tOre;
var tSheep;
var tWheat;

//Count number of clicks
var brickClick = 0;
var woodClick = 0;
var oreClick = 0;
var sheepClick = 0;
var wheatClick = 0;

var seven;
//Knight state (active/inactive)
var knight;

//Turn Counter
//var counter = 1;
//var color = 'black';
//var myUsername = [[${username}]];
var stompClient = null;

// Robber

var enableSteal = false;
var enableMoveRobber = false;

var settlementPlaced = false; /*Indicates whether the first settlement has been placed in the setup phase*/
var road1Placed = false;    /*Indicates whther the first road has been placed in the setup phase*/
var cityPlaced = false;     /*Indicates whether the first city has been placed in the setup phase*/
var road2Placed = false;    /*Indicates whether the second road has been placed in the setup phase*/

var isSetup1 = false;
var isSetup2 = false;
var gotSetupResources = false;

var holder = d3.select("svg");

var enableBuyAndUpgrade = false;
//var boardEnabled = false;
var currUser;

//var currPlayer = document.getElementById("currPlayer");

var colorList = [];
var userList = [];
userList.push(p1name);
userList.push(p2name);
userList.push(p3name);
//userList.push(p4name);
colorList.push(p1color);
colorList.push(p2color);
colorList.push(p3color);
//colorList.push(p4color);


var usersToPrint = [];
var colorsToPrint = [];
var arrayLength = userList.length;
for (var i = 0; i < arrayLength; i++) {
    if(!userList[i].match(myUsername)){
        usersToPrint.push(userList[i]);
        colorsToPrint.push(colorList[i]);
    }
}
var p1 = document.getElementById("player1");
p1.style.background = myColor;

for(var i = 0; i<usersToPrint.length;i++){

    if(i == 0){
        var p2 = document.getElementById("player2");
        p2.innerHTML = usersToPrint[i];
        p2.style.background = colorsToPrint[i];
        //p2.className = "turnWabble";

    }else if(i==1){
        var p3 = document.getElementById("player3");
        p3.innerHTML = usersToPrint[i];
        p3.style.background = colorsToPrint[i];
    }else if(i==2){
        var p4 = document.getElementById("player4");
        p4.innerHTML = usersToPrint[i];
        p4.style.background = colorsToPrint[i];
    }
}



connect();
initializeTurn();

console.log("my username is: "+myUsername);
console.log("my color is: "+myColor);

function initializeTurn(){



    currUser = startingPlayer;
    console.log("Player Starting is: "+startingPlayer);

    setAttributes();

    if(startingPlayer.match(myUsername)){
        //enable all turn buttons
        //boardEnabled = true;
        isSetup1 = true;
        disableMyButtons();
        document.getElementById('rolldice').disabled = true;
        document.getElementById('endTurn').disabled = true;


    }else{
        //disable all turn buttons

        //boardEnabled = false;
        disableMyButtons();
        document.getElementById('rolldice').disabled = true;
        document.getElementById('endTurn').disabled = true;
    }


}

function connect() {
    //showJoinedUser("I just connected!");
    var socket = new SockJS('/game-board-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
       // setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/turninfo', function (pap) {
            pap = JSON.parse((pap.body));

            currUser = pap.username;
           // currPlayer.innerHTML = "Turn: " + currUser;


            if(currUser.match(myUsername)){
                //disable all turn buttons
                console.log("it's my turn!")
                console.log(pap)
                if(pap.setup1){
                    isSetup1 = true;
                    console.log("Setup1 is true!")

                }else if(pap.setup2){
                    isSetup1 = false;
                    isSetup2 = true;
                    console.log("Setup1 is false!")
                    console.log("Setup2 is true!")

                    document.getElementById('endTurn').disabled = true;
                }else{
                    isSetup1 = false;
                    isSetup2 = false;
                    if (!gotSetupResources){
                        setupDone();
                        gotSetupResources = true;
                    }
                    document.getElementById('rolldice').disabled = false;
                    document.getElementById('endTurn').disabled = true;


                }


            }else{
                //disable all turn buttons
                document.getElementById('rolldice').disabled = true;
                document.getElementById('endTurn').disabled = true;
            }


        });



        stompClient.subscribe('/topic/dice', function (dice) {

            dice = JSON.parse((dice.body));

            console.log(dice.red);
            console.log(dice.yellow);
            console.log(dice.event);

            document.images["die1"].src = eval("die1Face" + dice.red + ".src");
            document.images["die2"].src = eval("die2Face" + dice.yellow + ".src");
            document.images["eventDie"].src = eval("eventFace" + dice.event + ".src");

            //Increment barbarian count if event die is (1, 2, 3)
            if(dice.event == 1 || dice.event == 2 || dice.event == 3) {
                barbarianCount += 1;

                //Barbarian attack
                if(barbarianCount == 6) {
                    barbarian = 0;
                }
            }

            var diceTotal = dice.red + dice.yellow;
            status.innerHTML = "You rolled " + diceTotal + ".";
            console.log("You rolled " + diceTotal + ".");
            if(diceTotal == 7){
                disableMyButtons();
                status.innerHTML += " Robber!";
                console.log("Place robber");
                enableMoveRobber = true;
            }
            else if(currUser.match(myUsername)){
                console.log("getResources")
                getResources();
            }

        });

        stompClient.subscribe('/topic/road', function (piece) {
            piece = JSON.parse((piece.body));

            var myId = piece.id;
            var toColor = piece.color;
            var valid = piece.isValid;

            if(valid){
                d3.select("#"+myId).attr("fill", toColor);
                d3.select("#"+myId).attr("hasRoad","true");

                if(currUser.match(myUsername)){
                    if(!road1Placed){
                        road1Placed = true;
                        document.getElementById('endTurn').disabled = false;
                    }else if(!road2Placed){
                        road2Placed = true;
                        document.getElementById('endTurn').disabled = false;
                    }else{
                        getResources();
                    }

                    nRoad++;
                   // pRoad = document.getElementById("pRoad");
                   // pRoad.innerHTML = "Roads " + nRoad;
                }



            }else{
                if(currUser.match(myUsername)){
                    //place alert invalid road here
                    //TODO: ALERT HERE

                }

            }

        });

        stompClient.subscribe('/topic/playerIncrement', function (players) {

            if(!gotSetupResources){
                gotSetupResources = true;
            }
            players = JSON.parse((players.body));
            var me = 'p';

            if (myUsername.match(p1name)){
                me = me+'1';
            }else if (myUsername.match(p2name)){
                me = me+'2';
            }else if (myUsername.match(p3name)){
                me = me+'3';
            }else{
                me = me+'4';
            }

            nBrick = players[me+'Brick'];
            nWood = players[me+'Wood'] ;
            nOre = players[me+'Ore'];
            nSheep = players[me+'Sheep'];
            nWheat = players[me+'Wheat'];
            nCoin = players[me+'Coin'];
            nCloth = players[me+'Cloth'];
            nBook = players[me+'Paper'];
            nGold = players[me+'Gold'];

            setAttributes();


        });


        stompClient.subscribe('/topic/setupDone', function (confirm) {

            getResources();

        });

        stompClient.subscribe('/topic/settlement', function (piece) {
            piece = JSON.parse((piece.body));

            var myId = piece.id;
            var toColor = piece.color;
            var valid = piece.isValid;

            if(valid){
                if(currUser.match(myUsername)){
                    if(!settlementPlaced){
                        settlementPlaced = true;
                    }else{
                        //maybe replace with resources
                        getResources();

                    }
                    nSettlement++;
                   //
                    // pSettlement = document.getElementById("pSettlement");
                   // pSettlement.innerHTML = "Settlements " + nSettlement;
                }

                d3.select("#"+myId).attr("fill", toColor);
               // d3.select("#"+myId).attr("hasSettlement","true");

            }else{
                if(currUser.match(myUsername)){
                    //illegal settlement error
                    //TODO: ALERT HERE
                }

            }

        });

        stompClient.subscribe('/topic/city', function (piece) {
            piece = JSON.parse((piece.body));

            var myId = piece.id;
            var toColor = piece.color;
            var valid  = piece.isValid;

            if(valid){
                if(currUser.match(myUsername)){
                    if(!cityPlaced){
                        cityPlaced = true;
                    }else{
                        getResources();
                    }

                    nCity++;
                   //pCity = document.getElementById("pCity");
                    // pCity.innerHTML = "Cities " + nCity;
                }
                d3.select("#"+myId).attr("fill", toColor).attr("r",12);
             //   d3.select("#"+myId).attr("hasCity", "true");
            }else{
                if(currUser.match(myUsername)){
                    console.log("invalid city location");
                    //TODO: ALERT HERE
                }


            }


        });

        stompClient.subscribe('/topic/victorypoints', function(vPiece){
            vPiece = JSON.parse((vPiece.body));

            var p1points = vPiece.p1points;
            var p2points = vPiece.p2points;
            var p3points = vPiece.p3points;

            document.getElementById('P1Vp').innerHTML = p1points;
            document.getElementById('P2Vp').innerHTML = p2points;
            document.getElementById('P3Vp').innerHTML = p3points;

            document.getElementById('P1').innerHTML = p1name;
            document.getElementById('P2').innerHTML = p2name;
            document.getElementById('P3').innerHTML = p3name;

            var p1PTS = 10*p1points;
            var p2PTS = 10*p2points;
            var p3PTS = 10*p3points;

            document.getElementById('P1Vp').style.width = p1PTS+"%";
            document.getElementById('P2Vp').style.width = p2PTS+"%";
            document.getElementById('P3Vp').style.width = p3PTS+"%";

            if(p1points >= 10){
                if(p2points>p3points)
                    openNav(p1name,p2name,p3name);
                else
                    openNav(p1name,p3name,p2name);
            }
            else if(p2points >= 10)
            {
                if(p1points>p3points)
                    openNav(p2name,p1name,p3name);
                else
                    openNav(p2name,p3name,p1name);
            }
            else if(p3points >= 10)
            {
                if(p1points>p2points)
                    openNav(p3name,p1name,p2name);
                else
                    openNav(p3name,p2name,p1name);
            }
        });

        stompClient.subscribe('/topic/knight', function(piece){
            piece = JSON.parse((piece.body));

            var myId = piece.id;
            var toColor = piece.color;
            var valid = piece.isValid;
            if(valid) {
                drawKnight(myId,toColor,1);
                getResources();
            }

        });

        stompClient.subscribe('/topic/upgradeknight', function(piece){
            piece = JSON.parse((piece.body));

            var myId = piece.id;
            var toColor = piece.color;
            var valid = piece.isValid;
            if(valid) {
                var str = d3.select("k"+myId).attr("strength")+1;
                drawKnight(myId,toColor,str);
                getResources();
            }

        });

        stompClient.subscribe('/topic/activateknight', function(piece){
            piece = JSON.parse((piece.body));

            var myId = piece.id;
            var toColor = piece.color;
            var valid = piece.isValid;
            if(valid) {
                d3.select("#"+myId).attr("fill", "white");
                getResources();
            }

        });

        stompClient.subscribe('/topic/traderequest', function (trade) {
            trade = JSON.parse((trade.body));


            var valid = trade.valid;
            var p1 = trade.aRequester;
            var p2 = trade.aRequestee;

            if(valid){
                if(currUser.match(myUsername)) {
                    getResources();
                }
            }else{
                if(p1.match(myUsername) || p2.match(myUsername)){
                    //TODO: ALERT HERE

                }

            }

        });

        stompClient.subscribe('/topic/maritimetrade', function(piece){
            piece = JSON.parse((piece.body));
            var valid = piece.isValid;
            if(valid) {
                if(currUser.match(myUsername)) {
                    getResources();
                }
            }
        });


        stompClient.subscribe('/topic/placerobber', function(robber) {
            robber = JSON.parse((robber.body));

            if(robber.isValid){
                console.log("isValid = true")
                drawRobber(robber.hexId);
                enableMoveRobber = false;
                if (currUser.match(myUsername)) {
                    if (robber.hasStealable){
                        enableSteal = true;
                    }
                    else if(currUser.match(myUsername)) {
                        getResources();
                        enableMyButtons();
                        document.getElementById('rolldice').disabled = true;
                    }
                }
            }
            else{
                enableMoveRobber = true;
            }
        });

        stompClient.subscribe('/topic/stealresource', function(steal){
            console.log("just stole");
            steal = JSON.parse((steal.body));
            if (currUser.match(myUsername)){
                if (steal.isValid){
                    enableMyButtons();
                    document.getElementById('rolldice').disabled = true;
                    enableSteal = false;
                    getResources();
                }else{
                    disableMyButtons();
                    enableSteal = true;
                }
            }
        });

    });
}


function sendEdge(){
    stompClient.send("/app/edge",{},JSON.stringify(jsonEdges));
}

function sendBoard(Board){
    stompClient.send("/app/geo",{},JSON.stringify(Board));
}


function sendHex(){
    stompClient.send("/app/hex",{},JSON.stringify(jsonPolygons));
}

function sendIntersection(){
    stompClient.send("/app/intersection",{},JSON.stringify(jsonIntersections));
}

function readySetNeighbours(){
    stompClient.send("/app/setNeighbours",{},{});
}

function getResources(){
    stompClient.send("/app/getResources",{},{});

}

function setupDone(){
    stompClient.send("/app/setupDone",{},{});
}

function requestTrade(trade){
    stompClient.send("/app/traderequest",{},JSON.stringify(trade))
}

function SendMaritime(get,give) {
    stompClient.send("/app/maritimetrade",{},JSON.stringify({"aRequested":get, "aOffered":give, "isValid":false}))
}

// Win and Lose game
function openNav(fst,snd,thd) {

    if(myUsername == fst)
        document.getElementById("mySidenav").style.width = "100%";
    else
    {
        document.getElementById("mySidenav").style.width = "100%";
        document.getElementById("mySidenav").innerHTML = "You lost!";
    }

        document.getElementById("fst").innerHTML = fst;
        document.getElementById("snd").innerHTML = snd;
        document.getElementById("thd").innerHTML = thd;

}



//Roll Dice
function rollDice() {
    var status = document.getElementById("status");
    var d1, d2, d3;

    d1 = Math.floor(Math.random() * 6) + 1;
    d2 = Math.floor(Math.random() * 6) + 1;
    d3 = Math.floor(Math.random() * 6) + 1;

    stompClient.send("/app/rolldice",{},JSON.stringify({"red":d1, "yellow":d2, "event":d3}));


    //boardEnabled = true;
    enableMyButtons();
    document.getElementById('rolldice').disabled = true;
    document.getElementById('endTurn').disabled = false;

}
//Used to enable rollDice button when end turn button is pressed
function endTurn() {

    stompClient.send("/app/endturn",{}, {});

    disableMyButtons();

}

function setVP() {

    stompClient.send("/app/showvictorypoints",{}, {});

}


//Radio click on/off

var clickBuyRoad = false;
var clickBuySettlement = false;
var clickUpgradeCity = false;
var clickActivateKnight = false;
var clickBuyKnight = false;
var clickUpgradeKnight = false;
var clickMoveShip = false;

function buyRoad(){
    clickBuyRoad = true;
    clickBuySettlement = false;
    clickUpgradeCity = false;
    clickActivateKnight = false;
    clickBuyKnight = false;
    clickUpgradeKnight = false;
    clickMoveShip = false;
}
function buySettlement(){
    clickBuySettlement = true;
    clickBuyRoad = false;
    clickUpgradeCity = false;
    clickActivateKnight = false;
    clickBuyKnight = false;
    clickUpgradeKnight = false;
    clickMoveShip = false;
}
function upgradeCity(){
    clickUpgradeCity = true;
    clickBuyRoad = false;
    clickBuySettlement = false;
    clickActivateKnight = false;
    clickBuyKnight = false;
    clickUpgradeKnight = false;
    clickMoveShip = false;
}
function activateKnight(){
    clickActivateKnight = true;
    clickBuyRoad = false;
    clickBuySettlement = false;
    clickUpgradeCity = false;
    clickBuyKnight = false;
    clickUpgradeKnight = false;
    clickMoveShip = false;
}
function buyKnight(){
    clickBuyKnight = true;
    clickBuyRoad = false;
    clickBuySettlement = false;
    clickUpgradeCity = false;
    clickActivateKnight = false;
    clickUpgradeKnight = false;
    clickMoveShip = false;
}
function upgradeAKnight(){
    clickUpgradeKnight = true;
    clickBuyRoad = false;
    clickBuySettlement = false;
    clickUpgradeCity = false;
    clickActivateKnight = false;
    clickBuyKnight = false;
    clickMoveShip = false;
}
function moveShip (){
    clickMoveShip = true;
    clickBuyRoad = false;
    clickBuySettlement = false;
    clickUpgradeCity = false;
    clickActivateKnight = false;
    clickBuyKnight = false;
    clickUpgradeKnight = false;
}


function disableMyButtons(){

    enableBuyAndUpgrade = false;
    document.getElementById('rolldice').disabled = true;
    document.getElementById('endTurn').disabled = true;
    document.getElementById('bRoad').disabled = true;
    document.getElementById('bSettlement').disabled = true;
    document.getElementById('uCity').disabled = true;
    document.getElementById('aKnight').disabled = true;
    document.getElementById('bKnight').disabled = true;
    document.getElementById('uKnight').disabled = true;
    document.getElementById('mShip').disabled = true;
    document.getElementById('maritimeTrade').disabled = true;

}

function enableMyButtons(){
    enableBuyAndUpgrade = true;

    document.getElementById('rolldice').disabled = false;
    document.getElementById('endTurn').disabled = false;
    document.getElementById('bRoad').disabled = false;
    document.getElementById('bSettlement').disabled = false;
    document.getElementById('uCity').disabled = false;
    document.getElementById('aKnight').disabled = false;
    document.getElementById('bKnight').disabled = false;
    document.getElementById('uKnight').disabled = false;
    document.getElementById('mShip').disabled = false;
    document.getElementById('maritimeTrade').disabled = false;

}

function drawKnight(chosenIntersectionID, colour, knightStrength)
{
    var intersectionObject = document.getElementById(chosenIntersectionID);
    var arc = d3.arc()
        .innerRadius(8)
        .outerRadius(12)
        .startAngle(0) //converting from degs to radians
        .endAngle(2*Math.PI) //just radians
    holder.append("path")
        .attr("d", arc)
        .attr("transform", "translate(" + intersectionObject.__data__.x + "," + intersectionObject.__data__.y +")")
        .attr("fill", colour)
        .attr("class", "knight")
        .attr("strength", knightStrength)
        .attr("x_posit", intersectionObject.__data__.x)
        .attr("y_posit", intersectionObject.__data__.y)
        .attr("id", "k"+chosenIntersectionID);
    holder.append("g")
        .attr("transform", "translate(" + (intersectionObject.__data__.x + 2) + "," + (intersectionObject.__data__.y - 2) + ")")
        .append("text")
        .text( "+" + knightStrength)
        .attr("font-family", "sans-serif")
        .attr("font-size", "12px")
        .attr("font-weight", "bold")
        .attr("fill", "#00ffff")
        .attr("id", "k"+chosenIntersectionID+"strength");
}


//Activated to show attributes when player button is clicked
function setAttributes() {
    //Place road, ship, settlement, city
   // pRoad = document.getElementById("pRoad");
   // pShip = document.getElementById("pShip");
   // pSettlement = document.getElementById("pSettlement");
    //pCity = document.getElementById("pCity");

    //Attribute id getters
    road = document.getElementById("road");
    ship = document.getElementById("ship");
    city = document.getElementById("city");
    wall = document.getElementById("wall");
    settlement = document.getElementById("settlement");
    victoryPt = document.getElementById("victoryPt");
    gold = document.getElementById("gold");
    barbarian = document.getElementById("barbarian");
    road.innerHTML = "Roads: " + nRoad;
    ship.innerHTML = "Ships: " + nShip;
    city.innerHTML = "Cities: " + nCity;
    wall.innerHTML = "Walls: " + nWall;
    settlement.innerHTML = "Settlements: " + nSettlement;
    victoryPt.innerHTML = "Victory Points: " + nVictoryPt;
    gold.innerHTML = "Golds: " + nGold;

    //Knights
    knight1 = document.getElementById("knight1");
    knight2 = document.getElementById("knight2");
    knight3 = document.getElementById("knight3");
    totalKnight = document.getElementById("totalKnight");
    knight1.innerHTML = "Rank 1: " + nKnight1;
    knight2.innerHTML = "Rank 2: " + nKnight2;
    knight3.innerHTML = "Rank 3: " + nKnight3;
    totalKnight.innerHTML = "Knights: " + nTotalKnight;

    //Resource Cards
    brick = document.getElementById("brick");
    wood = document.getElementById("wood");
    ore = document.getElementById("ore");
    sheep = document.getElementById("sheep");
    wheat = document.getElementById("wheat");
    resourceCard = document.getElementById("resourceCard");
    brick.innerHTML = nBrick;
    wood.innerHTML = nWood;
    ore.innerHTML = nOre;
    sheep.innerHTML = nSheep;
    wheat.innerHTML = nWheat;
    resourceCard.innerHTML = "Resource Cards: " + nResourceCard;

    //Commodity Cards
    coin = document.getElementById("coin");
    cloth = document.getElementById("cloth");
    book = document.getElementById("book");
    commodityCard = document.getElementById("commodityCard");
    coin.innerHTML = nCoin;
    cloth.innerHTML = nCloth;
    book.innerHTML = nBook;
    commodityCard.innerHTML = "Commodity Cards: " + nCommodityCard;

    /*Place road, ship, settlement, city
     pRoad.innerHTML = "Place Roads " + nRoad;
     pShip.innerHTML = "Place Ship " + nShip;
     pSettlement.innerHTML = "Place Settlements " + nSettlement;
     pCity.innerHTML = "Place Cities " + nCity;*/
}

//Build road
function buildRoad(id) {
    if (nRoad < 15 && nBrick > 0 && nWood > 0 ) {

        stompClient.send("/app/placeroad",{}, JSON.stringify({"id":id, "color":myColor, "isValid":false}));
    }
    else {

        //Set no resource message to true
    }
}
//Place road
function placeRoad(id) {

    stompClient.send("/app/setuproad",{}, JSON.stringify({"id":id, "color":myColor, "isValid":false}));

}

//Build ship
function buildShip(id) {
    if (nShip < 15 && nSheep > 0 && nWood > 0) {
        nSheep--;
        nWood--;
        nShip++;
        ship = document.getElementById("ship");
        ship.innerHTML = "Ship " + nShip;
    }
    else {
        //Set no resource message to true
    }
}

//Place ship
function placeShip(id) {
    nShip++;
    pShip = document.getElementById("pShip");
    pShip.innerHTML = "Ship" + nShip;
}

//Build settlement
function buildSettlement(id) {
    if (nSettlement < 5 && nWood > 0 && nBrick > 0 && nSheep > 0 && nWheat > 0) {

        stompClient.send("/app/placesettlement",{}, JSON.stringify({"id":id, "color":myColor, "isValid":false}));
    }
    else {
        //Set no resource message to true
    }
}

//Place settlement
function placeSettlement(id) {

    stompClient.send("/app/setupsettlement",{}, JSON.stringify({"id":id, "color":myColor, "isValid":false}));
}

//Build city
function buildCity(id) {
    if (nCity < 4 && nOre > 2 && nWheat > 1) {

        stompClient.send("/app/placecity",{}, JSON.stringify({"id":id, "color":myColor, "isValid":false}));
    }
    else {
        //Set no resource message to true
    }
}

//Place city
function placeCity(id) {

    stompClient.send("/app/setupcity",{}, JSON.stringify({"id":id, "color":myColor, "isValid":false}));


}

//Buy/Build knight
function buildKnight(id) {
    stompClient.send("/app/placeknight",{},JSON.stringify({"id":id, "color":myColor, "isValid":false}))
}
//upgrade knight
function upgradeKnight(id) {
    stompClient.send("/app/upgradeknight",{},JSON.stringify({"id":id, "color":myColor, "isValid":false}))
}

function buyActivateKnight(id){
    stompClient.send("/app/activateknight",{},JSON.stringify({"id":id, "color":myColor, "isValid":false}))
}



//Build wall
function buildWall() {
    if (nBrick > 1 && nWall < 3) {
        nBrick -= 2;
        nWall++;
    //    wall = document.getElementById("wall");
    //    wall.innerHTML = "Walls " + nWall;
    }
    else {
        //Set no resource message to true
    }
}

//get Knight1
function getKnight1() {
    if (nKnight1 < 2 && nOre > 0 && nSheep > 0) {
        nOre--;
        nSheep--;
        nKnight1++;
        knight = false;
    //    knight1 = document.getElementById("knight1");
    //    knight1.innerHTML = "Rank 1: " + nKnight1;
    }
    else {
        //Set no resource message to true
    }
}
//get Knight2
function getKnight2() {
    if (nKnight2 < 2 && nOre > 0 && nSheep > 0) {
        nOre--;
        nSheep--;
        nKnight1--;
        nKnight2++;
        knight1 = document.getElementById("knight1");
        knight1.innerHTML = "Rank 1: " + nKnight1;
        knight2 = document.getElementById("knight2");
        knight2.innerHTML = "Rank 2: " + nKnight2;
    }
    else {
        //Set no resource message to true
    }
}
//get Knight3
function getKnight3() {
    if (nKnight3 < 2 && nOre > 0 && nSheep > 0) {
        nOre--;
        nSheep--;
        nKnight2--;
        nKnight3++;
        knight2 = document.getElementById("knight2");
        knight2.innerHTML = "Rank 2: " + nKnight2;
        knight3 = document.getElementById("knight3");
        knight3.innerHTML = "Rank 3: " + nKnight3;
    }
    else {
        //Set no resource message to true
    }
}

 function moveKnight() {


 }

 function displaceRobber(){

    disableMyButtons();
    enableMoveRobber = true;
 }



/*
//Activate Knight
function activateKnight() {
    if (nWheat > 0) {
        knight = true;
    }
}*/

/* Robber */

var RobberPos = false;

function drawRobber(id) {
    var hex = document.getElementById(id);
    if(RobberPos)
    {
        console.log("hi");
        d3.select("robber")      .attr("transform", "translate(" + (hex.__data__.x) + "," + (hex.__data__.y) + ")")
    }
    else {
        RobberPos = true;
        console.log(RobberPos);

        var symbolGenerator = d3.symbol()
            .type(d3.symbolWye)
            .size(350);

        var pathData = symbolGenerator();

        var hex = document.getElementById(id);
        console.log(JSON.stringify(hex));

        holder.append("path")
            .attr("d", pathData)
            .attr("fill", "#ff704d")
            .attr("transform", "translate(" + (hex.__data__.x) + "," + (hex.__data__.y) + ")")
            .attr("class", "robber")
            .attr("id", "robber");


        var f = $("#theRobber").attr("d");
        console.log(f);
        enableMoveRobber = false;
        console.log("moved robber");
    }
}


function moveRobber(id){
    console.log("send robber");
    stompClient.send("/app/placerobber",{},JSON.stringify({"hexId":id,"isValid":false,"hasStealable":false}));
}

function stealResource(id){
    stompClient.send("/app/stealresource", {}, JSON.stringify({"intersectionID":id, "isValid":false}))
}






/* Trade */

function startTimer()
{
    var x = document.getElementById("noResource");
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 7000);
}
function w3_open1() {
    document.getElementById("mySidebar1").style.display = "block";
    document.getElementById("myOverlay1").style.display = "block";
}
function w3_close1() {
    document.getElementById("mySidebar1").style.display = "none";
    document.getElementById("myOverlay1").style.display = "none";
}
function w3_open2() {
    document.getElementById("mySidebar2").style.display = "block";
    document.getElementById("myOverlay2").style.display = "block";
}
function w3_close2() {
    document.getElementById("mySidebar2").style.display = "none";
    document.getElementById("myOverlay2").style.display = "none";
}
function w3_open3() {
    document.getElementById("mySidebar3").style.display = "block";
    document.getElementById("myOverlay3").style.display = "block";
}
function w3_close3() {
    document.getElementById("mySidebar3").style.display = "none";
    document.getElementById("myOverlay3").style.display = "none";
}
function w3_open4() {
    document.getElementById("mySidebar4").style.display = "block";
    document.getElementById("myOverlay4").style.display = "block";
}
function w3_close4() {
    document.getElementById("mySidebar4").style.display = "none";
    document.getElementById("myOverlay4").style.display = "none";
}
function w3_open5() {
    document.getElementById("mySidebar5").style.display = "block";
    document.getElementById("myOverlay5").style.display = "block";
}
function w3_close5() {
    document.getElementById("mySidebar5").style.display = "none";
    document.getElementById("myOverlay5").style.display = "none";
}
// Trade
var reset;

var mgetBrick = false;
var mgetWood = false;
var mgetOre = false;
var mgetSheep = false;
var mgetWheat = false;

var mgiveWood = false;
var mgiveOre = false;
var mgiveBrick = false;
var mgiveSheep = false;
var mgiveWheat = false;

function resetMres() {
     mgetBrick = false;
     mgetWood = false;
     mgetOre = false;
     mgetSheep = false;
     mgetWheat = false;

     mgiveWood = false;
     mgiveOre = false;
     mgiveBrick = false;
     mgiveSheep = false;
     mgiveWheat = false;
}

function setMgetBrick() {
     mgetBrick = true;

     mgetWood = false;
     mgetOre = false;
     mgetSheep = false;
     mgetWheat = false;
}

function setMgetWood() {
     mgetWood = true;

     mgetBrick = false;
     mgetOre = false;
     mgetSheep = false;
     mgetWheat = false;
}

function setMgetOre() {
     mgetOre = true;

     mgetWood = false;
     mgetBrick = false;
     mgetSheep = false;
     mgetWheat = false;
}

function setMgetSheep() {
     mgetSheep = true;

     mgetWood = false;
     mgetOre = false;
     mgetBrick = false;
     mgetWheat = false;
}

function setMgetWheat() {
     mgetWheat = true;

     mgetWood = false;
     mgetOre = false;
     mgetSheep = false;
     mgetBrick = false;
}

/* give */


function setMgiveBrick() {
     mgiveBrick = true;
    console.log("Give brick fool");
     mgiveWood = false;
     mgiveOre = false;
     mgiveSheep = false;
     mgiveWheat = false;
}

function setMgiveWood() {
     mgiveWood = true;

     mgiveBrick = false;
     mgiveOre = false;
     mgiveSheep = false;
     mgiveWheat = false;
}

function setMgiveOre() {
     mgiveOre = true;

     mgiveWood = false;
     mgiveBrick = false;
     mgiveSheep = false;
     mgiveWheat = false;
}

function setMgiveSheep() {
     mgiveSheep = true;

     mgiveWood = false;
     mgiveOre = false;
     mgiveBrick = false;
     mgiveWheat = false;
}

function setMgiveWheat() {
     mgiveWheat = true;

     mgiveWood = false;
     mgiveOre = false;
     mgiveSheep = false;
     mgiveBrick = false;
}


function mTradeSend() {
    var give;
    var get;
    var ok = true;
    if(mgetBrick)
        get = 'Brick';
    else if(mgetWood)
        get = 'Wood';
    else if(mgetOre)
        get = 'Ore';
    else if(mgetSheep)
        get = 'Sheep';
    else if(mgetWheat)
        get = 'Wheat';
    else
        ok = false;

    if(mgiveBrick)
        give = 'Brick';
    else if(mgiveWood)
        give = 'Wood';
    else if(mgiveOre)
        give = 'Ore';
    else if(mgiveSheep)
        give = 'Sheep';
    else if(mgiveWheat)
        give = 'Wheat';
    else
        ok = false;

    if(ok)
        SendMaritime(get,give);
}


function mTrade(){
   // reset = document.getElementById('mtNum').value = '';

}


function pTrade(){
    document.getElementById('tradeP1').innerHTML = p1name;
    document.getElementById('tradeP2').innerHTML = p2name;
    document.getElementById('tradeP3').innerHTML = p3name;
    reset = document.getElementById('ptGiveBrick').value = '';
    reset = document.getElementById('ptGiveWood').value = '';
    reset = document.getElementById('ptGiveOre').value = '';
    reset = document.getElementById('ptGiveSheep').value = '';
    reset = document.getElementById('ptGiveWheat').value = '';
    reset = document.getElementById('ptGiveCloth').value = '';
    reset = document.getElementById('ptGivePaper').value = '';
    reset = document.getElementById('ptGiveCoin').value = '';
    reset = document.getElementById('ptGetBrick').value = '';
    reset = document.getElementById('ptGetWood').value = '';
    reset = document.getElementById('ptGetOre').value = '';
    reset = document.getElementById('ptGetSheep').value = '';
    reset = document.getElementById('ptGetWheat').value = '';
    reset = document.getElementById('ptGetCloth').value = '';
    reset = document.getElementById('ptGetPaper').value = '';
    reset = document.getElementById('ptGetCoin').value = '';
}
function rTrade(){
    reset = document.getElementById('ptrGiveBrick').value = '';
    reset = document.getElementById('ptrGiveWood').value = '';
    reset = document.getElementById('ptrGiveOre').value = '';
    reset = document.getElementById('ptrGiveSheep').value = '';
    reset = document.getElementById('ptrGiveWheat').value = '';
    reset = document.getElementById('ptrGiveCloth').value = '';
    reset = document.getElementById('ptrGivePaper').value = '';
    reset = document.getElementById('ptrGiveCoin').value = '';
    reset = document.getElementById('ptrGetBrick').value = '';
    reset = document.getElementById('ptrGetWood').value = '';
    reset = document.getElementById('ptrGetOre').value = '';
    reset = document.getElementById('ptrGetSheep').value = '';
    reset = document.getElementById('ptGetWheat').value = '';
    reset = document.getElementById('ptrGetCloth').value = '';
    reset = document.getElementById('ptrGetPaper').value = '';
    reset = document.getElementById('ptrGetCoin').value = '';
}
function aTrade(){
    reset = document.getElementById('ptrGiveBrick').value = document.getElementById('ptGetBrick').value;
    reset = document.getElementById('ptrGiveWood').value = document.getElementById('ptGetWood').value;
    reset = document.getElementById('ptrGiveOre').value = document.getElementById('ptGetOre').value;
    reset = document.getElementById('ptrGiveSheep').value = document.getElementById('ptGetSheep').value;
    reset = document.getElementById('ptrGiveWheat').value = document.getElementById('ptGetWheat').value;
    reset = document.getElementById('ptrGiveCloth').value = document.getElementById('ptGetCloth').value;
    reset = document.getElementById('ptrGivePaper').value = document.getElementById('ptGetPaper').value;
    reset = document.getElementById('ptrGiveCoin').value = document.getElementById('ptGetCoin').value;
    reset = document.getElementById('ptrGetBrick').value =document.getElementById('ptGiveBrick').value;
    reset = document.getElementById('ptrGetWood').value = document.getElementById('ptGiveWood').value;
    reset = document.getElementById('ptrGetOre').value = document.getElementById('ptGiveOre').value;
    reset = document.getElementById('ptrGetSheep').value = document.getElementById('ptGiveSheep').value;
    reset = document.getElementById('ptGetWheat').value = document.getElementById('ptGiveWheat').value;
    reset = document.getElementById('ptrGetCloth').value = document.getElementById('ptGiveCloth').value;
    reset = document.getElementById('ptrGetPaper').value = document.getElementById('ptGivePaper').value;
    reset = document.getElementById('ptrGetCoin').value = document.getElementById('ptGiveCoin').value;

    requestTrade();
}


/////////////////////////// COPY PASTED BOARDMAP BELOW //////////////////////////////////


function HexBlueprint(axial_x, axial_y, axial_z, size, resource)
{

    this.axial_x = axial_x;
    this.axial_y = axial_y;
    this.axial_z = axial_z;
    this.size = size;
    this.resource = resource;


    var height = this.size*2;
    var width = Math.sqrt(3)/2*height;

    var t = 600 + (this.axial_x-this.axial_y)*width/2;
    var h = 350 + this.axial_z*(0.75)*height;

    this.centre = {x:  t, y:  h};

    this.points = "";

    for(var i = 0; i<=5; i++)
    {
        this.points += this.get_Hex_corner(i).i + "," + this.get_Hex_corner(i).l + " ";
    }


}

HexBlueprint.prototype.get_Hex_corner = function(vertex)
{
    var angle_deg = 60 * vertex + 30;
    var angle_rad = Math.PI / 180 * angle_deg;
    var a = this.centre.x + (this.size - 16) * Math.cos(angle_rad);
    var b = this.centre.y + (this.size - 16) * Math.sin(angle_rad);

    return {i: a, l: b};
}

function EdgeBlueprint(axial_x, axial_y, axial_z, size, resource, hexcorner, edgelength, edgeheight)
{

    this.axial_x = axial_x;
    this.axial_y = axial_y;
    this.axial_z = axial_z;
    this.size = size;
    this.resource = resource;
    this.edgelength = edgelength;
    this.edgeheight = edgeheight;
    var height = this.size*2;
    this.width = Math.sqrt(3)/2*height;

    var t = 600 + (this.axial_x-this.axial_y)*this.width/2;
    var h = 350 + this.axial_z*(0.75)*height;

    this.centre = {x:t,y:h};

    this.edgepoint = this.get_Edge_corner(hexcorner);

}

EdgeBlueprint.prototype.get_Edge_corner = function(vertex)
{

    var angle_deg = 60 * vertex;
    var angle_rad = Math.PI / 180 * angle_deg;

    var a = this.centre.x + this.width/2*Math.cos(angle_rad);
    var b = this.centre.y + this.width/2*Math.sin(angle_rad);
    return {i: a, l: b};

}

function IntersectionBlueprint(axial_x, axial_y, axial_z, vertex, hex_size)
{

    this.axial_x = axial_x;
    this.axial_y = axial_y;
    this.axial_z = axial_z;
    this.hex_size = hex_size;
    this.radius = 8;

    var height = hex_size*2;
    var width = Math.sqrt(3)/2*height;

    var t = 600 + (this.axial_x-this.axial_y)*width/2;
    var h = 350 + this.axial_z*(0.75)*height;

    var angle_deg = 60 * vertex + 30;
    var angle_rad = Math.PI / 180 * angle_deg;
    var a = t + (this.hex_size) * Math.cos(angle_rad);
    var b = h + (this.hex_size) * Math.sin(angle_rad);

    this.centre = {x: a, y: b};

}



// a js object that describes the hexagons that make up the board.
var jsonPolygons = [{"x":340.19237886466846,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"378.2974966311838,642 340.19237886466846,664 302.08726109815314,642 302.08726109815314,598 340.19237886466846,576 378.2974966311838,598 ","id":"h_-1_4","terrain_type":"sea","number":0},
    {"x":288.23085463760214,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"326.33597240411746,552 288.23085463760214,574 250.12573687108681,552 250.12573687108684,508 288.23085463760214,486 326.33597240411746,508 ","id":"h_-2_4","terrain_type":"sea","number":0},
    {"x":236.26933041053582,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"274.37444817705114,462 236.26933041053582,484 198.1642126440205,462 198.16421264402052,418 236.26933041053582,396 274.37444817705114,418 ","id":"h_-3_4","terrain_type":"wood","number":3},
    {"x":184.3078061834695,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"222.41292394998482,372 184.3078061834695,394 146.20268841695417,372 146.2026884169542,328 184.3078061834695,306 222.4129239499848,328 ","id":"h_-4_4","terrain_type":"gold","number":10},
    {"x":444.11542731880104,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"482.22054508531636,642 444.11542731880104,664 406.0103095522857,642 406.0103095522857,598 444.11542731880104,576 482.2205450853163,598 ","id":"h_0_3","terrain_type":"sea","number":0},
    {"x":392.1539030917347,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"430.25902085825004,552 392.1539030917347,574 354.0487853252194,552 354.0487853252194,508 392.1539030917347,486 430.25902085825,508 ","id":"h_-1_3","terrain_type":"sea","number":0},
    {"x":340.19237886466846,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"378.2974966311838,462 340.19237886466846,484 302.08726109815314,462 302.08726109815314,418 340.19237886466846,396 378.2974966311838,418 ","id":"h_-2_3","terrain_type":"sea","number":0},{"x":288.23085463760214,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"326.33597240411746,372 288.23085463760214,394 250.12573687108681,372 250.12573687108684,328 288.23085463760214,306 326.33597240411746,328 ","id":"h_-3_3","terrain_type":"sea","number":0},{"x":236.26933041053582,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"274.37444817705114,282 236.26933041053582,304 198.1642126440205,282 198.16421264402052,238 236.26933041053582,216 274.37444817705114,237.99999999999997 ","id":"h_-4_3","terrain_type":"brick","number":5},{"x":548.0384757729337,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"586.143593539449,642 548.0384757729337,664 509.93335800641836,642 509.93335800641836,598 548.0384757729337,576 586.143593539449,598 ","id":"h_1_2","terrain_type":"sea","number":0},{"x":496.07695154586736,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"534.1820693123826,552 496.07695154586736,574 457.97183377935204,552 457.97183377935204,508 496.07695154586736,486 534.1820693123826,508 ","id":"h_0_2","terrain_type":"brick","number":4},{"x":444.11542731880104,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"482.22054508531636,462 444.11542731880104,484 406.0103095522857,462 406.0103095522857,418 444.11542731880104,396 482.2205450853163,418 ","id":"h_-1_2","terrain_type":"sheep","number":8},{"x":392.1539030917347,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"430.25902085825004,372 392.1539030917347,394 354.0487853252194,372 354.0487853252194,328 392.1539030917347,306 430.25902085825,328 ","id":"h_-2_2","terrain_type":"ore","number":10},{"x":340.19237886466846,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"378.2974966311838,282 340.19237886466846,304 302.08726109815314,282 302.08726109815314,238 340.19237886466846,216 378.2974966311838,237.99999999999997 ","id":"h_-3_2","terrain_type":"sea","number":0},{"x":288.23085463760214,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"326.33597240411746,192 288.23085463760214,214 250.12573687108681,192 250.12573687108684,148 288.23085463760214,126 326.33597240411746,147.99999999999997 ","id":"h_-4_2","terrain_type":"sea","number":0},
    {"x":651.9615242270663,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"690.0666419935816,642 651.9615242270663,664 613.856406460551,642 613.856406460551,598 651.9615242270663,576 690.0666419935816,598 ","id":"h_2_1","terrain_type":"sea","number":0},{"x":600,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"638.1051177665153,552 600,574 561.8948822334847,552 561.8948822334847,508 600,486 638.1051177665153,508 ","id":"h_1_1","terrain_type":"wood","number":6},{"x":548.0384757729337,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"586.143593539449,462 548.0384757729337,484 509.93335800641836,462 509.93335800641836,418 548.0384757729337,396 586.143593539449,418 ","id":"h_0_1","terrain_type":"wheat","number":2},{"x":496.07695154586736,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"534.1820693123826,372 496.07695154586736,394 457.97183377935204,372 457.97183377935204,328 496.07695154586736,306 534.1820693123826,328 ","id":"h_-1_1","terrain_type":"wood","number":5},{"x":444.11542731880104,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"482.22054508531636,282 444.11542731880104,304 406.0103095522857,282 406.0103095522857,238 444.11542731880104,216 482.2205450853163,237.99999999999997 ","id":"h_-2_1","terrain_type":"wood","number":9},{"x":392.1539030917347,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"430.25902085825004,192 392.1539030917347,214 354.0487853252194,192 354.0487853252194,148 392.1539030917347,126 430.25902085825,147.99999999999997 ","id":"h_-3_1","terrain_type":"sea","number":0},{"x":340.19237886466846,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"378.2974966311838,102 340.19237886466846,124 302.08726109815314,102 302.08726109815314,58 340.19237886466846,36 378.2974966311838,57.99999999999998 ","id":"h_-4_1","terrain_type":"sea","number":0},{"x":755.884572681199,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"793.9896904477142,642 755.884572681199,664 717.7794549146837,642 717.7794549146837,598 755.884572681199,576 793.9896904477142,598 ","id":"h_3_0","terrain_type":"sea","number":0},{"x":703.9230484541326,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"742.0281662206479,552 703.9230484541326,574 665.8179306876174,552 665.8179306876174,508 703.9230484541326,486 742.0281662206479,508 ","id":"h_2_0","terrain_type":"wheat","number":12},{"x":651.9615242270663,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"690.0666419935816,462 651.9615242270663,484 613.856406460551,462 613.856406460551,418 651.9615242270663,396 690.0666419935816,418 ","id":"h_1_0","terrain_type":"wheat","number":11},{"x":600,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"638.1051177665153,372 600,394 561.8948822334847,372 561.8948822334847,328 600,306 638.1051177665153,328 ","id":"h_0_0","terrain_type":"sheep","number":9},{"x":548.0384757729337,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"586.143593539449,282 548.0384757729337,304 509.93335800641836,282 509.93335800641836,238 548.0384757729337,216 586.143593539449,237.99999999999997 ","id":"h_-1_0","terrain_type":"ore","number":2},{"x":496.07695154586736,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"534.1820693123826,192 496.07695154586736,214 457.97183377935204,192 457.97183377935204,148 496.07695154586736,126 534.1820693123826,147.99999999999997 ","id":"h_-2_0","terrain_type":"sheep","number":8},{"x":444.11542731880104,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"482.22054508531636,102 444.11542731880104,124 406.0103095522857,102 406.0103095522857,58 444.11542731880104,36 482.2205450853163,57.99999999999998 ","id":"h_-3_0","terrain_type":"sea","number":0},{"x":859.8076211353316,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"897.9127389018469,642 859.8076211353316,664 821.7025033688163,642 821.7025033688163,598 859.8076211353316,576 897.9127389018469,598 ","id":"h_4_-1","terrain_type":"sea","number":0},{"x":807.8460969082653,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"845.9512146747805,552 807.8460969082653,574 769.74097914175,552 769.74097914175,508 807.8460969082653,486 845.9512146747805,508 ","id":"h_3_-1","terrain_type":"sea","number":0},{"x":755.884572681199,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"793.9896904477142,462 755.884572681199,484 717.7794549146837,462 717.7794549146837,418 755.884572681199,396 793.9896904477142,418 ","id":"h_2_-1","terrain_type":"wood","number":3},{"x":703.9230484541326,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"742.0281662206479,372 703.9230484541326,394 665.8179306876174,372 665.8179306876174,328 703.9230484541326,306 742.0281662206479,328 ","id":"h_1_-1","terrain_type":"sheep","number":2},{"x":651.9615242270663,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"690.0666419935816,282 651.9615242270663,304 613.856406460551,282 613.856406460551,238 651.9615242270663,216 690.0666419935816,237.99999999999997 ","id":"h_0_-1","terrain_type":"desert","number":0},
    {"x":600,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"638.1051177665153,192 600,214 561.8948822334847,192 561.8948822334847,148 600,126 638.1051177665153,147.99999999999997 ","id":"h_-1_-1","terrain_type":"brick","number":4},{"x":548.0384757729337,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"586.143593539449,102 548.0384757729337,124 509.93335800641836,102 509.93335800641836,58 548.0384757729337,36 586.143593539449,57.99999999999998 ","id":"h_-2_-1","terrain_type":"sea","number":0},{"x":911.7691453623979,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"949.8742631289132,552 911.7691453623979,574 873.6640275958827,552 873.6640275958827,508 911.7691453623979,486 949.8742631289132,508 ","id":"h_4_-2","terrain_type":"sea","number":0},{"x":859.8076211353316,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"897.9127389018469,462 859.8076211353316,484 821.7025033688163,462 821.7025033688163,418 859.8076211353316,396 897.9127389018469,418 ","id":"h_3_-2","terrain_type":"sea","number":0},{"x":807.8460969082653,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"845.9512146747805,372 807.8460969082653,394 769.74097914175,372 769.74097914175,328 807.8460969082653,306 845.9512146747805,328 ","id":"h_2_-2","terrain_type":"ore","number":10},{"x":755.884572681199,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"793.9896904477142,282 755.884572681199,304 717.7794549146837,282 717.7794549146837,238 755.884572681199,216 793.9896904477142,237.99999999999997 ","id":"h_1_-2","terrain_type":"brick","number":3},{"x":703.9230484541326,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"742.0281662206479,192 703.9230484541326,214 665.8179306876174,192 665.8179306876174,148 703.9230484541326,126 742.0281662206479,147.99999999999997 ","id":"h_0_-2","terrain_type":"wheat","number":6},{"x":651.9615242270663,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"690.0666419935816,102 651.9615242270663,124 613.856406460551,102 613.856406460551,58 651.9615242270663,36 690.0666419935816,57.99999999999998 ","id":"h_-1_-2","terrain_type":"sea","number":0},{"x":963.7306695894642,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"1001.8357873559795,462 963.7306695894642,484 925.625551822949,462 925.625551822949,418 963.7306695894642,396 1001.8357873559795,418 ","id":"h_4_-3","terrain_type":"wheat","number":11},{"x":911.7691453623979,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"949.8742631289132,372 911.7691453623979,394 873.6640275958827,372 873.6640275958827,328 911.7691453623979,306 949.8742631289132,328 ","id":"h_3_-3","terrain_type":"sea","number":0},{"x":859.8076211353316,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"897.9127389018469,282 859.8076211353316,304 821.7025033688163,282 821.7025033688163,238 859.8076211353316,216 897.9127389018469,237.99999999999997 ","id":"h_2_-3","terrain_type":"sea","number":0},{"x":807.8460969082653,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"845.9512146747805,192 807.8460969082653,214 769.74097914175,192 769.74097914175,148 807.8460969082653,126 845.9512146747805,147.99999999999997 ","id":"h_1_-3","terrain_type":"sea","number":0},
    {"x":755.884572681199,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"793.9896904477142,102 755.884572681199,124 717.7794549146837,102 717.7794549146837,58 755.884572681199,36 793.9896904477142,57.99999999999998 ","id":"h_0_-3","terrain_type":"sea","number":0},{"x":1015.6921938165306,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"1053.797311583046,372 1015.6921938165306,394 977.5870760500153,372 977.5870760500153,328 1015.6921938165306,306 1053.797311583046,328 ","id":"h_4_-4","terrain_type":"sheep","number":2},{"x":963.7306695894642,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"1001.8357873559795,282 963.7306695894642,304 925.625551822949,282 925.625551822949,238 963.7306695894642,216 1001.8357873559795,237.99999999999997 ","id":"h_3_-4","terrain_type":"gold","number":12},
    {"x":911.7691453623979,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"949.8742631289132,192 911.7691453623979,214 873.6640275958827,192 873.6640275958827,148 911.7691453623979,126 949.8742631289132,147.99999999999997 ","id":"h_2_-4","terrain_type":"sea","number":0},{"x":859.8076211353316,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"897.9127389018469,102 859.8076211353316,124 821.7025033688163,102 821.7025033688163,58 859.8076211353316,36 897.9127389018469,57.99999999999998 ","id":"h_1_-4","terrain_type":"sea","number":0}];


// just a js object that holds the data to append circles for displaying production numbers
var jsonNumCircles =
    [{"x":236.26933041053582,"y":440,"x_axial":-4,"y_axial":3,"radius":14,"number":3},{"x":184.3078061834695,"y":350,"x_axial":-4,"y_axial":4,"radius":14,"number":10},{"x":236.26933041053582,"y":260,"x_axial":-3,"y_axial":4,"radius":14,"number":5},{"x":496.07695154586736,"y":530,"x_axial":-2,"y_axial":0,"radius":14,"number":4},{"x":444.11542731880104,"y":440,"x_axial":-2,"y_axial":1,"radius":14,"number":8},{"x":392.1539030917347,"y":350,"x_axial":-2,"y_axial":2,"radius":14,"number":10}
        ,{"x":600,"y":530,"x_axial":-1,"y_axial":-1,"radius":14,"number":6},{"x":548.0384757729337,"y":440,"x_axial":-1,"y_axial":0,"radius":14,"number":2},{"x":496.07695154586736,"y":350,"x_axial":-1,"y_axial":1,"radius":14,"number":5},{"x":444.11542731880104,"y":260,"x_axial":-1,"y_axial":2,"radius":14,"number":9},{"x":703.9230484541326,"y":530,"x_axial":0,"y_axial":-2,"radius":14,"number":12}
        ,{"x":651.9615242270663,"y":440,"x_axial":0,"y_axial":-1,"radius":14,"number":11}
        ,{"x":600,"y":350,"x_axial":0,"y_axial":0,"radius":14,"number":9},{"x":548.0384757729337,"y":260,"x_axial":0,"y_axial":1,"radius":14,"number":2},{"x":496.07695154586736,"y":170,"x_axial":0,"y_axial":2,"radius":14,"number":8},{"x":755.884572681199,"y":440,"x_axial":1,"y_axial":-2,"radius":14,"number":3},{"x":703.9230484541326,"y":350,"x_axial":1,"y_axial":-1,"radius":14,"number":2},{"x":600,"y":170,"x_axial":1,"y_axial":1,"radius":14,"number":4},{"x":807.8460969082653,"y":350,"x_axial":2,"y_axial":-2,"radius":14,"number":10}
        ,{"x":755.884572681199,"y":260,"x_axial":2,"y_axial":-1,"radius":14,"number":3},{"x":703.9230484541326,"y":170,"x_axial":2,"y_axial":0,"radius":14,"number":6},{"x":963.7306695894642,"y":440,"x_axial":3,"y_axial":-4,"radius":14,"number":11}
        ,{"x":1015.6921938165306,"y":350,"x_axial":4,"y_axial":-4,"radius":14,"number":2},{"x":963.7306695894642,"y":260,"x_axial":4,"y_axial":-3,"radius":14,"number":12}];

setTimeout(init,1500);

function init() {
    var edgeHeight = 30;
    var edgeWidth = 10;
    var jsonEdges = [];
    var jsonIntersections = [];
    var numCircleRadius = 14;
    var board_radius = 4;
    var hxradius = 60;
    var polyPoints = [];

    // generate the edge and intersection js objects
    for(q = -board_radius; q<= board_radius; q++)
    {
        r1 = Math.max(-board_radius, -q - board_radius);
        r2 = Math.min(board_radius, -q + board_radius);

        for(r = r1; r <= r2; r++)
        {
            b = -q - r;

            //draw empty hexes save the top most and bottom most rows
            if (b != board_radius && b != -board_radius)
            {
                // generate hex production numbers
                //var getNumCircleCoords = new HexBlueprint(q,r,b,hxradius,"");
                //jsonNumCircles.push({x: getNumCircleCoords.centre.x, y: getNumCircleCoords.centre.y, x_axial: q, y_axial: r,
                //radius: numCircleRadius, diceNum: 11});



                x=q;
                y=r;
                ny = y-1;
                my = y+1;
                nx = x-1;
                mx = x+1;
                var tempEdge;

                var EdgeHeight = 2*hxradius/3;
                var EdgeWidth = hxradius;

                // MAKE EDGES
                // top left
                var edge = new EdgeBlueprint(x, y, x+y, hxradius, "", 4, edgeHeight, edgeWidth);

                //var edge = new SideEdgeBlueprint(x, y, x+y, EdgeHeight,'top');
                var edgeValues = {"x": edge.edgepoint.i, "y": edge.edgepoint.l,
                    "stroke":"black", "stroke_width": "3", "fill" : "black", "rotate_amount": "330", "id": "e1_"+x+"_"+y};


                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }

                // left side
                edge = new EdgeBlueprint(x, y, x + y, hxradius, "", 3, edgeHeight, edgeWidth);
                edgeValues = {"x": edge.edgepoint.i, "y": edge.edgepoint.l,
                    "stroke":"black", "stroke_width": "3", "fill" : "black", "rotate_amount": "270", "id": "e2_"+x+"_"+y};

                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;
                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }


                // bottom left
                edge = new EdgeBlueprint(x, y, x+y, hxradius, "", 2, edgeHeight, edgeWidth);
                edgeValues = {"x": edge.edgepoint.i, "y": edge.edgepoint.l,
                    "stroke":"black", "stroke_width": "3", "fill" : "black", "rotate_amount": "210", "id": "e3_"+x+"_"+y};


                var tempEdge;
                var search = true;

                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;
                    }
                }

                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }


                // top right
                edge = new EdgeBlueprint(x, ny, x+ny, hxradius, "", 2, edgeHeight, edgeWidth);
                edgeValues = {"x": edge.edgepoint.i, "y": edge.edgepoint.l,
                    "stroke":"black", "stroke_width": "3", "fill" : "black", "rotate_amount": "210", "id": "e3_"+x+"_"+ny};

                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }


                // right side
                edge = new EdgeBlueprint(mx, ny, mx+ny, hxradius, "", 3, edgeHeight, edgeWidth);
                edgeValues = {"x": edge.edgepoint.i, "y": edge.edgepoint.l,
                    "stroke":"black", "stroke_width": "3", "fill" : "black", "rotate_amount": "270", "id": "e2_"+mx+"_"+ny};

                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }


                // bottom right
                edge = new EdgeBlueprint(mx, y, mx+y, hxradius, "", 4, edgeHeight, edgeWidth);
                edgeValues = {"x": edge.edgepoint.i, "y": edge.edgepoint.l,
                    "stroke":"black", "stroke_width": "3", "fill" : "black", "rotate_amount": "330", "id": "e1_"+mx+"_"+y};




                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }

                // GENERATE INTERSECTIONS


                var IntersectionNeighbours = [];

                var Intersection = new IntersectionBlueprint(x, y, x+y, 3, hxradius);
                circleValues = {"x": Intersection.centre.x, "y": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i3_"+x+"_"+y};
                var tempIntersection;
                var search = true;

                for(var i = 0; i<jsonIntersections.length; i++)
                {
                    tempIntersection = jsonIntersections[i];

                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempEdge);
                    }
                }
                if(search == true)
                {
                    IntersectionNeighbours.push(circleValues);
                    jsonIntersections.push(circleValues);
                }

                var Intersection = new IntersectionBlueprint(x, y, x+y, 4,hxradius);
                circleValues = {"x": Intersection.centre.x, "y": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i4_"+ x+"_"+y};
                var tempIntersection;
                var search = true;

                for(var i=0;i<jsonIntersections.length;i++)
                {
                    tempIntersection = jsonIntersections[i];

                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }
                if(search==true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }

                var Intersection = new IntersectionBlueprint(x, my, x+my, 4,hxradius);
                circleValues = {"x": Intersection.centre.x, "y": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i4_"+x+"_"+my};
                var tempIntersection;
                var search = true;

                for(var i=0;i<jsonIntersections.length;i++)
                {
                    tempIntersection = jsonIntersections[i];

                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }

                if(search == true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }


                var Intersection = new IntersectionBlueprint(mx, ny, mx+ny, 3,hxradius);
                circleValues = {"x": Intersection.centre.x, "y": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i3_"+mx+"_"+ny};
                var tempIntersection;
                var search = true;

                for(var i=0;i<jsonIntersections.length;i++)
                {
                    tempIntersection = jsonIntersections[i];
                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }
                if(search==true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }


                var Intersection = new IntersectionBlueprint(mx, y, mx+y, 3,hxradius);
                circleValues = {"x": Intersection.centre.x, "y": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i3_"+mx+"_"+y};
                var tempIntersection;
                var search = true;

                for(var i=0;i<jsonIntersections.length;i++)
                {
                    tempIntersection = jsonIntersections[i];

                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }

                if(search==true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }


                var Intersection = new IntersectionBlueprint(mx, y, mx+y, 4,hxradius);
                circleValues = {"x": Intersection.centre.x, "y": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i4_"+mx+"_"+y};
                var tempIntersection;
                var search = true;

                for(var i=0;i<jsonIntersections.length;i++)
                {
                    tempIntersection = jsonIntersections[i];

                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }
                if(search==true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }

            }
        }

    }

//    console.log(JSON.stringify(jsonIntersections));

    if(startingPlayer.match(myUsername)) {
        sendHex();
    }

    var color ='blue';

    // H_0_-1

    // append the board hexagons to the DOM
    var boardHexes = holder.selectAll("hexes")
        .data(jsonPolygons)
        .enter()
        .append("polygon");
////



    /////
    var hexeAttrs = boardHexes
        .attr("class", "hex")
        .attr("points", function (d) { return d.points; })
        .attr("stroke", function (d) { return 0; })
        .attr("id", function(d) { return d.id; }) // NEW
        .attr("number", function(d) { return d.number; }) // NEW
        .attr("stroke-width", function (d) { return d.stroke_width; })
        .on("click", function (d) {

            if(currUser.match(myUsername)){
                console.log("on click hex");
                if(enableMoveRobber){

                    moveRobber(d.id);
                }

            }
        })
        .style("fill", function (d) {if (d.terrain_type === "sea"){ return "#336699";}
        else if(d.terrain_type === "wheat") { return "#ffff66";}
        else if(d.terrain_type === "sheep"){ return "#99ff66";}
        else if(d.terrain_type === "wood"){ return "#008060";}
        else if(d.terrain_type === "ore"){ return "#999999";}
        else if(d.terrain_type === "brick"){ return "#c65353";}
        else if(d.terrain_type === "desert"){ return "#ffffb3";}
        else if(d.terrain_type === "gold"){ return "#e6b800";}
        else {return "white";}});

    // append the board intersections to the DOM
    var boardIntersections = holder.selectAll("intersections")
        .data(jsonIntersections)
        .enter()
        .append("circle");

    var intersectionAttrs = boardIntersections
        .attr("class", "intersection")
        .attr("cx", function (d) { return d.x; })
        .attr("cy", function (d) { return d.y; })
        .attr("r", function (d) { return d.radius; })
        .attr("id",function (d) { return d.id; })
        .attr("fill","black")
        .attr("stroke","white")
        .attr("hasCity", "true")
        .attr("class", "intersection")
        .attr("stroke","black")
        .on("click", function (d) {
            if(currUser.match(myUsername)){
                //if(boardEnabled){

                if(enableSteal){
                    console.log("stealing");
                    stealResource(d.id);
                }

                else if(!settlementPlaced && isSetup1){
                        placeSettlement(d.id);


                }else if(!cityPlaced && settlementPlaced && road1Placed && isSetup2){
                        placeCity(d.id);

                }else if(enableBuyAndUpgrade){


                    if(clickBuySettlement){

                            buildSettlement(d.id);

                    }else if(clickUpgradeCity){

                            buildCity(d.id);

                    }else if(clickBuyKnight){
                            buildKnight(d.id);
                    }else if(clickActivateKnight){
                            buyActivateKnight(d.id);
                    }else if(clickUpgradeKnight){
                            upgradeKnight(d.id);
                    }

                }
            }

        });



    // append the board edges to the DOM
    var edges = holder.selectAll("edges")
        .data(jsonEdges)
        .enter()
        .append("rect");

    var edgeAttrs = edges.attr("stroke", function (d) { return d.stroke; })
        .attr("class", "edge")
        .attr("id", function(d) { return d.id; })
        .attr("stroke-width", function (d) { return 0; })
        .attr("fill", function (d) {return d.fill;})
        .attr("x", function(d) {return d.x;})
        .attr("y", function(d) {return d.y;})
        .attr("width", edgeHeight)
        .attr("height", edgeWidth)
        .attr("transform", function(d){var x_translate = -edgeHeight/2;
            var y_translate = -edgeWidth/2;
            return "rotate(" + d.rotate_amount +","+d.x+ "," + d.y + ")"+ " translate(" + x_translate + "," + y_translate +")";})
        .on("click", function (d) {

                if(currUser.match(myUsername)){  // what is this

                    if(!road1Placed && settlementPlaced && isSetup1){
                        placeRoad(d.id);
                    }


                    else if(!road2Placed && road1Placed && cityPlaced && isSetup2){
                        placeRoad(d.id);
                    }

                    if(enableBuyAndUpgrade){

                        if(clickBuyRoad){
                                buildRoad(d.id);

                        }

                    }

                }


            });



    // append the board production numbers
    var hexProdCircs = holder.selectAll("prodCircs")
        .data(jsonNumCircles)
        .enter()
        .append("circle");

    var prodCircAttrs = hexProdCircs
        .attr("class", "prodCirc")
        .attr("cx", function (d) { return d.x; })
        .attr("cy", function (d) { return d.y; })
        .attr("r", function (d) { return d.radius; })
        .attr("fill","white")
        .attr("stroke","white")
        .attr("stroke-width", 4);

    var text = holder.selectAll("text")
        .data(jsonNumCircles)
        .enter()
        .append("text");

    var textLabels = text
        .attr("x", function(d) { return d.x; })
        .attr("y", function(d) { return d.y + 6; })
        .text( function (d) { return d.number; })
        .attr("font-family", "sans-serif")
        .attr("font-size", "16px")
        .attr("fill", "black")
        .attr("text-anchor", "middle");


}