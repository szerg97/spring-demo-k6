package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description("it should return booking by travelInfoId")

    request {
        url("/api/v1/booking/travel-info-1")
        method GET()
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