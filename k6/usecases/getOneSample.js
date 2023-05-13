import http from 'k6/http';
import { check, sleep, fail } from 'k6';

export function getOneSample() {
  const res = http.get('http://localhost:8080/api/v1/samples/index/1');
  if(!check(res, {
      'is status 200': (r) => r.status === 200,
  }, {
      my_tag: "I'm a tag for getting one sample by index"
  })){
      fail('Failed to get 200 response code')
  }
  console.log(res.body);
  sleep(1);
}