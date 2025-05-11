/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fabiangomez.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author FABIANG
 */

@Entity
@Table(name= "pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_pizza", nullable = false)
    private Integer idPizza;
    
    @Column(nullable = false, length = 38, unique = true)
    private String name;
    
    @Column(nullable = false, length = 150)
    private String description;
    
    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;
    
    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;
    
    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;
    
    @Column(columnDefinition = "TINYINT")
    private Boolean available;
    
}
