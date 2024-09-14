package com.techacademy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Lesson 16Chapter 4.6「コントローラーの作成」
@Controller
@RequestMapping("/country")
public class CountryController {
	private final CountryService service;

	public CountryController(CountryService service) {
		this.service = service;
	}

	//一覧画面
	@GetMapping("/list")
	public String getList(Model model) {
		//全件検索結果をModelに登録
		model.addAttribute("countrylist", service.getCountryList());
		// country/list.htmlに画面遷移
		return "country/list";
	}

	//Lesson16 Chapter5.2追加
	//詳細画面
	@GetMapping(value = {"/detail", "/detail/{code}"})//新規登録と更新2つ分パスのマッピング
	public String getCountry(@PathVariable(name = "code", required = false)String code, Model model) {
		//codeが指定されていたら検索結果、無ければ空のクラスを設定
		Country country = code != null ? service.getCountry(code): new Country();
		//Modelに登録
		model.addAttribute("country", country);
		//country/detail.htmlに画面遷移
		return "country/detail";
	}

	//更新
	@PostMapping("/detail")
	public String postCountry(@RequestParam("code") String code, @RequestParam("name") String name,
			@RequestParam("population") int population, Model model) {
	service.updateCountry(code, name, population); //updateCountry() メソッドで更新処理
	// 一覧画面にリダイレクト
	return "redirect:/country/list"; //再リクエストされる
	}

	//削除画面
	@GetMapping("/delete")
	public String deleteCountryForm(Model model) {
		return "country/delete";
	}
	@GetMapping("/delete/{code}") //課題
	public String getCountry1(@PathVariable("code")String code, Model model) {
		Country country = service.getCountry(code);
		model.addAttribute("country", country);
		return "country/delete";
	}

	//削除
	@PostMapping("/delete")//削除画面へ遷移
	public String deleteCountry(@RequestParam("code") String code, Model model) {
		service.deleteCountry(code);
		// 一覧画面にリダイレクト
		return "redirect:/country/list";
	}
}
