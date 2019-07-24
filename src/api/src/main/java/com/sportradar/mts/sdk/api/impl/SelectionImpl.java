/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.sportradar.mts.sdk.api.Selection;
import com.sportradar.mts.sdk.api.utils.StringUtils;

/**
 * Implementation of Selection interface
 */
public class SelectionImpl implements Selection {

    private final String eventId;
    private final String id;
    private final long odds;
    private final boolean isBanker;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SelectionImpl(@JsonProperty("eventId") String eventId,
                         @JsonProperty("id") String id,
                         @JsonProperty("odds") long odds,
                         @JsonProperty("isBanker") boolean isBanker)
    {
        Preconditions.checkArgument( !StringUtils.isNullOrEmpty(eventId), "eventId is missing");
        Preconditions.checkArgument(eventId.length() > 0, "eventId is missing");
        Preconditions.checkArgument(eventId.length() <= 100, "eventId is too long");
        Preconditions.checkArgument(id.length() > 0, "selection id is missing");
        Preconditions.checkArgument(id.length() <= 1000, "selection id is too long");
        if(odds != 0) {
            Preconditions.checkArgument(odds >= 10000, "selection odds too low");
            Preconditions.checkArgument(odds <= 1000000000, "odds too high");
        }

        this.eventId = eventId;
        this.id = id;
        this.odds = odds;
        this.isBanker = isBanker;
    }

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long getOdds() {
        return odds;
    }

    @Override
    public boolean getIsBanker() {
        return isBanker;
    }

    @Override
    public String toString()
    {
        return "SelectionImpl{" +
                "eventId=" + eventId +
                ", id='" + id + '\'' +
                ", odds=" + odds +
                ", isBanker=" + isBanker +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Selection)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Selection sel = (Selection)obj;
        return sel.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode()
    {
        return (getEventId() + "+" + getId() + "+" + getOdds() + "+" + getIsBanker()).hashCode();
    }
}
