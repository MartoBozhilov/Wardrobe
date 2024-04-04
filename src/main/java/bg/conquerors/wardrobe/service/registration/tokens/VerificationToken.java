package bg.conquerors.wardrobe.service.registration.tokens;

import bg.conquerors.wardrobe.model.entity.BaseEntity;
import bg.conquerors.wardrobe.model.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken extends BaseEntity {

    private String token;
    private Date expirationTime;
    private static final int EXPIRATION_TIME =15;

    @OneToOne
    @JoinColumn(name = "user_id")
    private  User User;

    public VerificationToken(User user,String token){
        super();
        this.setUser(user);
        this.setToken(token);
        this.expirationTime = this.getTokenExpirationTime();

    }

    public VerificationToken(String token){
        super();
        this.setToken(token);
        this.expirationTime = this.getTokenExpirationTime();
    }

    private Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
