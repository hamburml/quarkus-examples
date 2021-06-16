# quarkus with profiles

- start with mvn quarkus:dev
  - check localhost:8080/hello
  
- mvn package; start with `java -Dsmallrye.config.profile=env1 -jar quarkus-profiles-1.0.0-SNAPSHOT-runner.jar`
  - check localhost:8080/hello
  - importantEnv is important-env1
  - anotherKey is onlyInApplicationProperties
  
- mvn package; start with `java -Dsmallrye.config.profile=env2 -jar quarkus-profiles-1.0.0-SNAPSHOT-runner.jar`
  - check localhost:8080/hello
  - importantEnv is important-env2
  - anotherKey is overwrittenInApplicationEnv2
  
- mvn package; start with `java -Dsmallrye.config.profile=env-faulty -jar quarkus-profiles-1.0.0-SNAPSHOT-runner.jar`
  - crashes, because importantEnv is missing
