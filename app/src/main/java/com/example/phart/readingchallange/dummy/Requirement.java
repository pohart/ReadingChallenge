package com.example.phart.readingchallange.dummy;

import android.support.annotation.NonNull;

import net.jcip.annotations.Immutable;

/**
 * Created by PHART on 1/26/2018.
 */

@Immutable
class Requirement implements Comparable<Requirement> {
    private final int requirementNumber;
    private final String requirementString;

    Requirement(final int requirementNumber, final String requirementString) {
        this.requirementNumber = requirementNumber;
        this.requirementString = requirementString;
    }

    @Override
    public int compareTo(@NonNull final Requirement o) {
        return requirementNumber - o.requirementNumber;
    }

    @org.jetbrains.annotations.Contract("_ -> !null")
    static Requirement parseRequirement(String str) {
        return new Requirement(
                Integer.parseInt(str.substring(0, str.indexOf('|'))),
                str.substring(str.indexOf('|') + 1).trim());
    }

    public String getRequirementString() {
        return requirementString;
    }
}
