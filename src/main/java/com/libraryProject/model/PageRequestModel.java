package com.libraryProject.model;

import java.util.Map;

import org.springframework.data.domain.PageRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor
@Getter @Setter
public class PageRequestModel {

	private int page = 0;
	private int size = 10;

	public PageRequestModel(Map<String, String> params) {
		if (params.containsKey("page")) {
			page = Integer.parseInt(params.get("page"));
		}
		if (params.containsKey("size")) {
			size = Integer.parseInt(params.get("size"));
		}
	}
	
	
	public PageRequest toSpringPageRequest() {  // a lógica de criação do pageable fica aqui: pode colocar novos parametros
		// depois, sem precisar alterar em vários lugares
		return PageRequest.of(page, size);
	}
}
