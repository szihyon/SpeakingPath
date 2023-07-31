package com.twinlions.spkpath.consultant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter // Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "consultant_tag_tb") // consultant_info_tb와 매칭
@DynamicInsert // Null 인 것은 자동으로 제외하고 넣어줌
public class ConsultantTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Consultant cslt;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Override
    public String toString() {
        return "ConsultantTag{" +
                "Id=" + Id +
                ", cslt=" + cslt +
                ", tag=" + tag +
                '}';
    }

}
