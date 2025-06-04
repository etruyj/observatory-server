//===================================================================
// WriteBlackPearlMessages.java
//      Description:
//          This class takes Spectra system messages and writes them
//          to the InfluxDB.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.util.influx;

import com.spectralogic.blackpearl.nacre.model.Message;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

public class WriteBlackPearlMessages {
    public static void fromList(String host, List<Message> messages, InfluxDB influx) throws Exception {
        String table_name = "system_messages";
        Point point;

        for(Message message : messages) {
            point = Point.measurement(table_name)
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("host", host)
                        .addField("id", Float.valueOf(message.getId()))
                        .addField("severity", message.getSeverity())
                        .addField("description_path", message.getDescriptionPath())
                        .addField("description_text", message.getDescriptionText())
                        .addField("details_path", message.getDetailsPath())
                        .addField("details_text", message.getDetailsText())
                        .addField("read", message.isRead())
                        .addField("resolved", message.isResolved())
                        .build();

            influx.write(point);
        }
    }
}
