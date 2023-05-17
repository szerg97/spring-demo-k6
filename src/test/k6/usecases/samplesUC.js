import http from 'k6/http';
import { check, sleep, fail } from 'k6';
import { describe } from 'https://jslib.k6.io/expect/0.0.4/index.js';
import {environment} from "../environment.js";

const url = environment.url;

export function getAllSamples() {
    describe('Getting all samples', function () {
        console.log(url);
        const res = http.get(url, createGetAllParams());
        check(res, {
            'is status 200': (r) => r.status === 200,
        });
        sleep(1);
    });
    describe('Getting one sample by index', function () {
        console.log(url);
        const res = http.get(url + '/index/1', createGetOneParams());
        if(!check(res, {
            'is status 200': (r) => r.status === 200,
        })){
            fail('Failed to get 200 response code on GET ONE');
        }
        console.log(res.body);
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
        console.log(url);
        const res = http.post(url, JSON.stringify(data), extendPostParams(params));
        if(!check(res, {
            'is status 200': (r) => r.status === 200,
        })){
            fail('Failed to get 200 response code on POST')
        }
        console.log(res.body);
        sleep(1);
    });
}

export function createGetAllParams(){
    return {
        tags: {
            desc: 'Desc for getting all samples'
        }
    };
}

export function createGetOneParams(){
    return {
        tags: {
            desc: 'Desc for getting one samples'
        }
    };
}

export function extendPostParams(params){
    const t = params;
    t.tags = {
        additionalInfo: 'Add info here'
    }
    return t;
}