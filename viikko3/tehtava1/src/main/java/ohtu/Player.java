
package ohtu;

import java.util.Date;

public class Player {
    private int goals;
    private int assists;
    private int penalties;
    private String name;
    private String team;
    private String nationality;
    private Date birthdate;

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getPoints() {
        return goals + assists;
    }

    public int getPenalties() {
        return penalties;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return name;
    }
      
}
