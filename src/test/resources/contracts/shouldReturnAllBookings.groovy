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
                        "travelInfoId": "075efa8e-d842-40b6-9edd-a8fc5bdc7bed",
                        "name": "Alex",
                        "email": "alex@mail.com",
                        "departure": "2023-06-21T00:00:00.000+00:00",
                        "from": "Budapest",
                        "arrival": "2023-06-22T00:00:00.000+00:00",
                        "to": "London",
                        "totalAmount": 510.00,
                        "dateOfPayment": "2023-06-04T14:10:12.683+00:00"
                ],
                [
                        "travelInfoId": "059efa8e-r789-40b6-9edd-a8fc5bdc8cat",
                        "name": "Brian",
                        "email": "brian@mail.com",
                        "departure": "2023-06-21T00:00:00.000+00:00",
                        "from": "Budapest",
                        "arrival": "2023-06-22T00:00:00.000+00:00",
                        "to": "London",
                        "totalAmount": 510.00,
                        "dateOfPayment": "2023-06-04T14:10:12.683+00:00"
                ]
        ])
    }
}