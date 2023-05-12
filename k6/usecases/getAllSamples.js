import http from 'k6/http';
import { check, sleep } from 'k6';

export function getAllSamples() {
  const res = http.get('http://localhost:8080/api/v1/samples');
  check(res, {
      'is status 200': (r) => r.status === 200,
  }, {
      my_tag: "I'm a tag for all"
  });
  //console.log(res.body);
  sleep(1);
}