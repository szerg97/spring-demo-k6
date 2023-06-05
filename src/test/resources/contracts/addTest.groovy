package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("it should add a test and then return all tests")

    request {
        url("/api/v1/tests")
        method POST()
        headers {
            contentType applicationJson()
        }
        body(["name": "test-4", "value": 62])
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
                ],
                [
                        "name": "test-4",
                        "value": 62
                ]
        ])
    }
}