let main = {
    init: function () {
        const _this = this;

        // 유저 구매 기록 버튼 이벤트
        const buyRecordBtn = document.querySelector("#btn-buy-record");
        if (buyRecordBtn) {
            buyRecordBtn.addEventListener('click', _this.request_buy_record);
        }

        // 유저 판매 기록 버튼 이벤트
        const sellRecordBtn = document.querySelector("#btn-sell-record");
        if (sellRecordBtn) {
            sellRecordBtn.addEventListener('click', _this.request_sell_record)
        }
    },
    request_buy_record: function () {
        const url = window.location.pathname.split('/');
        const nickname = decodeURIComponent(url[url.length - 1]);
        window.location.href = `/user/trade/record/buy/${nickname}`;
    },
    request_sell_record: function () {
        const url = window.location.pathname.split('/');
        const nickname = decodeURIComponent(url[url.length - 1]);
        window.location.href = `/user/trade/record/sell/${nickname}`;
    }
};

main.init();