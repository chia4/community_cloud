window.onload = function () {
    var errorInfo = getParamValue("error");
    if (errorInfo !== false) {
        $("error").innerText = errorInfo;
    }

    var request = new XMLHttpRequest();
    request.open("GET", "/announcement-message-board/get-latest-announcement");
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            var announcement = JSON.parse(request.responseText);
            if (announcement != null) {
                date = new Date(announcement["timestamp"] * 1000)
                $("announcement-time").innerText = date.toLocaleString()
                $("announcement").innerText = "公告: " + announcement["content"];
            }
        }
    }
    getUnits();
    getRooms();
    getMessageBoard();
}

function $(x) {
    return document.getElementById(x);
}

function getParamValue(key) {
    var query = window.location.search.substring(1);
    var params = query.split("&");
    for (var i = 0; i < params.length; i++) {
        var param = params[i].split("=");
        if (param[0] === key) {
            return decodeURI(param[1]);
        }
    }
    return false;
}

function getUnits() {
    var request = new XMLHttpRequest();
    request.open("GET", "/unit-room/get-units");
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            units = JSON.parse(request.responseText);
            for (var i = 0; i < units.length; i++) {
                $("unit-number2").options.add(new Option(units[i], units[i]));
                $("unit-number3").options.add(new Option(units[i], units[i]));
            }
        }
    }
}

function getRooms() {
    var rooms;
    var request = new XMLHttpRequest();
    request.open("GET", "/unit-room/get-rooms");
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            rooms = JSON.parse(request.responseText);
            var units = Object.keys(rooms);
            for (var i = 0; i < units.length; i++) {
                $("unit-number4").options.add(new Option(units[i], units[i]));
            }
        }
    }
    $("unit-number4").onchange = function () {
        var unitNumber = $("unit-number4").options[$("unit-number4").selectedIndex].value;
        $("room-number2").innerHTML = "<option value=\"not-selected\">-</option>"
        for (var i = 0; i < rooms[unitNumber].length; i++) {
            $("room-number2").options.add(new Option(rooms[unitNumber][i], rooms[unitNumber][i]));
        }
    }
}

function getMessageBoard() {
    var request = new XMLHttpRequest();
    request.open("GET", "/announcement-message-board/get-message-board");
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            var response = JSON.parse(request.responseText);
            var messageBoardDiv = "<div style=\"background-color: #E3E1D2;float: left;width: 650px;margin: 10px 0\"><div style='margin: 15px'>"
            for (var set in response) {
                var date = new Date(response[set]["timestamp"] * 1000).toLocaleString();
                var html = messageBoardDiv;
                html += "<span style='font-weight: bold;font-size: small;'>" + response[set]["username"] + "</span><br>"
                html += "<span style='font-size: small;'>id: " + response[set]["id"] + "&nbsp;&nbsp;|&nbsp;&nbsp;" + date + "</span><br>"
                html += response[set]["content"];
                if (response[set]["feedback"] != null) {
                    html += "<br><br><span style='color: red;font-weight: bold;font-size: small;'>回复: " + response[set]["feedback"] + "</span><br>"
                }
                html += "</div></div>";
                $("message-board").innerHTML += html;
            }
        }
    }
}