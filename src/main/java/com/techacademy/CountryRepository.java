package com.techacademy;

import org.springframework.data.jpa.repository.JpaRepository;

//Lesson16 Chapter4.4「リポジトリの作成」
public interface CountryRepository extends JpaRepository<Country, String>{
	//<Countryクラス,エンティティクラス主キー>
}
