package org.springframework.samples.petclinic.recoveryroom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;
import org.springframework.samples.petclinic.pet.Visit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RecoveryRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotEmpty
    @Size(min = 3, max = 50)
    String name;

    @Min(value = 0)
    @PositiveOrZero
    double size;

    @NotNull
    boolean secure;

    @Transient
    RecoveryRoomType roomType;

    // @OneToMany(targetEntity = Visit.class)
    // private Visit visit;

    // @OneToMany()

    // @NotNull
    @ManyToOne(targetEntity = RecoveryRoomType.class, optional = false)
    private RecoveryRoomType recoveryRoomType;
}
