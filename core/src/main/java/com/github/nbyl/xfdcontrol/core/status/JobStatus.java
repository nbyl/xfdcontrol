package com.github.nbyl.xfdcontrol.core.status;

import com.google.common.base.Objects;

public class JobStatus {

    private String name;

    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, color);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final JobStatus other = (JobStatus) obj;
        return Objects.equal(this.name, other.name) && Objects.equal(this.color, other.color);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("color", color)
                .add("name", name)
                .toString();
    }
}
