package com.techacademy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lesson16 Chapter4.3「エンティティの作成」
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="country")
public class Country {
	@Id
	private String code;
	private String name;
	private int population;
}
