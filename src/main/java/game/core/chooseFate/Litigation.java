package game.core.chooseFate;

import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "litigation")
@Entity
public class Litigation {
    @Column(length = 1000)
    private String personDescription;
    @Column(length = 1000)
    private String faultDesc;
    @Column(length = 1000)
    private String descPosChoice;
    @Column(length = 1000)
    private String descNegChoice;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private int id;

    public Litigation(@NonNull String personDescription, @NonNull String faultDesc,
                      @NonNull String descPosChoice, @NonNull String descNegChoice) {

        this.personDescription = personDescription;
        this.faultDesc = faultDesc;
        this.descPosChoice = descPosChoice;
        this.descNegChoice = descNegChoice;

    }

    public Litigation() {
    }

    public String getPersonDescription() {
        return personDescription;
    }

    public String getFaultDesc() {
        return faultDesc;
    }

    public String getDescPosChoice() {
        return descPosChoice;
    }

    public String getDescNegChoice() {
        return descNegChoice;
    }
}
