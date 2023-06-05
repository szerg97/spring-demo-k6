package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description("it should book travelling and return response")

    request {
        url("/api/v1/booking")
        headers {
            contentType applicationJson()
        }
        method POST()
        body([
                "name": "Alex",
                "email": "alex@mail.com",
                "departure": [2023, 1, 1],
                "start": "Budapest",
                "arrival": [2023, 1, 1],
                "destination": "Paris",
                "account": "Account 1",
                "amount": 100.000
        ])
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body([
                "travelInfoId": "travel-info-1",
                "name": "Alex",
                "email": "alex@mail.com",
                "departure": [2023, 1, 1],
                "from": "Budapest",
                "arrival": [2023, 1, 1],
                "to": "Paris",
                "totalAmount": 102.000,
                "dateOfPayment": 1672527600000
        ])
    }
}