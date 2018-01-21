"use strict";

function getCacheData() {
    $('#loginForm').hide();
    $('#cacheInfo').show();
    $.ajax({
        url: "/cache/getData",
        type: "post",
        error: function () {
            alert('Не удалось получить данные кеша');
        },
        success: function (data) {
            $('#maxCacheSize').text(data.maxCacheSize);
            $('#currentCacheSize').text(data.currentCacheSize);
            $('#hitsCount').text(data.hitsCount);
            $('#missesCount').text(data.missesCount);
        }
    });
}

$('#loginButton').click(function() {
    var userName = $('#uname').val();
    var password = $('#psw').val();
    if (!userName || !password) {
        alert('Не все поля заполнены');
    } else {
        $.ajax({
            url: "/login",
            type: "post",
            data: {
                userName: userName,
                password: password
            },
            error: function () {
                alert('Неправильное имя пользователя или пароль')
            },
            success: function () {
                getCacheData();
                setInterval(getCacheData, 1000);
            }
        });
    }
});
