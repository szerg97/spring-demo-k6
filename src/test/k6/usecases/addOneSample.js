import http from 'k6/http';
import { check, sleep, fail } from 'k6';
import {environment} from "../environment.js";

const url = environment.url;

export function addOneSample() {
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
  const res = http.post(url, JSON.stringify(data), params);
  if(!check(res, {
      'is status 200': (r) => r.status === 200,
  }, {
      my_tag: "I'm a tag for adding one sample"
  })){
    fail('Failed to get 200 response code on POST')
  }
  console.log(res.body);
  sleep(1);
}