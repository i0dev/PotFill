package com.i0dev.plugin.potfill.object.config;

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