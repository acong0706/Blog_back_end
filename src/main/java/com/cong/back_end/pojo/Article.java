package com.cong.back_end.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Cong
 * @Create_time 2022-10-21 下午 07:30
 * @Project_name back_end
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private int id;
    private String title;
    private String content;
    private String author;
    private String date;
    private String publishDate;
    private String editDate;
    private String oldTags;
    private String newTags;
    private String tags;
    private String[] oldTagsArray;
    private String[] newTagsArray;
}
