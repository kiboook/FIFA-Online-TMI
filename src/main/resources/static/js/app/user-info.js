let main = {
    init: function () {
        const _this = this;

        // 유저 최고 등급 검색 버튼 이벤트
        const maxDivisionBtn = document.querySelector("#btn-max-division");
        if (maxDivisionBtn) {
            maxDivisionBtn.addEventListener('click', _this.search_user_max_division);
        }

        // 유저 거래 기록 페이지 이동 이벤트
        const userRecordBtn = document.querySelector("#btn-user-trade-record");
        if (userRecordBtn) {
            userRecordBtn.addEventListener('click', _this.move_user_record_page);
        }

        // 유저 매치 기록 페이지 이동 이벤트
        const userMatchRecordBtn = document.querySelector("#btn-user-match-record");
        if (userMatchRecordBtn) {
            userMatchRecordBtn.addEventListener('click', _this.move_user_match_record_page);
        }
    },
    search_user_max_division: function () {
        const url = window.location.pathname.split('/');
        const nickname = decodeURIComponent(url[url.length - 1]);
        window.location.href = `/user/division/${nickname}`;
    },
    move_user_record_page: function () {
        const url = window.location.pathname.split('/');
        const nickname = decodeURIComponent(url[url.length - 1]);
        window.location.href = `/user/trade/record/${nickname}`;
    },
    move_user_match_record_page: function () {
        const url = window.location.pathname.split('/');
        const nickname = decodeURIComponent(url[url.length - 1]);
        window.location.href = `/user/match/record/${nickname}`;
    }
};

main.init();