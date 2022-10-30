package tutoring.Project.member;

import lombok.Getter;
import lombok.Setter;
import tutoring.Project.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int idx;

    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String password;
}
