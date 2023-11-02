package com.samuelbraga.pierpont.infrastructure.repositories.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal amount;

  @ManyToOne
  private Account account;

  @ManyToOne
  private OperationType operationType;
}
