import http from 'k6/http';
import { Counter, Gauge, Rate, Trend } from 'k6/metrics';
import { check, sleep, fail } from 'k6';
import { describe } from 'https://jslib.k6.io/expect/0.0.4/index.js';
import {environment} from "../environment.js";

const url = environment.url;

const customTrend = new Trend('custom_duration');
const customCounter = new Counter('custom_counter');
const customGauge = new Gauge('custom_gauge');
const customRate = new Rate('custom_rate');

export function handleSamples() {
    const countA = 1;
    const countB = 2;
    const countC = 3;
    describe('Getting all samples', function () {
        console.log('GET: ' + url);
        const res = http.get(url, {info: 'Desc for getting all samples'});
        check(res, {
            'is status 200': (r) => r.status === 200,
        });

        //Trend
        console.log('Response time (ms) was ' + String(res.timings.duration));
        customTrend.add(res.timings.duration, { desc: 'Request timings duration' });

        //Counter (sum of all values * each iteration (in case of 5 iterations, (1+2+3)*5))
        customCounter.add(countA, {isAEven: isEven(countA)});
        customCounter.add(countB, {isBEven: isEven(countB)});
        customCounter.add(countC, {isCEven: isEven(countC)});

        //Rate 50% pass, 50% fail
        customRate.add(1);
        customRate.add(true);
        customRate.add(false);
        customRate.add(0);

        //Gauge displays the last value, along with min and max
        customGauge.add(1);
        customGauge.add(5);
        customGauge.add(10);

        sleep(1);
    });
    describe('Getting one sample by index', function () {
        console.log('GET: ' + url + '/index/1');
        const res = http.get(url + '/index/1', {info: 'Desc for getting one sample'});
        if(!check(res, {
            'is status 200': (r) => r.status === 200,
        })){
            fail('Failed to get 200 response code on GET ONE, instead status code is: ' + res.status);
        }
        console.log(res.body);

        //Trend
        console.log('Response time (ms) was ' + String(res.timings.duration));
        customTrend.add(res.timings.duration, { desc: 'Request timings duration' });

        sleep(1);
    });
    describe('Adding one sample', function () {
        const data = {
            id: null,
            value: 15
        };
        const params = {
            headers: {
                "Content-Type": "application/json"
            }
        }
        console.log('POST: ' + url);
        const res = http.post(url, JSON.stringify(data), tag(params, {info: 'Additional info here'}));
        if(!check(res, {
            'is status 200': (r) => r.status === 200,
        })){
            fail('Failed to get 200 response code on POST, instead status code is:' + res.status);
        }
        console.log(res.body);

        //Trend
        console.log('Response time (ms) was ' + String(res.timings.duration));
        customTrend.add(res.timings.duration, { desc: 'Request timings duration', status: String(res.status), url: res.url, redirected: String(res.redirected)});

        sleep(1);
    });
}

export function tag(params, tags){
    params.tags = tags;
    return params;
}

function isEven(count) {
    return count % 2 === 0;
}