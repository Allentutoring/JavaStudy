package tutoring.Project.base.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedDate;


@MappedSuperclass
@Setter
@Getter
@ToString
@NoArgsConstructor
@Where(clause = "deleted_at is null")
public class BaseEntity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Timestamp createdAt;
    @LastModifiedDate
    @UpdateTimestamp
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    
}
