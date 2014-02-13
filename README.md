### Using Spring Integration with MQTT

Simple [Spring Integration](http://projects.spring.io/spring-integration/) example with [MQTT](http://mqtt.org).

Currently based on the [Spring Integration guide](https://spring.io/guides/gs/integration/).

Extends the guide example by publishing received tweets on an MQTT topic (`tweets/spring-integration`) on the `iot.eclipse.org` public test broker, instead of writing to a file. The tweets are published with the retained flag set, QoS 0.

#### Build

```
./gradlew build
```

#### Run

To see results, subscribe to the relevant topic, e.g.

```
mosquitto_sub -h iot.eclipse.org -t "tweets/#" -v
```

The Spring Integration app requires that clientId and clientSecret (Twitter app values) are set in the environment, e.g.:

```
java -DclientId=YourClientId -DclientSecret=YourClientSecret -jar build/libs/mqtt-integration-0.1.0.jar
```
