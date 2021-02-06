var main = {
    init: function () {
        const _this = this;

        // 유저 닉네임 검색 버튼 이벤트
        const searchBtn = document.querySelector("#btn-user-info");
        if (searchBtn) {
            searchBtn.addEventListener('click', _this.search_user_info);
        }

        // 유저 닉네임 검색 Enter 키 이벤트
        const searchEnter = document.querySelector("#input-nickname");
        if (searchEnter) {
            searchEnter.addEventListener('keyup', key => {
                if (key.keyCode === 13) {
                    _this.search_user_info();
                }
            })
        }
    },
    search_user_info: function () {
        const nickname = document.querySelector("#input-nickname").value;

        if (nickname === '') {
            alert("구단주를 입력하세요!");
        } else {
            $.ajax({
                type: 'GET',
                url: `/api/v1/user/${nickname}`
            }).done(nickname => {
                window.location.href = `/user/info/${nickname}`;
            }).fail(err => {
                console.log(err);
                alert("존재하지 않는 구단주입니다!");
            });
        }
    }
};

main.init();