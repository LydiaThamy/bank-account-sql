package sg.edu.nus.iss.day24_paflecture.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    
    private int statusCode;
    private Date timeStamp;
    private String message;
    private String description;
    
}
