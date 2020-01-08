package game.core.chooseFate;

import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "history")
@Entity
public class History {

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "litigation_id", nullable = false)
    private Litigation litigation;
    @Column(length = 1000)
    private String posSequence;
    @Column(length = 1000)
    private String negSequence;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 6, nullable = false)
    private int id;

    public History(@NonNull Litigation litigation, @NonNull String posSequence, @NonNull String negSequence) {
        this.litigation = litigation;
        this.posSequence = posSequence;
        this.negSequence = negSequence;
    }

    public History() {
    }

    public Litigation getLitigation() {
        return litigation;
    }

    public String getPosSequence() {
        return posSequence;
    }

    public String getNegSequence() {
        return negSequence;
    }
}

