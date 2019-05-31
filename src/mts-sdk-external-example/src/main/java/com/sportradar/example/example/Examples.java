/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.example.example;

import com.sportradar.example.listeners.TicketAckHandler;
import com.sportradar.example.listeners.TicketCancelAckHandler;
import com.sportradar.example.listeners.TicketCancelResponseHandler;
import com.sportradar.example.listeners.TicketResponseHandler;
import com.sportradar.example.tickets.TicketExamples;
import com.sportradar.mts.sdk.api.interfaces.MtsSdkApi;
import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;
import com.sportradar.mts.sdk.api.interfaces.TicketAckSender;
import com.sportradar.mts.sdk.api.interfaces.TicketCancelAckSender;
import com.sportradar.mts.sdk.api.interfaces.TicketCancelSender;
import com.sportradar.mts.sdk.api.interfaces.TicketSender;
import com.sportradar.mts.sdk.app.MtsSdk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MTS integration examples
 */
public class Examples {
    private static final Logger logger = LoggerFactory.getLogger(Examples.class);

    public static void Run()
    {
        SdkConfiguration config = MtsSdk.getConfiguration();
        MtsSdkApi mtsSdk = new MtsSdk(config);
        mtsSdk.open();
        TicketAckSender ticketAckSender = mtsSdk.getTicketAckSender(new TicketAckHandler());
        TicketCancelAckSender ticketCancelAckSender = mtsSdk.getTicketCancelAckSender(new TicketCancelAckHandler());
        TicketCancelSender ticketCancelSender = mtsSdk.getTicketCancelSender(new TicketCancelResponseHandler(ticketCancelAckSender, mtsSdk.getBuilderFactory()));
        TicketSender ticketSender = mtsSdk.getTicketSender(new TicketResponseHandler(ticketCancelSender, ticketAckSender, mtsSdk.getBuilderFactory()));

        TicketExamples ticketExamples = new TicketExamples(mtsSdk.getBuilderFactory());
        logger.info("Example 1");
        ticketSender.send(ticketExamples.Example1());
        logger.info("Example 2");
        ticketSender.send(ticketExamples.Example2());
        logger.info("Example 3");
        ticketSender.send(ticketExamples.Example3());
        logger.info("Example 4");
        ticketSender.send(ticketExamples.Example4());
        logger.info("Example 5");
        ticketSender.send(ticketExamples.Example5());
        logger.info("Example 6");
        ticketSender.send(ticketExamples.Example6());
        logger.info("Example 7");
        ticketSender.send(ticketExamples.Example7());
        logger.info("Example 8");
        ticketSender.send(ticketExamples.Example8());
        logger.info("Example 9");
        ticketSender.send(ticketExamples.Example9());
        logger.info("Example 10");
        ticketSender.send(ticketExamples.Example10());
        logger.info("Example 11");
        ticketSender.send(ticketExamples.Example11());
        logger.info("Example 12");
        ticketSender.send(ticketExamples.Example12());
        logger.info("Example 13");
        ticketSender.send(ticketExamples.Example13());
        logger.info("Example 14");
        ticketSender.send(ticketExamples.Example14());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.info("interrupted while sleeping");
        }
        mtsSdk.close();
    }
}
