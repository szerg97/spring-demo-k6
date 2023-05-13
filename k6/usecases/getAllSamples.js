import http from 'k6/http';
import { check, sleep, fail } from 'k6';

export function getAllSamples() {
  const res = http.get('http://localhost:8080/api/v1/samples');
  check(res, {
      'is status 200': (r) => r.status === 200,
  }, {
      my_tag: "I'm a tag for getting all samples"
  });
  sleep(1);
}