package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description("it should return booking by travelInfoId")

    request {
        url("/api/v1/booking/075efa8e-d842-40b6-9edd-a8fc5bdc7bed")
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body([
                "travelInfoId": "075efa8e-d842-40b6-9edd-a8fc5bdc7bed",
                "name": "Brian",
                "email": "brian@mail.com",
                "departure": "2023-06-21T00:00:00.000+00:00",
                "from": "Budapest",
                "arrival": "2023-06-22T00:00:00.000+00:00",
                "to": "London",
                "totalAmount": 510.00,
                "dateOfPayment": "2023-06-04T14:10:12.683+00:00"
        ])
    }
}