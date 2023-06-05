package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("it should return all tests")

    request {
        url("/api/v1/tests")
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body([
                [
                        "name": "test-1",
                        "value": 500
                ],
                [
                        "name": "test-2",
                        "value": 250
                ],
                [
                        "name": "test-3",
                        "value": 125
                ]
        ])
    }
}