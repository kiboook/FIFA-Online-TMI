const main = {
    init: function () {
        const _this = this;
        const searchBtn = document.querySelector("#btn-user-info");
        if (searchBtn) {
            searchBtn.addEventListener('click', function () {
                _this.search_user_info();
            });
        }

        const searchEnter = document.querySelector("#nickname");
        if (searchEnter) {
            searchEnter.addEventListener('keyup', key => {
                if (key.keyCode === 13) {
                    _this.search_user_info();
                }
            })
        }
    },
    search_user_info: function () {
        const nickname = document.querySelector("#nickname").value;

        if (nickname === '') {
            alert("구단주를 입력하세요!");
        } else {
            $.ajax({
                type: 'GET',
                url: `/api/v1/user/${nickname}`
            }).done(function (nickname) {
                window.location.href = `/user/info/${nickname}`;
            }).fail(function () {
                alert("존재하지 않는 구단주입니다!");
            });
        }
    }
};

main.init();