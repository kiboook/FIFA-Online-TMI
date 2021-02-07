var main = {
    init: function () {
        const url = window.location.pathname.split('/');
        const nickname = decodeURIComponent(url[url.length - 1]);

        fetch(`/api/v1/user/division/${nickname}`)
            .then(res => {
                return res.text();
            })
            .then(accessId => {
                fetch(`https://api.nexon.co.kr/fifaonline4/v1.0/users/${accessId}/maxdivision`, {
                    headers: {
                        Authorization: "API_KEY"
                    }
                })
                    .then(res => {
                        return res.json();
                    })
                    .then(json => {
                        const firstMatchType = json[0].matchType;
                        const firstDivision = json[0].division;
                        const firstDivisionDate = json[0].achievementDate.split('T')[0];
                        document.querySelector("#first-user-division-match-type").innerText =
                            `매치 종류 : ${firstMatchType}`;
                        document.querySelector("#first-user-division").innerText =
                            `최고 등급 : ${firstDivision}`;
                        document.querySelector("#first-user-division-date").innerText =
                            `달성 날짜 : ${firstDivisionDate}`;

                        const secondMatchType = json[1].matchType;
                        const secondDivision = json[1].division;
                        const secondDivisionDate = json[1].achievementDate.split('T')[0];
                        document.querySelector("#second-user-division-match-type").innerText =
                            `매치 종류 : ${secondMatchType}`;
                        document.querySelector("#second-user-division").innerText =
                            `최고 등급 : ${secondDivision}`;
                        document.querySelector("#second-user-division-date").innerText =
                            `달성 날짜 : ${secondDivisionDate}`;
                    })
            })
    }
}

main.init();

