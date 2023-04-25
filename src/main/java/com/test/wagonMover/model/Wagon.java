package com.test.wagonMover.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wagon", schema = "wgn_mover")
public class Wagon {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Индекс вагона на пути
    private Long index;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line line;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @Column(name = "weight", nullable = false)
    private Double cargoWeight;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "wagon_wp_id", schema = "wgn_mover",
            joinColumns = {@JoinColumn(name = "wagon_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "wagon_passport_id", referencedColumnName = "id")})
    @JoinColumn(name = "wagon_passport_id")
    private WagonPassport wagonPassport;

}
