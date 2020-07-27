package com.project.modules.sys.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TestEntity {

    private Long id;
    private Long As;
    private String name;
}
