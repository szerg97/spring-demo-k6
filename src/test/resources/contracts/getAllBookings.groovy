package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description("it should return all bookings")

    request {
        url("/api/v1/booking")
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body([
                [
                        "travelInfoId": "travel-info-1",
                        "name": "Alex",
                        "email": "alex@mail.com",
                        "departure": [2023, 1, 1],
                        "from": "Budapest",
                        "arrival": [2023, 1, 1],
                        "to": "Paris",
                        "totalAmount": 102.000,
                        "dateOfPayment": 1672527600000
                ],
                [
                        "travelInfoId": "travel-info-2",
                        "name": "Brian",
                        "email": "brian@mail.com",
                        "departure": [2023, 1, 1],
                        "from": "Budapest",
                        "arrival": [2023, 1, 1],
                        "to": "Paris",
                        "totalAmount": 204.000,
                        "dateOfPayment": 1672527600000
                ],
                [
                        "travelInfoId": "travel-info-3",
                        "name": "Charles",
                        "email": "charles@mail.com",
                        "departure": [2023, 1, 1],
                        "from": "Budapest",
                        "arrival": [2023, 1, 1],
                        "to": "Paris",
                        "totalAmount": 408.000,
                        "dateOfPayment": 1672527600000
                ]
        ])
    }
}