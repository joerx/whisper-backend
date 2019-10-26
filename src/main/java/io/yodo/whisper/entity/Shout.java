package io.yodo.whisper.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "shout")
public class Shout {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "message")
    @NotNull
    @Length(min = 10)
    private String message;

    @Column(name = "username")
    @NotNull
    @Length(min = 1)
    private String username;

    @Column(name = "timestamp")
    @NotNull
    private Date timestamp;

    public Shout() {
        timestamp = new Date();
    }

    public Shout(String message, String username, Date timestamp) {
        this.message = message;
        this.username = username;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shout shout = (Shout) o;
        return id == shout.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Shout{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
