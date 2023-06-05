package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("it should return all samples")

    request {
        url("/api/v1/samples")
        method GET()
    }

    response {
        status 200
        headers {
            contentType applicationJson()
        }
        body([
                    [
                            "id": "sample-1",
                            "value": 15000,
                            "currency": "HUF"
                    ], [
                            "id":"sample-2",
                            "value": 20000,
                            "currency": "EUR"
                    ], [
                            "id":"sample-3",
                            "value": 6000,
                            "currency": "EUR"
                    ]
        ])
    }
}