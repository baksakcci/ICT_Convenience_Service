package com.example.sinabro.entity.warnning;

import com.example.sinabro.entity.member.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Warning {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String warning;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_id")
    private User user;

    public Warning(String warning, String message) {
        this.warning = warning;
        this.message = message;
    }

    public Warning editWarning(String warning, String message) {
        this.warning = warning;
        this.message = message;
        return this;
    }

}
