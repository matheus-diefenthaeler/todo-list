package br.com.diefenthaeler.todolistapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    private String description;
    private Boolean completed;
}
