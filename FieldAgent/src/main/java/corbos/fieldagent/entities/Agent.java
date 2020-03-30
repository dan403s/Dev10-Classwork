package corbos.fieldagent.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Agent {

    @Id
    @NotBlank(message = "Identifier must not be empty")
    private String identifier;

//    @Column(nullable = false)
    @NotBlank(message = "First name must not be empty.")
    @Size(max = 25, message = "First name must be less than or equal 25 characters.")
    private String firstName;

//    @Column
    @Size(max = 25, message = "Middle name must be less than or equal 25 characters.")
    private String middleName;

//    @Column(nullable = false)
    @NotBlank(message = "Last name must not be empty.")
    @Size(max = 25, message = "Last name must be less than or equal 25 characters.")
    private String lastName;

//    @Column
    @Size(max = 255, message = "Picture URL must be less than or equal 255 characters.")
    private String pictureUrl;

//    @Column(nullable = false)
    @NotNull(message = "BirthDate must not be empty.")
    @Past(message = "BirthDate must be in the past.")
    private LocalDate birthDate;

//    @Column(nullable = false)
    @NotNull(message = "Height must not be empty.")
    @Min(value = 36, message = "Height must be between 36 and 96 inches.")
    @Max(value = 96, message = "Height must be between 36 and 96 inches.")
    private int height;

//    @Column(nullable = false)
    @NotNull(message = "Activation date must not be empty.")
    private LocalDate activationDate;

//    @Column(nullable = false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    @NotNull(message = "Agency must not be empty.")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "security_clearance_id")
    @NotNull(message = "Security clearance must not be empty.")
    private SecurityClearance securityClearance;

}
