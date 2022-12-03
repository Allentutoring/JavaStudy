package tutoring.javastudy.base.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedDate;


@Getter
@MappedSuperclass
@NoArgsConstructor
@Where(clause = "deleted_at is null")
public class BaseEntity implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @CreationTimestamp
    protected Timestamp createdAt;
    @LastModifiedDate
    @UpdateTimestamp
    protected Timestamp updatedAt;
    @Setter
    protected Timestamp deletedAt;
    
}
