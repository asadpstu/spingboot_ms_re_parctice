package org.nightcrwaler.companyms.company;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewModel {
    private String id;
    private String companyId;
    private String reviewer;
    private String content;
    private int rating;

}