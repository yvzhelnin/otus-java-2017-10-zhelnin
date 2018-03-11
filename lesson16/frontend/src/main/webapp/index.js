"use strict";

$('#loginButton').click(function () {
    var socket = new WebSocket("ws://localhost:8080/frontend/getCacheData/" + $('#uname').val() + '/' + $('#psw').val());
    socket.onopen = function (event) {
        socket.send('');
        $('#loginForm').hide();
        $('#cacheInfo').show();
    };

    socket.onclose = function (event) {
        $('#loginForm').show();
        $('#cacheInfo').hide();
        console.log("connection closed");
    };

    socket.onmessage = function (event) {
        var response = JSON.parse(event.data);
        if (response) {
            $('#maxCacheSize').text(response.maxCacheSize);
            $('#currentCacheSize').text(response.currentCacheSize);
            $('#hitsCount').text(response.hitsCount);
            $('#missesCount').text(response.missesCount);
        }
        socket.send('');
    };

    socket.onerror = function (event) {
    };

    var userName = $('#uname').val();
    var password = $('#psw').val();
    if (!userName || !password) {
        alert('Не все поля заполнены');
    }
});
