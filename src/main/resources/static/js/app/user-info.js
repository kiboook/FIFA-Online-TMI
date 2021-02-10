let main = {
    init: function () {
        const _this = this;

        // 유저 최고 등급 검색 버튼 이벤트
        const maxDivisionBtn = document.querySelector("#btn-max-division");
        if (maxDivisionBtn) {
            maxDivisionBtn.addEventListener('click', _this.search_user_max_division);
        }
    },
    search_user_max_division: function () {
        const url = window.location.pathname.split('/');
        const nickname = decodeURIComponent(url[url.length - 1]);

        window.location.href = `/user/division/${nickname}`;
    }
};

main.init();