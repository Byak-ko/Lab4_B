package org.app;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class User {
    Long id;
    String firstName;
    String secondName;
    String email;
}
