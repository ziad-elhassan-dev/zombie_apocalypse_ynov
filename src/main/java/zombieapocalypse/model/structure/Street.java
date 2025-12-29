package zombieapocalypse.model.structure;

/* Class representing a street */
public class Street {
    /** name of this Street */
    private String name;
    /** whether this Street is one of the mainroads */
    private boolean isMainroads;

    /**
     * Builds a Street
     * 
     * @param name        name of this Street
     * @param isMainroads if this Street is one of the mainroads
     */
    public Street(String name, boolean isMainroads) {
        this.name = name;
        this.isMainroads = isMainroads;
    }

    /**
     * Returns whether this Street is one of the mainroads
     * 
     * @return true if street is one of the mainroads, false otherwise
     */
    public boolean getIsMainroads() {
        return this.isMainroads;
    }

    /**
     * Sets the boolean value indicating whether this road is a main road or not.
     * 
     * @param isMainroads true if it is a main road, false otherwise
     */
    public void setIsMainroads(boolean isMainroads) {
        this.isMainroads = isMainroads;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param object the reference object with which to compare.
     * @return       true if this object is the same as the object argument, false otherwise.
     */
    public boolean equals(Object object) {
        if (object instanceof Street) {
            Street street = (Street) object;
            if (this.name == street.getName()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns this Street's name
     * 
     * @return Street's name
     */
    public String getName() {
        return this.name;
    }
}
