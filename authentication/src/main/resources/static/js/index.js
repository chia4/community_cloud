window.onload = function () {
    var request = new XMLHttpRequest();
    request.open("GET", "/announcement-message-board/get-latest-announcement");
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            var announcement = JSON.parse(request.responseText);
            if (announcement != null) {
                date = new Date(announcement["timestamp"] * 1000)
                $("announcement-time").innerHTML = "赞:&nbsp;" + announcement["likes"] + "&nbsp;&nbsp;|&nbsp;&nbsp;" + date.toLocaleString();
                $("announcement").innerText = "公告: " + announcement["content"];
            }
        }
    }
    getMessageBoard();
}

function $(x) {
    return document.getElementById(x);
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
                html += "<span style='font-size: small;'>" + "赞:&nbsp;" + response[set]["likes"] + "&nbsp;&nbsp;|&nbsp;&nbsp;id:&nbsp;" + response[set]["id"] + "&nbsp;&nbsp;|&nbsp;&nbsp;" + date + "</span><br>"
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
