package org.fall.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefreshLoadDTO {
    int length;
    List<Integer> listId;
    String belong;
    Date newest;
    Date oldest;
}
