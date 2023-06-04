package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description("it should book travelling and return response")

    request {
        url("/api/v1/booking")
        method POST()
        body([
                "name": "Alex",
                "email": "alex@mail.com",
                "departure": "2023-06-21",
                "start": "Budapest",
                "arrival": "2023-06-22",
                "destination": "London",
                "account": "Account 1",
                "amount": 500.0
        ])
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body([
                "travelInfoId": "075efa8e-d842-40b6-9edd-a8fc5bdc7bed",
                "name": "Alex",
                "email": "alex@mail.com",
                "departure": "2023-06-21T00:00:00.000+00:00",
                "from": "Budapest",
                "arrival": "2023-06-22T00:00:00.000+00:00",
                "to": "London",
                "totalAmount": 510.000,
                "dateOfPayment": "2023-06-04T13:31:01.607+00:00"
        ])
    }
}