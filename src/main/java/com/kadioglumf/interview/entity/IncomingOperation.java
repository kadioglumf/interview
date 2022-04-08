package com.kadioglumf.interview.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "incoming_operation")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class IncomingOperation implements Serializable {

    @Id
    @Column(name = "operation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    @Basic
    @Column(name = "transaction_id")
    private String transactionId;

    @Basic
    @Column(name = "url")
    private String url;

    @Basic
    @Column(name = "status_code")
    private int statusCode;

    @Basic
    @Column(name = "request_time")
    private Date requestTime;

    @Basic
    @Column(name = "response_time")
    private Date responseTime;

    @Basic
    @Lob
    @Column(name = "request_data")
    private String requestData;

    @Basic
    @Lob
    @Column(name = "response_data")
    private String responseData;

    @Basic
    @Column(name = "operation_name")
    private String operationName;
}
