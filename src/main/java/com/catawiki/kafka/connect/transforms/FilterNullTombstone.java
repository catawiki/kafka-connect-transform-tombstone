package com.catawiki.kafka.connect.transforms;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class FilterNullTombstone<R extends ConnectRecord<R>> implements Transformation<R> {
    private static final Logger log = LoggerFactory.getLogger(FilterNullTombstone.class);

    public ConnectRecord apply(ConnectRecord record) {
        if(record.valueSchema() == null ||
                Schema.Type.STRUCT != record.valueSchema().type() ||
                record.value() == null) {
            log.info("record valueSchema() or value is null");
            return null;
        }
        return record;
    }

    public ConfigDef config() {
        return new ConfigDef();
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}
