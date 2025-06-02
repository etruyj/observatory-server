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
import com.socialvagrancy.utils.ui.ArgParser;
import com.spectralogic.observatory2.server.command.ObServerController;

import java.util.ArrayList; //temporary for initial builds until config file is built
import java.util.List;

public class ObServerShell {
    public static void main(String[] args) {
        try {
            ArgParser aparser = new ArgParser();
            aparser.parse(args);

            ObServerController conn = new ObServerController();

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

