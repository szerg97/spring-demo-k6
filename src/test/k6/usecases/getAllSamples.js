import http from 'k6/http';
import { check, sleep } from 'k6';
import {environment} from "../environment.js";

const url = environment.url;

export function getAllSamples() {
    console.log(url);
    const res = http.get(url);
    check(res, {
      'is status 200': (r) => r.status === 200,
    }, {
      my_tag: "I'm a tag for getting all samples"
    });
    sleep(1);
}