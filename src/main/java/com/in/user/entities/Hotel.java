package com.in.user.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {

    private String id;
    private String name;
    private String location;
    private String about;
}
