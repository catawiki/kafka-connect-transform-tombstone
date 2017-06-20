# kafka-connect-transform-tombstone
Kafka Connect Single Message Transform to filter out null tombstone messages

## Package
1. `mvn package`
2. This will generate a jar file `kafka-connect-transform-tombstone-0.1.0.jar` in `target` folder.

## Usage
After adding jar file to your `CLASSPATH`, just add these 2 lines to your Kafka connect adapter properties file:

  - `transforms=FilterNull # you can use any alias here`
  - `transforms.FilterNull.type=com.catawiki.kafka.connect.transforms.FilterNullTombstone # remember to use same alias name here`

## NOTE
1. Using this SMT in a sink connector may conflict with the offset maintenence related logic. Since we return a `null` `ConnectorRecord`, Kafka connect will not hand null `SinkRecord` to the sink connector. As a result, the sink connector will see an offset jump when next valid message arrives.
2. In our case, we use this SMT in a source connector ([Debezium](http://debezium.io/)). So that we dont have any null tombstone messages in Kafka and, as a result, the sink connectors dont have to deal with such messages.
3. Here is a list of transforms shipped with Kafka Connect distribution: [Transformations](https://kafka.apache.org/documentation/#connect_transforms)
 
