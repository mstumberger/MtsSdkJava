/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.impl.builders;

import com.google.common.collect.Lists;
import com.sportradar.mts.sdk.api.Bet;
import com.sportradar.mts.sdk.api.Sender;
import com.sportradar.mts.sdk.api.Ticket;
import com.sportradar.mts.sdk.api.builders.TicketBuilder;
import com.sportradar.mts.sdk.api.enums.OddsChangeType;
import com.sportradar.mts.sdk.api.impl.TicketImpl;
import com.sportradar.mts.sdk.api.utils.MtsTicketHelper;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import org.apache.commons.lang.NullArgumentException;

import java.util.Date;
import java.util.List;

/**
 * Implementation of the TicketBuilder
 */
public class TicketBuilderImpl implements TicketBuilder {
    private String ticketId;
    private List<Bet> bets;
    private String reofferId;
    private String altStakeRefId;
    private boolean isTest;
    private Sender sender;
    private OddsChangeType oddsChangeType;
    private Integer totalCombinations;
    private Date lastMatchEndTime;

    @Override
    public TicketBuilder setTicketId(String ticketId) {
        if (!MtsTicketHelper.validateTicketId(ticketId))
        {
            throw new IllegalArgumentException("TicketId not valid");
        }
        this.ticketId = ticketId;
        return this;
    }

    @Override
    public List<Bet> getBets() {
        return bets;
    }

    @Override
    public TicketBuilder addBet(Bet bet) {
        if (bet == null)
        {
            throw new IllegalArgumentException("Bet not valid");
        }
        if(bets == null)
        {
            bets = Lists.newArrayList();
        }
        bets.add(bet);
        return this;
    }

    @Override
    public TicketBuilder setReofferId(String reofferId) {
        if (!MtsTicketHelper.validateTicketId(reofferId))
        {
            throw new IllegalArgumentException("ReofferId not valid");
        }
        this.reofferId = reofferId;
        return this;
    }

    @Override
    public TicketBuilder setAltStakeRefId(String altStakeRefId) {
        if (!MtsTicketHelper.validateTicketId(altStakeRefId))
        {
            throw new IllegalArgumentException("AltStakeRefId not valid");
        }
        this.altStakeRefId = altStakeRefId;
        return this;
    }

    @Override
    public TicketBuilder setTestSource(boolean isTest) {
        this.isTest = isTest;
        return this;
    }

    @Override
    public TicketBuilder setSender(Sender sender) {
        if (sender == null)
        {
            throw new IllegalArgumentException("Sender cannot be null");
        }
        this.sender = sender;
        return this;
    }

    @Override
    public TicketBuilder setOddsChange(OddsChangeType oddsChangeType) {
        this.oddsChangeType = oddsChangeType;
        return this;
    }

    @Override
    public TicketBuilder setTotalCombinations(Integer totalCombinations) {
        if(totalCombinations == null || totalCombinations < 1) {
            throw new IllegalArgumentException("TotalCombinations value is not valid");
        }
        this.totalCombinations = totalCombinations;
        return this;
    }

    @Override
    public TicketBuilder setLastMatchEndTime(Date lastMatchEndTime) {
        if (lastMatchEndTime == null) {
            throw new NullArgumentException("lastMatchEndTime");
        }
        if (lastMatchEndTime.before(new Date())) {
            throw new IllegalArgumentException("LastMatchEndTime can not be in the past.");
        }
        this.lastMatchEndTime = lastMatchEndTime;
        return this;
    }


    @Override
    public Ticket build() {
        return new TicketImpl(ticketId, bets, sender, reofferId, altStakeRefId, isTest, oddsChangeType, totalCombinations, lastMatchEndTime, new Date(), SdkInfo.mtsTicketVersion());
    }
}
