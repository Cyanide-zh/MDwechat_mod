package com.blanke.mdwechat;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

// Version is a helper class for comparing version strings.
public class Version {
    private String versionName;

    private List<Integer> version = new ArrayList<>();

    public Version(String versionName) {
        this.versionName = versionName;
        String[] version = versionName.split("\\.");
        for (String s : version) {
            this.version.add(Integer.parseInt(s));
        }
    }

    public String toString() {
        return versionName;
    }

    public boolean equals(Version other) {
        if (other == null) return false;
        return this.versionName.equals(other.versionName);
    }

    public int compareTo(@NotNull Version other) {
        return compare(this, other);
    }

    private static int compare(@NotNull Version x, @NotNull Version y) {
        for (int index = 0; index < x.version.size() && index < y.version.size(); index++) {
            if (x.version.get(index) > y.version.get(index)) return 1;
            if (x.version.get(index) < y.version.get(index)) return -1;
        }
        return Integer.compare(x.version.size(), y.version.size());
    }

}
