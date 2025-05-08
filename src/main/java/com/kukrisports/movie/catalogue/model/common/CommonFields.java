package com.kukrisports.movie.catalogue.model.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Abstract base class that provides common audit fields to all entity classes.
 * <p>
 * Includes universally unique identifier (UUID) for entity ID, as well as
 * created and updated timestamps. By extending this class, entities automatically
 * inherit these properties, ensuring consistency across the data model.
 * </p>
 *
 * <p>This class is annotated with {@code @MappedSuperclass}, allowing its
 * fields to be mapped to database columns in subclasses.</p>
 *
 * <p>Note: {@code createdAt} and {@code updatedAt} are initialized at object creation
 * time and should be managed via service or JPA event listeners for real-time updates.</p>
 *
 * @author fazal.babaria
 */

@MappedSuperclass
public class CommonFields implements Serializable {
    private static final long serialVersionUID = -3049884834665292478L;

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
