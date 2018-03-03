package org.hedgebits.hashcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class Car {
    private final int id;
    private Point pos;
}
