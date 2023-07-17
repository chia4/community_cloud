window.onload = function () {
    var errorInfo = getParamValue("error");
    if (errorInfo !== false) {
        $("error").innerText = errorInfo;
    }

    var request = new XMLHttpRequest();
    request.open("GET", "/residence/info");
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            var residence = JSON.parse(request.responseText);
            $("welcome").innerText = "你好, " + residence["name"] + ", ";
            var info = $("info");
            row = info.insertRow(-1);
            cell = row.insertCell(-1);
            cell.innerText = residence["username"];
            cell = row.insertCell(-1);
            cell.innerText = residence["name"];
            cell = row.insertCell(-1);
            cell.innerText = residence["unitNumber"];
            cell = row.insertCell(-1);
            cell.innerText = residence["roomNumber"];
        }
    }

    getUnit();
    getRoom();
    getAnnouncement();
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

function getUnit() {
    var unit = $("unit");
    var request = new XMLHttpRequest();
    request.open("GET", "/residence/room-info" + window.location.search);
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            if (request.responseText === "") {
                var row = unit.insertRow(-1);
                var cell = row.insertCell(-1);
                cell.colSpan = 3;
                cell.innerText = "空";
            } else {
                var response = JSON.parse(request.responseText);
                row = unit.insertRow(-1);
                cell = row.insertCell(-1);
                cell.innerText = response["unitNumber"];
                cell = row.insertCell(-1);
                cell.innerText = response["roomNumber"];
                cell = row.insertCell(-1);
                cell.innerText = response["houseHolder"];
            }
        }
    }
}

function getRoom() {
    var room = $("room");
    var request = new XMLHttpRequest();
    request.open("GET", "/residence/family-info" + window.location.search);
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            if (request.responseText === "[]") {
                var row = room.insertRow(-1);
                var cell = row.insertCell(-1);
                cell.colSpan = 2;
                cell.innerText = "空";
            } else {
                var response = JSON.parse(request.responseText);
                for (var set in response) {
                    row = room.insertRow(-1);
                    cell = row.insertCell(-1);
                    cell.innerText = response[set]["username"];
                    cell = row.insertCell(-1);
                    cell.innerText = response[set]["name"];
                }
            }
        }
    }
}

function getAnnouncement() {
    var request = new XMLHttpRequest();
    request.open("GET", "/announcement-message-board/get-latest-announcement");
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            var announcement = JSON.parse(request.responseText);
            if (announcement != null) {
                date = new Date(announcement["timestamp"] * 1000)
                $("announcement-time").innerHTML = "<a href='/residence/like-announcement?id=" + announcement["id"] + "'>赞:&nbsp;" + announcement["likes"] + "</a>&nbsp;&nbsp;|&nbsp;&nbsp;" + date.toLocaleString()
                $("announcement").innerText = "公告: " + announcement["content"];
            }
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
                html += "<span style='font-size: small;'>" + "<a href='/residence/like-message-board?id=" + response[set]["id"] + "'>赞:&nbsp;" + response[set]["likes"] + "</a>&nbsp;&nbsp;|&nbsp;&nbsp;id:&nbsp;" + response[set]["id"] + "&nbsp;&nbsp;|&nbsp;&nbsp;" + date + "</span><br>"
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