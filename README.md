# Bucket4j
* https://github.com/bucket4j/bucket4j
* https://howisitgo1ng.tistory.com/entry/Spring-Boot%EC%97%90-Redis%EC%99%80-%EC%97%B0%EB%8F%99%ED%95%98%EC%97%AC-%EC%B2%98%EB%A6%AC%EC%9C%A8-%EC%A0%9C%ED%95%9C-%EC%9E%A5%EC%B9%98Rate-Limiter-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0Spring-Boot-Redis-Bucket4j
* Redis + RedisInsight
```yaml
version: '3'
services:
  redis:
    image: redislabs/redismod
    ports:
      - 6379:6379
  redisinsight:
    image: redislabs/redisinsight:latest
    ports:
      - 8001:8001
    volumes:
      - ./data/redisinsight:/db
```
### 의견
* 토큰버킷알고리즘 구현체로 실제 운영에서도 사용할만한 기능으로 보임
* 레디스만 잘 설치하면 바로 사용할 수 있음
