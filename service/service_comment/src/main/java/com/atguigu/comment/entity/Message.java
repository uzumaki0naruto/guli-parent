package com.atguigu.comment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private int id;
    private int from_id;
    private int to_id;
    private int conversation;
    private String content;
    private int status;

    private DateTimeLiteralExpression.DateTime create_time;
}
