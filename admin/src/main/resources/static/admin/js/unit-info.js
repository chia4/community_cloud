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
    request.open("GET", "/admin/get-unit" + window.location.search);
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
                cell.innerText = response["caretaker"];
                cell = row.insertCell(-1);
                cell.innerText = response["cleaner"];
            }
        }
    }
}

function getRoom() {
    var room = $("room");
    var request = new XMLHttpRequest();
    request.open("GET", "/admin/get-rooms-by-unit" + window.location.search);
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            if (request.responseText === "[]") {
                var row = room.insertRow(-1);
                var cell = row.insertCell(-1);
                cell.colSpan = 3;
                cell.innerText = "空";
            } else {
                var response = JSON.parse(request.responseText);
                for (var set in response) {
                    row = room.insertRow(-1);
                    cell = row.insertCell(-1);
                    cell.innerText = response[set]["unitNumber"];
                    cell = row.insertCell(-1);
                    cell.innerText = response[set]["roomNumber"];
                    cell = row.insertCell(-1);
                    cell.innerText = response[set]["houseHolder"];
                }
            }
        }
    }
}
