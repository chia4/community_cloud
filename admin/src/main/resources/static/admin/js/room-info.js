window.onload = function () {
    getUnit();
    getRoom();
}

function $(x) {
    return document.getElementById(x);
}

function getUnit() {
    var unit = $("unit");
    var request = new XMLHttpRequest();
    request.open("GET", "/admin/get-room" + window.location.search);
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
    request.open("GET", "/admin/get-residences-by-room" + window.location.search);
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
