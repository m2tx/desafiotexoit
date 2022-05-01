# Desafio Texo It - Awards Interval
## API Endpoints
| METHOD | URI |
| ------ | ------------------ |
| GET | /movies |
| GET | /movies/{id} |
| POST | /movies |
| PUT | /movies/{id} |
| DELETE | /movies/{id} |
| GET | /moveis/awards/interval/ |

## Compile

```
	mvn package
```

## Execute


```
	java -jar ./target/desafiotexoit-0.0.1.jar --movielist=./src/main/resources/movielist.csv
	curl http://localhost:8080/movies/awards/interval/
```

## H2 Console

- URL = [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- DATABASE = jdbc:h2:mem:desafiotexoit
- USERNAME = sa
- PASSWORD = password
