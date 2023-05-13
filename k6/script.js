import { group } from 'k6';
import {getAllSamples} from './usecases/getAllSamples.js';
import { getOneSample } from './usecases/getOneSample.js';
import { addOneSample } from './usecases/addOneSample.js';

// smoke test options: minimal load as a sanity check
export const options = {
    vus: 1,
    duration: '13s',
    thresholds: {
        http_req_duration: ['p(99)<400']
    }
}

// load test options: performance assessment
export const loadTest_options = {
    stages: [
        { duration: '20s', target: 100 },   // ramp-up of traffic from 1 to 100 users over 20 secs
        { duration: '2m', target: 1000 },   // stay at 1000 users for 2 min
        { duration: '5s', target: 5 },      // ramp-down to 5 users
    ],
    thresholds: {
        http_req_duration: ['p(99)<500']    // 99% of requests must complete under 0.5 sec
    }
}

// stress test options: system stability under extreme conditions
export const stressTest_options = {
    stages: [
        { duration: '2m', target: 100 },    // below normal load
        { duration: '5m', target: 100 },
        { duration: '2m', target: 200 },    // normal load
        { duration: '5m', target: 200 },
        { duration: '2m', target: 300 },    // around the breaking point
        { duration: '5m', target: 300 },
        { duration: '2m', target: 400 },    // beyond the breaking point
        { duration: '5m', target: 400 },
        { duration: '10m', target: 0 },     // ramp-down, recovery stage
    ]
}

// soak test options: reliability over a long period
export const soakTest_options = {
    stages: [
        { duration: '2m', target: 400 },    // ramp-up to 400 users
        { duration: '3h56m', target: 400 },    // stay at 400 users for ~4 hours
        { duration: '2m', target: 0 },      // scale down
    ]
}

export default function () {
    group('GET', function () {
        getAllSamples();
        getOneSample();
    });

    group('POST', function () {
        addOneSample();
    });
}