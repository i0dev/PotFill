package com.i0dev.plugins.object.config;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Location {
    double x, y, z;
    String worldName;
}