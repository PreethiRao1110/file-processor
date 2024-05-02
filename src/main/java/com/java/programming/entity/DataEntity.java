package com.java.programming.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Table (value = "rainfall2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataEntity {

    @Id
    @PrimaryKey
    @Column(value = "Id")
    private long id;

//    @DateTimeFormat(pattern = "mm/dd/yy")
//    @Column(value = "Date")
//    private Date date;

    @Column(value = "Station Code")
    private String stationCode;

    @Column (value = "Direction")
    private String direction;

    public DataEntity to(){
        return DataEntity.builder().id(id).stationCode(stationCode).direction(direction).build();
    }

}
