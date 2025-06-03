//===================================================================
// ObServerShell.java
//      Description:
//          This class is the entry point for the shell script of the
//           tool.
//
// Created by Sean Snyder
//===================================================================

package com.spectralogic.observatory2.server.ui;

import com.socialvagrancy.utils.http.Server;
import com.socialvagrancy.utils.http.ServerBuilder;
import com.socialvagrancy.utils.io.Configuration;
import com.socialvagrancy.utils.ui.ArgParser;
import com.spectralogic.observatory2.server.command.ObServerController;
import com.spectralogic.observatory2.server.model.ServerConfig;

import java.util.ArrayList; //temporary for initial builds until config file is built
import java.util.List;

public class ObServerShell {
    public static void main(String[] args) {
        try {
            Configuration.load("../observatory.yml", ServerConfig.class);
            ArgParser aparser = new ArgParser();
            aparser = loadConfiguration(aparser);
            aparser.parse(args);

            ObServerController conn = new ObServerController(
                                                aparser.getRequired("influx-url"),
                                                aparser.getRequired("influx-db"),
                                                aparser.getRequired("influx-user"),
                                                aparser.getRequired("influx-pass"),
                                                aparser.getRequired("sqlite-path")
                    );

            processCommand(aparser, conn);
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void processCommand(ArgParser aparser, ObServerController conn) throws Exception {
        List output = null;
        String outputFormat = "table";

        try {
            if(aparser.getBoolean("help")) {
                //Display.print("../lib/options/help.txt");
            } else if(aparser.getBoolean("version")) {
                //Display.print("../lib/options/version.txt");
            } else {
                switch(aparser.getRequired("command")) {
                    case "create-api-key":
                        conn.createToken(aparser.getRequired("key"),
                                        aparser.getRequired("site"));
                        break;
                    case "start-server":
                        startServer(aparser.getRequired("ip-address"),
                                    aparser.getRequired("port"),
                                    aparser.getRequired("api-prefix"),
                                    conn,
                                    new ArrayList());
                        break;
                    default:
                        System.err.println("Invalid command [" + aparser.getRequired("command") + "]. Use --help to list available options.");
                        break;
                }

                if(output != null) {
                    //Display.output(output, aparser.get("output-format") != null ? aparser.get("output-format") : outputFormat);
                }   
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArgParser loadConfiguration(ArgParser aparser) {
        ServerConfig config = Configuration.get();

        String[] args = {
            "--influx-url", config.getInfluxdb().getUrl(),
            "--influx-db", config.getInfluxdb().getDatabaseName(),
            "--influx-user", config.getInfluxdb().getUsername(),
            "--influx-pass", config.getInfluxdb().getPassword(),
            "--sqlite-path", config.getSqlite().getDatabase()
        };

        aparser.parse(args);

        return aparser;
    }

    public static void startServer(String ip_address, String port, String api_prefix, ObServerController conn, List<String> allowed_origins) throws Exception {
        Server server = new ServerBuilder()
                                .uri(ip_address, Integer.valueOf(port), api_prefix)
                                .setPackage("com.spectralogic.observatory2.server.api")
                                .addProperty("controller", conn)
                                .addProperty("allowed_origins", allowed_origins)
                                .addHttpFilter("cors")
                                .setConnectionHttp()
                                .build();
    }

}

