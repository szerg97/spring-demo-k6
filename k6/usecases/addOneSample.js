import http from 'k6/http';
import { check, sleep, fail } from 'k6';

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
  const res = http.post('http://localhost:8080/api/v1/samples', JSON.stringify(data), params);
  if(!check(res, {
      'is status 200': (r) => r.status === 200,
  }, {
      my_tag: "I'm a tag for adding one sample"
  })){
    fail('Failed to get 200 response code')
  }
  console.log(res.body);
  sleep(1);
}