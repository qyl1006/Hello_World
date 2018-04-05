package cn.wolfcode.ajax.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Employee {
    private Long id;
    private String username;
    private String email;
    private Integer age;
    private List<String> hobby = new ArrayList<>();
}
