package jpabasic.toyvaserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;

    private String userId;
    private String userPw;
    private String nickname;
    private String email;

    private LocalDateTime createdAt;
    private String status;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = "active";
    }

    protected User(){}

    private User(Builder builder) {
        this.userId = builder.userId;
        this.userPw = builder.userPw;
        this.nickname = builder.nickname;
        this.email = builder.email;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private String userPw;
        private String nickname;
        private String email;
        private LocalDateTime createdAt;
        private String status;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder userPw(String userPw) {
            this.userPw = userPw;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
