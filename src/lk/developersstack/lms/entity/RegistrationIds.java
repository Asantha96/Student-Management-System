package lk.developersstack.lms.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
    public class RegistrationIds implements Serializable {//serializable interface is a marker interface, it has no any implementation. why it is implementing thus it has no implementation. Because when transfering an object from one place to another destination, this interface should be implemented. It is a rule.
    private long studentId;
    private long programId;
}
