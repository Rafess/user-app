package com.letscode.userapp.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private String address;
    private String email;

}
