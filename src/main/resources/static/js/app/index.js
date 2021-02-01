var main = {
    init: function () {
        var _this = this;
        $('#btn-user-info').on('click', function () {
            _this.search_user_info();
        });
    },
    search_user_info: function () {
        const nickname = $('#nickname').val();
        if (nickname === '') {
            alert("구단주를 입력하세요!");
        } else {
            $.ajax({
                type: 'GET',
                url: '/api/v1/user/' + nickname,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function (id) {
                window.location.href = '/user/info/'+id;
            }).fail(function () {
                alert("존재하지 않는 구단주입니다!");
            });
        }
    }
};

main.init();