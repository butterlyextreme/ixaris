package contracts.rankingsController

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description ""
    request{
        method POST()
        url("/match") {
        }
        headers {
            header 'Content-Type': 'application/json'
        }
        body(
                match_date: "2021-07-14T08:18:10.447Z",
                player_lose: "001",
                player_win: "002",
                score: "9-7"
        )
}
response {
    body("")
    status 201
}
}
