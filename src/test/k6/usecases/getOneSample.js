import http from 'k6/http';
import { check, sleep, fail } from 'k6';
import {environment} from "../environment.js";

const url = environment.url;

export function getOneSample() {
    console.log(url);
    const res = http.get(url);
    if(!check(res, {
      'is status 200': (r) => r.status === 200,
    }, {
      my_tag: "I'm a tag for getting one sample by index"
    })){
      fail('Failed to get 200 response code on GET ONE');
    }
    console.log(res.body);
    sleep(1);
}