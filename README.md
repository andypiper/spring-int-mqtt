### Using Spring Integration with MQTT

Simple [Spring Integration](http://projects.spring.io/spring-integration/) example with [MQTT](http://mqtt.org).

Based on the [Spring Integration guide](https://spring.io/guides/gs/integration/). Uses Spring Boot to create a standalone executable jar.

Extends the guide example by:
* (outbound MQTT) publishing received tweets on an MQTT topic (`tweets/spring-integration`) on the `iot.eclipse.org` public test broker, instead of writing to a file. The tweets are published with the retained flag set, QoS 0.
* (inbound MQTT) subscribing to an MQTT topic (`inbound`) on the `iot.eclipse.org` public test broker, and writing messages from there to the file `./si/MQTT_INPUT`.

#### Build

```
./gradlew build
```

#### Run

The Spring Integration app requires that clientId and clientSecret (Twitter app values) are set in the environment, e.g.:

```
java -DclientId=YourClientId -DclientSecret=YourClientSecret -jar build/libs/mqtt-integration-0.2.0.jar
```

*outbound MQTT*

To see results, subscribe to the relevant topic, e.g.

```
mosquitto_sub -h iot.eclipse.org -t "tweets/#" -v
```

*inbound MQTT*

To see results, publish to any part of the relevant topic tree, e.g.

```
mosquitto_pub -h iot.eclipse.org -t "inbound/messages" -m "This is a message"
```

Then tail the file `./si/MQTT_INPUT` - each entry will be prepended with a timestamp and the topic used.
