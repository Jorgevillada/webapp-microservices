#Consul
##pull consul
```
docker pull consul
```
##run in docker
```
docker run -d --name=c1 -p 8500:8500 consul agent -dev -client=0.0.0.0 -bind=0.0.0.0
```

##Open Consul Dashboard
`http://localhost:8500`

##Configure properties
(Currently the projects use the configuration of consul)
in key/value add this property

```
key or folder = config/export-service/test
value = ANY_VALUE 
```
- config:base path
- export-service: Service name
- test: Property

in projects it is used like this
```
@Value("${test}")
String value;
```



If you want run Consul in another location, 
check bootstrap.yml in both projects(api-gateway, export-service) 
and configure the host and port

#Run Api-Gateway 
(run in port 9000, check health in consul dashboard)

check zuul configuration in application.yml

#Run export-service1 
(run in port 8000, check health in consul dashboard)

#Run export-service2
exec with vm option
```
-Dserver.port=8001
```
(run in port 8001, check health in consul dashboard)



#Test 
http://localhost:9000/export-service/hola-mundo

If both instances are running correctly, the port shown in each request must change(8000,8001)

#Hystrix

##Hystrix Stream
- Api-Gateway: http://localhost:9000/actuator/hystrix.stream
- export-service1: http://localhost:8000/actuator/hystrix.stream
- export-service2: http://localhost:8001/actuator/hystrix.stream

##Hystrix Dashboard
http://localhost:9000/hystrix

check with any hystrix stream

##Hystrix Command error

- export-service1: http://localhost:8000/fault
- export-service2: http://localhost:8001/fault

(check in hystrix dashboard with export-service1 or export-service2 stream)