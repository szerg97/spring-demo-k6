import { group } from 'k6';
import {getAllSamples} from './usecases/getAllSamples.js';
import { getOneSample } from './usecases/getOneSample.js';
import { addOneSample } from './usecases/addOneSample.js';

export const options = {
    scenarios: {
        shared_iter_scenario: {
            executor: "shared-iterations",
            vus: 10,
            iterations: 50,
            startTime: "0s",
            gracefulStop: '5s',
            maxDuration: '10s',
        },
        per_vu_scenario: {
            executor: "per-vu-iterations",
            vus: 10,
            iterations: 10,
            startTime: "10s",
            gracefulStop: '5s',
            maxDuration: '10s',
        },
    },
  };

export default function () {
    group('GET', function () {
        getAllSamples();
        getOneSample();
    });

    group('POST', function () {
        addOneSample();
    });
}