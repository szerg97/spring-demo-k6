package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description("it should return all samples")

    request {
        url("/api/v1/samples")
        method GET()
    }

    response {
        status 200
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            contentType: applicationJson()
        }
        body([
                    [
                        "id": "ABCD1234",
                        "value": 10
                    ], [
                        "id":"BCDE2345",
                        "value": 20
                    ], [
                        "id":"CDEF3456",
                        "value": 30
                    ]
        ])
    }
}